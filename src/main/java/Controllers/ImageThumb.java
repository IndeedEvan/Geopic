package Controllers;

import java.io.File;
import java.io.IOException;
import net.coobird.thumbnailator.Thumbnails;

/**
 * @author Ehsan
 */

public class ImageThumb {
	
	/* Permet de créer les miniatures des images(60x60) et les stocker dans le dossier thumb 
	 * ImageThumb() créer les miniatures et leur donne le nom initial de l'image correspondante
	 * getFileExtension() permet l'adaptation des différentes extensions mais non utilisée */
	
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
