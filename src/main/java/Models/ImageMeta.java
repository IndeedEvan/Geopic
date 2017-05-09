
package Models;

import com.drew.imaging.ImageProcessingException;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import org.jxmapviewer.viewer.GeoPosition;

/**
 * @author Ehsan
 */

public class ImageMeta {
	
	/**
	* Construction de l'image et la relie à ses coordonnées GPS + date de création
	*/

    private final File f;
    private final GeoPosition gps;
    private final String path;
    private final Date crdate;

    public ImageMeta(File file, GeoPosition geos,Date cr ) throws ImageProcessingException, IOException {
        this.f = file;
        this.gps = geos;
        this.path=file.toString();
        this.crdate=cr;
        
    }

    public GeoPosition getGPS() {
        return this.gps;
    }

    public String GetImgPath() {
        return this.path;
    }
    public Date getCrDate(){
        return this.crdate;
    }

}
