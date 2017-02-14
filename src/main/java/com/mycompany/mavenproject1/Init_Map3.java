/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import com.drew.imaging.ImageProcessingException;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JToolTip;
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
public class Init_Map3 extends JXMapKit {
  //  private final JXMapViewer mapViewer;
    private ArrayList<SwingWaypoint> swing;
    private ArrayList<ImageMeta> imgmeta;
    private ArrayList<GeoPosition> geos;
    
    public Init_Map3() throws ImageProcessingException, IOException, ClassNotFoundException {
        //final GeoPosition gp = new GeoPosition(43.9307939,2.1282176); 

	TileFactoryInfo veInfo = new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.MAP);
        DefaultTileFactory tileFactory = new DefaultTileFactory(veInfo);
        this.setTileFactory(tileFactory);
         ////////////////////
        File cacheDir = new File(System.getProperty("user.home") + File.separator + ".jxmapviewer2");
        LocalResponseCache.installResponseCache(veInfo.getBaseURL(), cacheDir, false);
        ////////////////////
        this.geos=new ArrayList<>();
        final JToolTip tooltip = new JToolTip();
        tooltip.setVisible(false);
       
        tooltip.setComponent(this.getMainMap());
        this.getMainMap().add(tooltip);
        // Setup JXMapViewer
 
        this.getMainMap().setTileFactory(tileFactory);
        swing=new ArrayList<SwingWaypoint>();
    
        imgmeta=new ArrayList<>();

    Connection c = null;
    Statement stmt = null;
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:test.db");
      c.setAutoCommit(false);
      

      stmt = c.createStatement();
      ResultSet rs = stmt.executeQuery( "SELECT distinct IMG_PATH.IMG_PATH, IMG_GPS.IMG_LAT,IMG_GPS.IMG_LONG FROM IMG_PATH, IMG_GPS WHERE IMG_PATH.IMG_ID=IMG_GPS.IMG_ID;" );
      String path;
      GeoPosition geo;
      ImageMeta img;
      while ( rs.next() ) {
         //int id = rs.getInt("id");
         path=rs.getString("IMG_PATH");
   
         geo=new GeoPosition(rs.getDouble("IMG_LAT"),rs.getDouble("IMG_LONG"));
         geos.add(geo);
         //System.out.println(geos+"   first  ");
        
         img=new ImageMeta(new File(path),geo);
         imgmeta.add(img);
      }
    
      rs.close();
      stmt.close();
     
      c.close();
    } catch ( ImageProcessingException | IOException | ClassNotFoundException | SQLException e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    }
  
    
        for (ImageMeta ima:imgmeta) {
            if (ima.is_taged()) {
            swing.add(new SwingWaypoint(ima.GetImgName(),ima.getGPS(),ima.GetImg()));
            } 
        }
        
        if (swing.isEmpty()) {
          
            this.getMainMap().setZoom(17);
        } else {
            this.getMainMap().setCenterPosition(swing.get(0).getPosition());
          //  mapViewer.zoomToBestFit((Set<GeoPosition>) geos, 0.7);
            this.getMainMap().setZoom(14);
            }

        MouseInputListener mia = new PanMouseInputListener(this.getMainMap());
        this.getMainMap().addMouseListener(mia);
        this.getMainMap().addMouseMotionListener(mia);
        this.getMainMap().addMouseListener(new CenterMapListener(this.getMainMap()));
        this.getMainMap().addMouseWheelListener(new ZoomMouseWheelListenerCenter(this.getMainMap()));
        this.getMainMap().addKeyListener(new PanKeyListener(this.getMainMap()));
     
        // Create waypoints from the geo-positions
       Set<SwingWaypoint> waypoints = new HashSet<SwingWaypoint>(swing);
        // Set the overlay painter
        WaypointPainter<SwingWaypoint> swingWaypointPainter = new SwingWaypointOverlayPainter();
        swingWaypointPainter.setWaypoints(waypoints);
        this.getMainMap().setOverlayPainter(swingWaypointPainter);

        // Add the JButtons to the map viewer
        for (SwingWaypoint w : waypoints) {
            this.getMainMap().add(w.getButton());    
        }
     //this.setMiniMapVisible(false);
    }
    
}
