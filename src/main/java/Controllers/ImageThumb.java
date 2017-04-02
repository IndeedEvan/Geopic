/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Ehsan
 */
public class ImageThumb {
    private BufferedImage img;
    //private Image img;

    public ImageThumb(File f) throws IOException {
        this.img= ImageIO.read(f);
        //this.img = new ImageIcon(f.getPath()).getImage().getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH);
        img=resize(img, 100, 100);
        File save=new File("thumb/"+f.getName());
        String ext=getFileExtension(f);
        ImageIO.write(img,ext , save);

    }

    private BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }
    private  String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
        return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
}
