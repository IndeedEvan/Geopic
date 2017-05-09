
package Exceptions;

import javax.swing.JOptionPane;

/**
 * @author Ehsan
 */
 
public class isImported extends java.sql.SQLException {

	/**
	* Evite d'importer 2 fois la même image
	*/

    public isImported(String msg) {
        super(msg);
        
    }

    public void msg(String path) {
        JOptionPane.showMessageDialog(null, "l'image "
                + path.substring(path.indexOf("('")) + " est déjà importée");

    }
}
