/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ehsan
 */
public class Import_Folder {
    private final File[] files;
    private Metadata metadata=null;
    private ArrayList<String> stmts;
  
    public Import_Folder(File[] f){
        this.files=f;
        this.stmts=new ArrayList<>();
 
    }
    
    public void test() throws SQLException, ClassNotFoundException {
         
        for (File f1:files) {
           try {
                metadata=ImageMetadataReader.readMetadata(f1);
                Directory directory = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);
                int hg=directory.getInt(ExifSubIFDDirectory.TAG_EXIF_IMAGE_HEIGHT);
                int wd=directory.getInt(ExifSubIFDDirectory.TAG_EXIF_IMAGE_WIDTH);
                DateFormat df = DateFormat.getDateInstance();
                Date date = directory.getDate(ExifSubIFDDirectory.TAG_DATETIME_DIGITIZED);
                df.format(date);
                //System.out.println(w);
                int year = df.getCalendar().get( Calendar.YEAR );
                int month = df.getCalendar().get( Calendar.MONTH )+1 ;
                int day = df.getCalendar().get( Calendar.DAY_OF_MONTH ) ;
                String datey=day+"/"+month+"/"+year;
                
                //////
              //  Class.forName("org.sqlite.JDBC");
               // c = DriverManager.getConnection("jdbc:sqlite:test.db");
               // c.setAutoCommit(false);
               
                
                String sql = "INSERT INTO IMG_PATH (IMG_PATH) VALUES ("+"'"+f1.getAbsoluteFile().toString()+"'"+");"; 
              //  System.out.println(sql);
               
              
                    stmts.add(sql);
               sql = "INSERT INTO IMG_META (IMG_WIDTH,IMG_HEIGHT,IMG_CR_DATE) VALUES ("+String.valueOf(wd)+","+String.valueOf(hg)+","+"'"+datey+"'"+");"; 
            //  System.out.println(sql);
          
                    stmts.add(sql);
              GpsDirectory gpsDir = (GpsDirectory) metadata.getFirstDirectoryOfType(GpsDirectory.class);
              GpsDescriptor gpsDescriptor = new GpsDescriptor(gpsDir);
              
                GeoLocation geo=null;
                if (gpsDir != null) {
                    geo=gpsDir.getGeoLocation();
                    sql = "INSERT INTO IMG_GPS (IMG_LAT, IMG_LONG,IMG_ALT,IMG_TAKE_TIME) VALUES ("+geo.getLatitude()+","+geo.getLongitude()+","+"'"+gpsDescriptor.getGpsAltitudeDescription()+"'"+","+"'"+gpsDescriptor.getGpsTimeStampDescription()+"'"+");"; 
             // System.out.println(sql);
                    stmts.add(sql);
                }
                //stmt.executeUpdate(sql);
              //  stmt.close();
               // System.out.println(f1.getName()+" Done");
                
                //c.close();
                //System.out.println(wd+" | "+hg+" | "+datey);
//
            } catch (ImageProcessingException | IOException | MetadataException ex) {
                Logger.getLogger(Import_Folder.class.getName()).log(Level.SEVERE, null, ex);
            }
         
                
              
       }//System.out.println("before for");
       Class.forName("org.sqlite.JDBC");
       Connection c=DriverManager.getConnection("jdbc:sqlite:test.db");
       Statement stmt=c.createStatement();
       c.setAutoCommit(false);
  
       for (String sql:stmts) {
           stmt.executeUpdate(sql);
         //  System.out.println(sql);
       }
       stmt.close();
       c.commit();
       c.close();
       
    }
    
    /*   public ArrayList<GeoLocation> ImportGPS_Data() throws ImageProcessingException, IOException {
        File p = new File(path.toString());
    //    final String[] acceptedExtensions = new String[] { ".jpg", ".jpeg" };
        ArrayList<GeoLocation> gpsPhotoData = new ArrayList<>();
            Metadata metadata = ImageMetadataReader.readMetadata(p);
           // print(metadata);
            // See whether it has GPS data
            Collection<GpsDirectory> gpsDirectories = metadata.getDirectoriesOfType(GpsDirectory.class);
            for (GpsDirectory gpsDirectory : gpsDirectories) {
                // Try to read out the location, making sure it's non-zero
                GeoLocation geoLocation = gpsDirectory.getGeoLocation();
               
                if (geoLocation != null && !geoLocation.isZero()) {
                    // Add to our collection for use below
                    gpsPhotoData.add(geoLocation);
                    break;
                }
            }
            if (gpsPhotoData.isEmpty()) {
                return null;
            }
        return gpsPhotoData;
            
        }  */

 /*   @Override
    public void ImportIMG_PATH() {
        //System.out.println("in");
       for (File f1:files) {
           try {
                metadata=ImageMetadataReader.readMetadata(f1);
                Directory directory = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);
                int hg=directory.getInt(ExifSubIFDDirectory.TAG_EXIF_IMAGE_HEIGHT);
                int wd=directory.getInt(ExifSubIFDDirectory.TAG_EXIF_IMAGE_WIDTH);
                DateFormat df = DateFormat.getDateInstance();
                Date date = directory.getDate(ExifSubIFDDirectory.TAG_DATETIME_DIGITIZED);
                df.format(date);
                //System.out.println(w);
                int year = df.getCalendar().get( Calendar.YEAR );
                int month = df.getCalendar().get( Calendar.MONTH )+1 ;
                int day = df.getCalendar().get( Calendar.DAY_OF_MONTH ) ;
                String datey=day+"/"+month+"/"+year;
                
                //////
              //  Class.forName("org.sqlite.JDBC");
               // c = DriverManager.getConnection("jdbc:sqlite:test.db");
               // c.setAutoCommit(false);
                
                Statement stmt = c.createStatement();
                String sql = "INSERT INTO IMG_PATH (IMG_PATH) VALUES ("+"'"+f1.getAbsoluteFile().toString()+"'"+");"; 
               // System.out.println(sql);
               
               stmt.addBatch(sql);
               stmt.close();
               stmts.add(stmt);
                //stmt.executeUpdate(sql);
              //  stmt.close();
               // System.out.println(f1.getName()+" Done");
                
                //c.close();
                //System.out.println(wd+" | "+hg+" | "+datey);
//
            } catch (ImageProcessingException | IOException | MetadataException | SQLException ex) {
                Logger.getLogger(Import_Folder.class.getName()).log(Level.SEVERE, null, ex);
            }
       }
    
    }

    @Override
    public ArrayList<Statement> ImportIMG_META() {

        for (File f1 : files) {
            
            try {
                metadata=ImageMetadataReader.readMetadata(f1);
                Directory directory = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);
                int hg=directory.getInt(ExifSubIFDDirectory.TAG_EXIF_IMAGE_HEIGHT);
                int wd=directory.getInt(ExifSubIFDDirectory.TAG_EXIF_IMAGE_WIDTH);
                DateFormat df = DateFormat.getDateInstance();
                Date date = directory.getDate(ExifSubIFDDirectory.TAG_DATETIME_DIGITIZED);
                df.format(date);
                //System.out.println(w);
                int year = df.getCalendar().get( Calendar.YEAR );
                int month = df.getCalendar().get( Calendar.MONTH )+1 ;
                int day = df.getCalendar().get( Calendar.DAY_OF_MONTH ) ;
                String datey=day+"/"+month+"/"+year;
                //////
             //   Class.forName("org.sqlite.JDBC");
              //  c = DriverManager.getConnection("jdbc:sqlite:test.db");
            //    c.setAutoCommit(false);
                
                
              String sql = "INSERT INTO IMG_META (IMG_WIDTH,IMG_HEIGHT,IMG_CR_DATE) VALUES ("+String.valueOf(wd)+","+String.valueOf(hg)+","+"'"+datey+"'"+");"; 
              Statement stmt = c.createStatement();
              stmt.addBatch(sql);
              stmt.close();
              stmts.add(stmt);
//  System.out.println(sql);
             //   stmt.executeUpdate(sql);
             //   stmt.close();
               // System.out.println(f1.getName()+" Done");
             //   c.commit();
            //    c.close();
                //System.out.println(wd+" | "+hg+" | "+datey);
//
            } catch (ImageProcessingException | IOException | MetadataException ex) {
                Logger.getLogger(Import_Folder.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Import_Folder.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return stmts;
        
    }

    @Override
    public void ImportIMG_GPS() {
        for (File f1:files) {
            try {
                Metadata metadata=ImageMetadataReader.readMetadata(f1);
                GpsDirectory gpsDir = (GpsDirectory) metadata.getFirstDirectoryOfType(GpsDirectory.class);
                String[] geo=new String[2];
                if (gpsDir != null) {
                      geo[0]=String.valueOf(gpsDir.getGeoLocation().getLatitude());
                      geo[1]=String.valueOf(gpsDir.getGeoLocation().getLongitude()); 
                }
                
                
                
                
                
            } catch (ImageProcessingException | IOException ex) {
                Logger.getLogger(Import_Folder.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void ExecuteSQL(ArrayList<Statement> stmts) {
        
        
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            for (Statement stm:stmts) {
                stm.executeBatch();
            }
            c.commit();
            c.close();
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Import_Folder.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    
    }
*/

}