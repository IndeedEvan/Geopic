/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Exceptions.NoGPStag;
import Exceptions.isImported;
import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.lang.GeoLocation;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.drew.metadata.exif.GpsDescriptor;
import com.drew.metadata.exif.GpsDirectory;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author Ehsan
 */
public class ImportAction {

    private final File[] files;
    private final ArrayList<ArrayList<String>> stmts;
    private ArrayList<File> filestoinsert = new ArrayList<File>();

    public ImportAction(File[] f) {
        this.files = f;
        this.stmts = new ArrayList<>();

    }

    public void files() throws SQLException, NullPointerException, NoGPStag, IOException, MetadataException, ImageProcessingException, ParseException {
        
        ArrayList<File> resources = new ArrayList<File>(Arrays.asList(files));
        dbInsert(resources);

    }

    public void folders() throws SQLException, NullPointerException, NoGPStag, IOException, MetadataException, ImageProcessingException, ParseException {

        File[] list = files[0].listFiles();

        for (File f : list) {
            if (f.isFile()) {
                filestoinsert.add(f);
                //System.out.println(f.getAbsoluteFile());
            }
        }
        //System.out.println(filestoinsert.size());
        //File[] resources = filestoinsert.toArray(new File[filestoinsert.size()]);
        dbInsert(filestoinsert);

    }

    public void folderSubfolder() throws SQLException, NullPointerException, NoGPStag, IOException, MetadataException, ImageProcessingException, ParseException {

        for (File fs : files) {

            browser(fs.toString());
            //File[] resources = filestoinsert.toArray(new File[filestoinsert.size()]);
            dbInsert(filestoinsert);
        }

    }

    public void browser(String path) {

        File root = new File(path);
        File[] list = root.listFiles();

        if (list == null) {
            return;
        }

        for (File f : list) {
            if (f.isDirectory()) {
                browser(f.getAbsolutePath());
            } else {
                filestoinsert.add(f);
            }
        }
    }

    private void dbInsert(ArrayList<File> filestoinsert) throws SQLException, NullPointerException, NoGPStag, IOException, MetadataException, ImageProcessingException, ParseException {
        Metadata metadata;
        //int prgsval = 0;
        //int prgspro = 100 / files.length;
        //final Prgsbarre prgs = new Prgsbarre(prgsval);
        List<String> extension = Arrays.asList("jpg", "jpeg");

        for (File f1 : filestoinsert) {

            String ext = FilenameUtils.getExtension(f1.toString()).toLowerCase();
            if (extension.contains(ext)) {

                try {
                    metadata = ImageMetadataReader.readMetadata(f1);
                    Directory directory = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);
                    if (directory == null) {
                        throw new NoGPStag("Directory is Null", f1);
                    }
                    int hg = directory.getInt(ExifSubIFDDirectory.TAG_EXIF_IMAGE_HEIGHT);

                    int wd = directory.getInt(ExifSubIFDDirectory.TAG_EXIF_IMAGE_WIDTH);
                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    BasicFileAttributes view
                            = Files.getFileAttributeView(f1.toPath(), BasicFileAttributeView.class)
                                    .readAttributes();
                    FileTime fileTime = view.creationTime();

                    Date date = df.parse(df.format(fileTime.toMillis()));
                    df.format(date);
                    int year = df.getCalendar().get(Calendar.YEAR);
                    int month = df.getCalendar().get(Calendar.MONTH) + 1;
                    int day = df.getCalendar().get(Calendar.DAY_OF_MONTH);
                    String datey = day + "/" + month + "/" + year;

                    GpsDirectory gpsDir = (GpsDirectory) metadata.getFirstDirectoryOfType(GpsDirectory.class);
                    GpsDescriptor gpsDescriptor = new GpsDescriptor(gpsDir);

                    if (gpsDir != null) {
                        ArrayList<String> query = new ArrayList<String>();
                        GeoLocation geo;
                        String sql1 = "INSERT INTO IMG_PATH (IMG_PATH) VALUES ("
                                + "'" + f1.getAbsoluteFile().toString() + "'" + ");";
                        query.add(sql1);

                        String sql2 = "INSERT INTO IMG_META (IMG_WIDTH,IMG_HEIGHT,IMG_CR_DATE) VALUES ("
                                + String.valueOf(wd) + "," + String.valueOf(hg) + "," + "'" + datey + "'" + ");";
                        query.add(sql2);

                        geo = gpsDir.getGeoLocation();
                        if (geo == null) {
                            throw new NoGPStag("Geo is Null", f1);
                        }
                        String sql3 = "INSERT INTO IMG_GPS (IMG_LAT, IMG_LONG,IMG_ALT,IMG_TAKE_TIME) VALUES ("
                                + geo.getLatitude() + "," + geo.getLongitude() + "," + "'"
                                + gpsDescriptor.getGpsAltitudeDescription() + "'" + "," + "'"
                                + gpsDescriptor.getGpsTimeStampDescription() + "'" + ");";
                        query.add(sql3);
                        stmts.add(query);
                        //ImageThumb imgth=new ImageThumb(f1);
                        ImageThumb_DEP test = new ImageThumb_DEP(f1);

                        //prgsval += prgspro;
                        //System.out.println(prgsval);
                        //prgs.Prgsupdate(prgsval);
                    } else {
                        throw new NoGPStag("No GPS Data found", f1);
                    }
                } catch (NoGPStag ex) {
                    ex.msg(f1);
                }

            }
        }
        //prgs.dispose();

        if (stmts.size() > 0) {
            try (
                    Connection c = DriverManager.getConnection("jdbc:sqlite:test.db")) {
                c.setAutoCommit(false);
                for (ArrayList query : stmts) {
                    ArrayList<String> st = query;
                    try (Statement stmt = c.createStatement()) {

                        for (String sql : st) {

                            int status = stmt.executeUpdate(sql);

                        }

                    } catch (SQLException e) {
                        if (e.getErrorCode() == 19) {
                            isImported ex = new isImported("importé");
                            ex.msg(st.get(0));
                        }
                    }
                    c.commit();
                }

                c.close();
            }
        }
    }

}
