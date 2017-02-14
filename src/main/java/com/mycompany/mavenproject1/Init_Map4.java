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
import javax.swing.event.ChangeListener;
import javax.swing.event.MouseInputListener;
import org.jxmapviewer.JXMapKit;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
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
public class Init_Map4 extends JXMapViewer{
    
    private final ArrayList<ImageMeta> imgmeta;
    private final ArrayList<SwingWaypoint> swing;
    private final Set<GeoPosition> positions;
    public javax.swing.JSlider zoomSlider;
    
    public Init_Map4() throws ImageProcessingException, IOException{
        TileFactoryInfo info = new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.MAP);
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        tileFactory.setThreadPoolSize(8);
        
         // Setup local file cache
        File cacheDir = new File(System.getProperty("user.home") + File.separator + ".jxmapviewer2");
        LocalResponseCache.installResponseCache(info.getBaseURL(), cacheDir, false);
        
        //this=new JXMapKit();
        setTileFactory(tileFactory);
        this.positions = new HashSet<GeoPosition>();
        this.imgmeta=new ArrayList<>();
        this.swing=new ArrayList<>();

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
         path=rs.getString("IMG_PATH");
   
         geo=new GeoPosition(rs.getDouble("IMG_LAT"),rs.getDouble("IMG_LONG"));
         this.positions.add(geo);
         img=new ImageMeta(new File(path),geo);
         imgmeta.add(img);
      }
      rs.close();
      stmt.close();
     // System.out.println("Opened database successfully");
      c.close();
    } catch ( ImageProcessingException | IOException | ClassNotFoundException | SQLException e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
 
    }

        for (ImageMeta ima:imgmeta) {
            if (ima.is_taged()) {
            swing.add(new SwingWaypoint(ima.GetImgName(),ima.getGPS(),ima.GetImg()));
            } 
        }
        
               
       // Set<SwingWaypoint> waypoints = new HashSet<SwingWaypoint>(Arrays.asList(
                
        if (swing.isEmpty()) {
          
            setZoom(17);
        } else {
            this.setCenterPosition(swing.get(0).getPosition());
            
            //this.zoomToBestFit(this.positions, 0.7);
            this.setZoom(14);
            }
        

        MouseInputListener mia = new PanMouseInputListener(this);
        addMouseListener(mia);
        addMouseMotionListener(mia);
        addMouseListener(new CenterMapListener(this));
        addMouseWheelListener(new ZoomMouseWheelListenerCenter(this));
        addKeyListener(new PanKeyListener(this));
       
        
        
        Set<SwingWaypoint> waypoints = new HashSet<SwingWaypoint>(swing);
        // Set the overlay painter
        for (SwingWaypoint w : waypoints) {
            add(w.getButton());    
        }
        
        WaypointPainter<SwingWaypoint> swingWaypointPainter = new SwingWaypointOverlayPainter();
        swingWaypointPainter.setWaypoints(waypoints);
        this.setOverlayPainter(swingWaypointPainter);

        // Add the JButtons to the map viewer
        zoomSlider=new javax.swing.JSlider(0,17,5);
        zoomSlider.setVisible(true);
        this.add(zoomSlider);
       // zoomSlider.addChangeListener(l);
     
       // this.map.setVisible(true);
    
    }

    
}
