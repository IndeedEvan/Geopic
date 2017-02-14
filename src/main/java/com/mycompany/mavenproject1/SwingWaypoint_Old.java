package com.mycompany.mavenproject1;

import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

/**
 * A waypoint that is represented by a button on the map.
 *
 * @author Daniel Stahr
 */
public class SwingWaypoint_Old extends DefaultWaypoint {
    public JButton button;
    private final String text;
    private final ImageIcon image;
    private Dimension dim;

    public SwingWaypoint_Old(String text, GeoPosition coord,ImageIcon img) {
        
        super(coord);
        
        this.text = text;
        Image scaledInstance = img.getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH);
        this.image=new ImageIcon(scaledInstance);
        
     
        button = new JButton(text.substring(0, 1));
        button.setSize(50, 50);
        button.setPreferredSize(new Dimension(50, 50));
        button.addMouseListener(new SwingWaypointMouseListener());
        button.setVisible(true);
      //  button = new JButton(text.substring(0, 1) ,image);
   //   button = new JButton("yes");
     //   button.addMouseListener(new SwingWaypointMouseListener());
     //   button.setBorderPainted(false);
      //  button.setBorder(null);
      //  button.setMargin(new Insets(0, 0, 0, 0));
      //  button.setContentAreaFilled(false);
        
     //   button.setVisible(true);
        
       
    }
    
    JButton getButton() { 
        return button;
    }
    
    ImageIcon getImage() {
        return image;
    }
    

    private class SwingWaypointMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            
            JOptionPane.showMessageDialog(button, "You clicked on " + text);
            
        }

        @Override
        public void mousePressed(MouseEvent e) {
            
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }
}
