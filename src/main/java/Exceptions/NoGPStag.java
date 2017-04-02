/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

import java.io.File;
import javax.swing.JOptionPane;

/**
 *
 * @author Ehsan
 */
public class NoGPStag extends Exception {

    public NoGPStag(String msg,File f1) {
        super(msg);
         
                
    }
    public  void msg(File f1){
        JOptionPane.showMessageDialog(null, "L'image " + f1.getName() +
                 " ne contiennent aucune information GPS ");
    }

}
