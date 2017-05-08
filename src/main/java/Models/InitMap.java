/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Controllers.SWayPainter;
import Exceptions.imgWasDeleted;
import com.drew.imaging.ImageProcessingException;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.MouseInputListener;
import org.jxmapviewer.JXMapKit;
import org.jxmapviewer.VirtualEarthTileFactoryInfo;
import org.jxmapviewer.input.CenterMapListener;
import org.jxmapviewer.input.PanKeyListener;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.LocalResponseCache;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.viewer.WaypointPainter;

/**
 *
 * @author Ehsan
 */
public class InitMap extends JXMapKit {

    private ArrayList<SWayMaker> swing;
    private ArrayList<ImageMeta> imgmeta;
    private ArrayList<GeoPosition> geos;

    public InitMap(int x, int y) throws ImageProcessingException, IOException, ClassNotFoundException, SQLException {
        setSize(x, y);
        TileFactoryInfo veInfo = new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.MAP);
        DefaultTileFactory tileFactory = new DefaultTileFactory(veInfo);
        this.setTileFactory(tileFactory);
        File cacheDir = new File(System.getProperty("user.home") + File.separator + ".jxmapviewer2");
        LocalResponseCache.installResponseCache(veInfo.getBaseURL(), cacheDir, false);
        this.getMainMap().setTileFactory(tileFactory);
        this.getMiniMap().setVisible(false);
        this.getZoomOutButton().setVisible(false);
        this.getZoomInButton().setVisible(false);
        this.getZoomSlider().setVisible(false);

        this.geos = new ArrayList<>();
        swing = new ArrayList<>();
        imgmeta = new ArrayList<>();
        Connection c;
        Statement stmt;
        try {
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT distinct IMG_PATH.IMG_PATH,\n" +
            "IMG_GPS.IMG_LAT,IMG_GPS.IMG_LONG, IMG_META.IMG_CR_DATE FROM IMG_PATH,\n" +
            "IMG_GPS,IMG_META WHERE IMG_PATH.IMG_ID=IMG_GPS.IMG_ID AND IMG_PATH.IMG_ID=IMG_META.IMG_ID");
            String path;
            GeoPosition geo;
            ImageMeta img;

            while (rs.next()) {
                path = rs.getString("IMG_PATH");
                geo = new GeoPosition(rs.getDouble("IMG_LAT"), rs.getDouble("IMG_LONG"));
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                Date d = df.parse(rs.getString("IMG_CR_DATE"));
                img = new ImageMeta(new File(path), geo,d);
                geos.add(geo);
                imgmeta.add(img);

            }

            rs.close();
            stmt.close();
            c.close();

        } catch (ImageProcessingException | SQLException e) {

            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } catch (ParseException ex) {
            Logger.getLogger(InitMap.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (ImageMeta ima : imgmeta) {
            File f = new File(ima.GetImgPath());
            if (f.exists()) {
                swing.add(new SWayMaker(f, ima.getGPS(), ima.getCrDate(), false,false));
            } else {
                imgWasDeleted exp = new imgWasDeleted(f.toString());
                exp.msg(exp.getMessage());
            }
        }

        if (swing.isEmpty()) {
            this.getMainMap().setZoom(17);
            this.getMiniMap().setZoom(17);
        } else {
            this.getMainMap().setCenterPosition(swing.get(0).getPosition());
            this.getMiniMap().setZoom(14);
            this.getMainMap().setZoom(14);
        }

        MouseInputListener mia = new PanMouseInputListener(this.getMainMap());
        this.getMainMap().addMouseListener(mia);
        this.getMainMap().addMouseMotionListener(mia);
        this.getMainMap().addMouseListener(new CenterMapListener(this.getMainMap()));
        this.getMainMap().addMouseWheelListener(new ZoomMouseWheelListenerCenter(this.getMainMap()));
        this.getMainMap().addKeyListener(new PanKeyListener(this.getMainMap()));

        // Create waypoints from the geo-positions
        Set<SWayMaker> waypoints = new HashSet<SWayMaker>(swing);
        // Set the overlay painter
        WaypointPainter<SWayMaker> swingWaypointPainter = new SWayPainter();
        swingWaypointPainter.setWaypoints(waypoints);
        this.getMainMap().setOverlayPainter(swingWaypointPainter);

        // Add the JButtons to the map viewer
        ArrayList<GeoPosition> gP = new ArrayList<>();
        for (SWayMaker w : waypoints) {
            this.getMainMap().add(w.getButton());
            gP.add(w.getPosition());

        }

    }

   public ArrayList<SWayMaker> GetSWayPointsMAP(){
        return this.swing;
    }

}
