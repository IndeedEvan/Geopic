package Controllers;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.border.Border;

/**
 *
 * @author Thida
 * Affiche l'image redimensionnée dans Viewer
 */
public class ViewerBGControler implements Border{
    private final BufferedImage back;
    private final Dimension dim;
    
    public ViewerBGControler(BufferedImage back,Dimension d) {
        
        this.back=back;
        this.dim=d;
        
    }
    
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y,int w,int h) {
      
    	Image i = resize();
    	ImageIcon icon = new ImageIcon(i);
        int width = icon.getIconWidth();
		int height = icon.getIconHeight();
		
		int x1 = (dim.width - width) / 2;
		int y1 = (dim.height -height) / 2;
		g.drawImage(i, x1, y1, null);
        
    }
    
    public Image resize() {
		Image img = null;
		int width = back.getWidth();
		int height = back.getHeight();
		if (height<1000 || width<1000){
			img=back.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		}
		if (height>1000 || width>1000){
			img=back.getScaledInstance(width/3, height/3, java.awt.Image.SCALE_SMOOTH);
		}
		if (height>2000 || width>2000){
			img=back.getScaledInstance(width/5, height/5, java.awt.Image.SCALE_SMOOTH);
		}
		if (height>3000 || width>3000){
			img=back.getScaledInstance(width/6, height/6, java.awt.Image.SCALE_SMOOTH);
		}
		return img;
	}
    
    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(0,0,0,0);
    }
    @Override
    public boolean isBorderOpaque(){
        return true;
    }
    
}