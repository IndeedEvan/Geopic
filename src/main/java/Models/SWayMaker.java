package Models;

import Views.main;
import Controllers.ImageThumb;
import Controllers.ViewerBGController;
import Exceptions.imgWasDeleted;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;

/**
 * @author Ehsan
 */
 
public class SWayMaker extends DefaultWaypoint {
	
	/**
	* Création d'un point géolocalisé
	*/
	
    private final String path;
    private final JButton button;
    private final ImageIcon icon;
    private final Date crdate;
    private boolean tag;
    private boolean del;

    public SWayMaker(File f, GeoPosition coord,Date cr, boolean tagged,boolean deleted) throws IOException {
        super(coord);
        this.path=f.toString();
        this.tag=tagged;
        this.crdate=cr;
        this.del=deleted;
        File thumb = new File("thumb/" + f.getName());
        
        if (!(thumb.getParentFile()).exists()){
        	thumb.getParentFile().mkdir();
            JOptionPane.showMessageDialog(null, "Le dossier étant inexistant, est en cours de création. Veuillez patientez.");

        }
        if (!thumb.exists()) {
        	new ImageThumb(f);
        }

        this.icon = new ImageIcon(thumb.toString());
        button = new JButton(icon);
        button.addMouseListener(new SwingWaypointMouseListener());
        button.setBorderPainted(true);
        button.setBorder(null);
        button.setMargin(new Insets(0, 0, 0, 0));
        button.setContentAreaFilled(false);
        button.setVisible(true);
       
    }

    public JButton getButton() {
        return this.button;
    }

    public String getPath() {
        return this.path;
    }
    public boolean GetTag(){
        return this.tag;
    }
    public Date GetCrDate(){
        return this.crdate;
    }
    public void SetTag(){
        this.tag=true;
    }
    public void RemoveTag(){
        this.tag=false;
    }
    public void SetDeleted(){
        this.del=true;
    }
    public boolean GetDeleted(){
        return this.del;
    }

    /**
     * @param jDeskp
     * @param path
     * @throws Exceptions.imgWasDeleted
     */
    public void viewdis(javax.swing.JDesktopPane jDeskp, File path) throws imgWasDeleted {
        try {
            BufferedImage image = ImageIO.read(path);
            jDeskp.setBorder(new ViewerBGController(image, jDeskp.getSize()));
        } catch (IOException ex) {
            throw new imgWasDeleted("L'image n'existe plus");

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
