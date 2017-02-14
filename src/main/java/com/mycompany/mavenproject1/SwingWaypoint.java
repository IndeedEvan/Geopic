package com.mycompany.mavenproject1;

import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * A waypoint that is represented by a button on the map.
 *
 * @author Daniel Stahr
 */
public class SwingWaypoint extends DefaultWaypoint {
    private final JButton button;
    private final Image image;
    private final String text;
    

    public SwingWaypoint(String text, GeoPosition coord,ImageIcon img) {
        super(coord);
        this.text = text;
        this.image = img.getImage().getScaledInstance(60,60, Image.SCALE_SMOOTH);
        
        button = new JButton(text.substring(0, 1), new ImageIcon(image));
        button.setSize(30, 30);
        button.setPreferredSize(new Dimension(30, 30));
        button.addMouseListener(new SwingWaypointMouseListener());
        button.setBorderPainted(false);
        button.setBorder(null);
        button.setMargin(new Insets(0, 0, 0, 0));
        button.setContentAreaFilled(false);
        button.setVisible(true);
      
    }

    JButton getButton() {
        return this.button;
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
