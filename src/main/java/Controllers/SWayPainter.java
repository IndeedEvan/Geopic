package Controllers;

import Models.SWayMaker;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.WaypointPainter;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;

/**
 * @author Ehsan
 */
 
public class SWayPainter extends WaypointPainter<SWayMaker> {
	
	/**
	* Classe qui permet de dessiner les images (sous forme d'objet boutons) sur la carte 
	* Permet également de surligner les images en rouge lors de la recherche
	* Met à jour la carte après suppression d'image ou toute autre interaction
	*/
	 
    private ArrayList<Integer> level;

    @Override
    protected void doPaint(Graphics2D g, JXMapViewer jxMapViewer, int width, int height) {
        level = new ArrayList<>();
        for (int i = 18; i > 0; i--) {
            level.add(i);
        }

        for (SWayMaker swingWaypoint : getWaypoints()) {
            JButton button = swingWaypoint.getButton();
            button.setSize(level.get(jxMapViewer.getZoom()) * 7, level.get(jxMapViewer.getZoom()) * 7);
            if (swingWaypoint.GetTag()) {
                  button.setBorder(BorderFactory.createLineBorder(Color.red, 3));
            } else {
                button.setBorder(null);
            }
            
            button.setVisible(!swingWaypoint.GetDeleted());
            
            Point2D point = jxMapViewer.getTileFactory().geoToPixel(
                    swingWaypoint.getPosition(), jxMapViewer.getZoom());
            Rectangle rectangle = jxMapViewer.getViewportBounds();
            int buttonX = (int) (point.getX() - rectangle.getX());
            int buttonY = (int) (point.getY() - rectangle.getY());
            button.setLocation(buttonX - button.getWidth() / 2, buttonY - button.getHeight() / 2);

        }
        

    }
}
