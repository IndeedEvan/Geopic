package Controllers;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

/**
 * @author Sabrina
 */

public class DeleteAction { 
	/* Permet de supprimer une image de la base de donnée
	 DeleteAction() affiche un message pour confirmer ou non la suppression
	 DeletePhoto() suppression de l'image dans la base de donnée (SQL)
	 -> +connexion à la base de données si l'image existe */

    public DeleteAction(String path) {
        JCheckBox checkbox = new JCheckBox("Supprimer ce fichier de façon permanente.");
        String message = "Voulez vous vraiment supprimer cette photo?";
        Object[] params = {message, checkbox};
        int n = JOptionPane.showConfirmDialog(null, params, "Veuillez confirmer", JOptionPane.YES_NO_OPTION);
        boolean check = checkbox.isSelected();

        if (n == JOptionPane.YES_OPTION) {
            this.DeletePhoto(path, check);
        }
    }

    private void DeletePhoto(String path, boolean del) {
        String sql = "DELETE FROM IMG_PATH WHERE IMG_PATH='" + path + "';";
        try (Connection c = DriverManager.getConnection("jdbc:sqlite:test.db")) {
            Statement stmt = c.createStatement();
            c.setAutoCommit(false);
            stmt.executeUpdate(sql);
            c.commit();
            c.close();
        } catch (SQLException ex) {
            System.out.println("Image don't exisit in db");
            System.out.println(ex.getMessage());
        }
        File f=new File(path);
        new File("thumb/"+f.getName()).delete();
        
        if (del) {
             f.delete();
        }
    }
}