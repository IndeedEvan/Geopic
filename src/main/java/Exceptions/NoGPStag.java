
package Exceptions;

import java.io.File;
import javax.swing.JOptionPane;

/**
 * @author Ehsan
 */
 
public class NoGPStag extends Exception {
	
	/**
	* Pas de coordonées GPS présentes dans les métadonnées de l'image
	*/
	
    public NoGPStag(String msg,File f1) {
        super(msg);
         
                
    }
    public  void msg(File f1){
        JOptionPane.showMessageDialog(null, "L'image " + f1.getName() +
                 " ne contiennent aucune information GPS ");
    }

}
