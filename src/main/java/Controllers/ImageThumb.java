/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.File;
import java.io.IOException;
import net.coobird.thumbnailator.Thumbnails;

/**
 *
 * @author Ehsan
 */
public class ImageThumb {

    public ImageThumb(File f) throws IOException{
        
        Thumbnails.of(f).size(60, 60)
                .toFile(new File("thumb/" + f.getName()
                        ));
  

    }

    private String getFileExtension(File file) {
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        } else {
            return "";
        }
    }
}
