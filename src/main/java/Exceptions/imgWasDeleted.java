/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Ehsan
 */
public class imgWasDeleted extends IOException {

    public imgWasDeleted(String msg) {
        super(msg);
    }
    
        public void msg(String path) {
        
              Object[] options = {"Supprimer le fichier de la base de données", "Continuer"};
            int dialogButton = JOptionPane.showOptionDialog(null, "le fichier " + path
                    + " n'existe plus",
                    "Veuillez choisir", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

            if (dialogButton == JOptionPane.YES_OPTION) {
                DeletePhoto(path, false);

            }

    }

  
    public void DeletePhoto(String path, boolean del) {
        String sql = "DELETE FROM IMG_PATH WHERE IMG_PATH='" + path + "';";
        try (Connection c = DriverManager.getConnection("jdbc:sqlite:test.db")) {
            Statement stmt = c.createStatement();
            c.setAutoCommit(false);
            stmt.executeUpdate(sql);
            c.commit();
            c.close();
       } catch (SQLException ex){
           System.out.println("DeletePhoto imgwasDeleted");
       }
    }
}