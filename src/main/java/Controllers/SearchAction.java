
package Controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import org.jdesktop.swingx.JXDatePicker;

/**
 * @author Evan
 */
 
public final class SearchAction {
	/**
	* Classe qui permet d'effectuer une recherche d'image par date
	* Elle affiche le popup et le formulaire
	* get from/to date() : relever les dates du formulaire
	* getAnswer() : l'utilisateur à validé ou annulé ?
	*/
	
    private final JXDatePicker from = new JXDatePicker();
    private final JXDatePicker to = new JXDatePicker();
    private final DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    private final int answer;
    
    public SearchAction(){
        String message = "Pick a date";
        String d = "du";
        String a = "à";
        
        from.setDate(Calendar.getInstance().getTime());
        from.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
        
        to.setDate(Calendar.getInstance().getTime());
        to.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
        
        Object[] params = {message, d,from,a,to};
        int n = JOptionPane.showConfirmDialog(null, params, "Veuillez confirmer",JOptionPane.CANCEL_OPTION);
        
        this.answer=n;
        
        
    }
    
    public Date getFromDate(){
        return this.from.getDate();
    }
    public Date getToDate(){
        return this.to.getDate();
    }
    public int getAnswer(){
        return this.answer;
    }
}
