package Views;

import java.awt.Image;

import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JCheckBox;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

/**
 * @author Manon / Thida
 */

public final class Viewer extends JDesktopPane {

    private final JPanel panel_2 = new JPanel();
    private Image image;
    private final Dimension dim;
    

    public Viewer(final String path, Dimension d) {

        
        this.dim = d;
        System.out.println(dim.width);
        panel_2.setSize(60, 100);
        panel_2.setBackground(Color.BLACK);

        ImageIcon cross = new ImageIcon(getClass().getResource("cross.png"));

        Image cross2 = cross.getImage();
        Image cross3 = cross2.getScaledInstance(35, 35, java.awt.Image.SCALE_SMOOTH);
        cross = new ImageIcon(cross3);
        JButton button = new JButton(cross);
        button.setPreferredSize(new Dimension(35, 35));
        button.setFocusable(false);
        button.setContentAreaFilled(false);
        panel_2.add(button);
        image = resize(path);

        this.validate();
       

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JCheckBox checkbox = new JCheckBox("Supprimer la photo définitivement.", false);
                Object[] options = {"Supprimer la photo de la base de données", "Annuler", checkbox};
                int dialogButton = JOptionPane.showOptionDialog(null, "Vous voulez supprimer " + path,
                        "Veuillez choisir", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

                if (dialogButton == JOptionPane.YES_OPTION) {
                    try {
                        DeletePhoto(path, checkbox.isSelected());
                    } catch (SQLException | IOException ex) {
                        Logger.getLogger(Viewer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
       
    }

    public Image resize(String f) {
        Image img = null;
        try {
            img=ImageIO.read(new File(f)).getScaledInstance(dim.width,
                    dim.height, java.awt.Image.SCALE_SMOOTH);
             
        } catch (IOException ex) {
            
            Logger.getLogger(Viewer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return img;
    }

    
    public void DeletePhoto(String path, boolean del) throws SQLException, IOException {

        String sql = "DELETE FROM IMG_PATH WHERE IMG_PATH='" + path + "';";
        try (Connection c = DriverManager.getConnection("jdbc:sqlite:test.db")) {
            Statement stmt = c.createStatement();
            c.setAutoCommit(false);
            stmt.executeUpdate(sql);
            c.commit();
            c.close();
        } catch (java.sql.SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (del) {
            Files.deleteIfExists(Paths.get(path));
        }
    }

}