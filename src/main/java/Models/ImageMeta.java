/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import com.drew.imaging.ImageProcessingException;
import java.io.File;
import java.io.IOException;
import org.jxmapviewer.viewer.GeoPosition;

public class ImageMeta {

    private final File f;
    private final GeoPosition gps;
    private final String path;

    public ImageMeta(File file, GeoPosition geos) throws ImageProcessingException, IOException {
        this.f = file;
        this.gps = geos;
        this.path=file.toString();
        
    }

    public GeoPosition getGPS() {
        return this.gps;
    }

    public String GetImgPath() {
        return this.path;
    }

}
