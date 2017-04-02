/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

import javax.swing.JOptionPane;

/**
 *
 * @author Ehsan
 */
public class isImported extends java.sql.SQLException {

    public isImported(String msg) {
        super(msg);
        
    }

    public void msg(String path) {
        JOptionPane.showMessageDialog(null, "l'image "
                + path.substring(path.indexOf("('")) + " est déjà importée");

    }
}
