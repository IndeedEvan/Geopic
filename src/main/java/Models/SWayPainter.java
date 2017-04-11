package Models;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.WaypointPainter;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import javax.swing.JButton;

/**
 * "Paints" the Swing waypoints. In fact, just takes care of correct positioning
 * of the representing (button/image).
 *
 * @author Ehsan
 */
public class SWayPainter extends WaypointPainter<SWayMaker> {

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
            Point2D point = jxMapViewer.getTileFactory().geoToPixel(
                    swingWaypoint.getPosition(), jxMapViewer.getZoom());
            Rectangle rectangle = jxMapViewer.getViewportBounds();
            int buttonX = (int) (point.getX() - rectangle.getX());
            int buttonY = (int) (point.getY() - rectangle.getY());
            button.setLocation(buttonX - button.getWidth() / 2, buttonY - button.getHeight() / 2);

        }

    }
}
