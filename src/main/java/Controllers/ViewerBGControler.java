/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import javax.swing.border.Border;

/**
 *
 * @author Ehsan
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
      
        g.drawImage(back.getScaledInstance(dim.width, dim.height, Image.SCALE_FAST), 0, 0, null);
        
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