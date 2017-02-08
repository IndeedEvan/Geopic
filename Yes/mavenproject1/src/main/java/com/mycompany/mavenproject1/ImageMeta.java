/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.GpsDirectory;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;
import org.jxmapviewer.viewer.GeoPosition;

public class ImageMeta {
	private final ImageIcon img;
	private final int img_width;
	private final int img_height;
        private final File path;
        private final GeoPosition gps;
        

	public ImageMeta(File file, GeoPosition geos) throws ImageProcessingException, IOException {
                this.path=file;
		this.img= new ImageIcon(path.toString());
                this.gps=geos;
		this.img_width=img.getIconWidth();
		this.img_height=img.getIconHeight();
                
            /*    this.metadata=ImageMetadataReader.readMetadata(file);
                
                
              //  this.tags = new ArrayList<>();
                for (Directory directory : metadata.getDirectories()) {
                    for (Tag tag : directory.getTags()) {
                        this.tags.add(tag.toString());
                    }
                } */
               
	}
        

     /*   public ArrayList<GeoLocation> ImportGPS_Data() throws ImageProcessingException, IOException {
        File p = new File(path.toString());
    //    final String[] acceptedExtensions = new String[] { ".jpg", ".jpeg" };
        ArrayList<GeoLocation> gpsPhotoData = new ArrayList<>();
            Metadata metadata = ImageMetadataReader.readMetadata(p);
           // print(metadata);
            // See whether it has GPS data
            Collection<GpsDirectory> gpsDirectories = metadata.getDirectoriesOfType(GpsDirectory.class);
            for (GpsDirectory gpsDirectory : gpsDirectories) {
                // Try to read out the location, making sure it's non-zero
                GeoLocation geoLocation = gpsDirectory.getGeoLocation();
               
                if (geoLocation != null && !geoLocation.isZero()) {
                    // Add to our collection for use below
                    gpsPhotoData.add(geoLocation);
                    break;
                }
            }
            if (gpsPhotoData.isEmpty()) {
                return null;
            }
        return gpsPhotoData;
            
        }  */
        
        
     /*   public String[] GPSRef() {   /*[0]=Latitude ref   |||  [1]=Longitude ref        
            String[] ref={" "," "};
            for (String str:this.tags) {
                if (str.contains("Latitude Ref")) {
                    
                    ref[0]=str.substring(str.length()-1);
                }
                if ((str.contains("Longitude Ref"))) {
                   ref[1]=str.substring(str.length()-1); 
                }
            }
            return ref;
        }  */
        
    /*    public int GetGPS_Alti() {
            for (String str:this.tags) {
                if (str.contains("Altitude -")) {
                    return Integer.parseInt(str.substring(str.indexOf("-")+2, str.indexOf("metres")-1));
                }
            }
            return -1;
        } */
        
       // public String GetCreateDate() {
            
       // }
        public GeoPosition getGPS(){
            return this.gps;
        }

        public String GetImgName() {
            return this.path.getName();
        }
        public boolean is_taged() throws ImageProcessingException, IOException {
            Metadata metadata = ImageMetadataReader.readMetadata(this.path);
            return metadata.containsDirectoryOfType(GpsDirectory.class);
        }
        public int[] GetImgSize(){
            return new int[] {this.img_width,this.img_height};
            
        }
        public ImageIcon GetImg(){
            return this.img;
        }
        
     

}