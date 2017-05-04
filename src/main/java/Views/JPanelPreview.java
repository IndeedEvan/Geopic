package Views;

import java.awt.Graphics;
import java.awt.Image;
import java.io.Serializable;

/**
 * @author Manon / Thida
 */
public class JPanelPreview extends javax.swing.JPanel implements Serializable {
	
	/**
     *  Classe permettant d'afficher dans le viewer l'image sur laquelle on a cliqué.
     */
	
	Image image = null;

    
    public JPanelPreview(Image image) {
        this.image = image;
        initComponents();
    }
    
    public void setImage(Image image){
        this.image = image;
    }
    
    public Image getImage(Image image){
        return image;
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g); 
        if (image != null) { 
            int height = this.getSize().height;
            int width = this.getSize().width;
            g.drawImage(image,0,0, width, height, this);
        }
    }

    private void initComponents() {
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }
}