package Models;

import Views.main;
import Controllers.ImageThumb;
import Controllers.ViewrBGControler;
import Exceptions.imgWasDeleted;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;

/**
 * A waypoint that is represented by a button on the map.
 *
 * @author Daniel Stahr
 */
public class SWayMaker extends DefaultWaypoint {

    private final String path;
    private final JButton button;
    private final ImageIcon icon;

    public SWayMaker(File f, GeoPosition coord) throws IOException {
        super(coord);
        this.path=f.toString();
        File thumb = new File("thumb/" + f.getName());
        if (!thumb.exists()) {
            new ImageThumb(f);
        }

        this.icon = new ImageIcon(thumb.toString());

        button = new JButton(icon);
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

    String getPath() {
        return this.path;
    }

    /**
     *
     * @param jDeskp
     * @param path
     * @throws Exceptions.imgWasDeleted
     */
    public void viewdis(javax.swing.JDesktopPane jDeskp, File path) throws imgWasDeleted {
        try {
            BufferedImage image = ImageIO.read(path);
            jDeskp.setBorder(new ViewrBGControler(image, jDeskp.getSize()));
        } catch (IOException ex) {
            throw new imgWasDeleted("was del");

        }
    }

    private class SwingWaypointMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

            try {
                viewdis(main.jDPViewer, new File(path));
                main.path = path;
                main.jTPane1.setSelectedIndex(1);
            } catch (imgWasDeleted ex) {
                ex.msg(path);
            }

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
