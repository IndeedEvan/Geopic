package Views;

import Controllers.ImportAction;
import Exceptions.NoGPStag;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.MetadataException;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

public class Interface extends JFrame{

    private JFrame frame;
    private jPMap panel;
    private JTextField txtJjmmaaaa;
    private JTextField txtJjmmaaaa_1;
    private JTextField txtJjmmaaaa_2;
    private javax.swing.JMenu jMFile;
    private javax.swing.JMenu jMEdit;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JMenuItem jMFile_item1;
    private javax.swing.JMenuItem jMFile_item2;
    private final Dimension d = new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Interface window = new Interface();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     *
     * @throws com.drew.imaging.ImageProcessingException
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public Interface() throws ImageProcessingException, IOException, ClassNotFoundException, SQLException {
        initialize();
        frame.setSize(d);

    }

    private void ImportAction(java.awt.event.ActionEvent evt) throws SQLException, ImageProcessingException, IOException, ClassNotFoundException, ParseException, NoGPStag, MetadataException {
        // TODO add your handling code here:
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setMultiSelectionEnabled(true);
        int res = jFileChooser.showOpenDialog(frame);
        if (res == JFileChooser.APPROVE_OPTION) {
            File[] resource = jFileChooser.getSelectedFiles();
            ImportAction imf = new ImportAction(resource);
            try {
                imf.files();
            } catch (NullPointerException ex) {
                Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
            }
            int zom=this.panel.getzoom();
            this.panel.removeAll();
            panel =new jPMap(d.width - 105, d.height - 120);
            panel.setzoom(zom);
            this.frame.add(panel);
            this.frame.revalidate();
        }

    }

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:

    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() throws ImageProcessingException, IOException, ClassNotFoundException, SQLException {
        frame = new JFrame();

        //frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.getContentPane().setLayout(null);
        jMenuBar = new javax.swing.JMenuBar();
        jMFile = new javax.swing.JMenu();
        jMFile_item1 = new javax.swing.JMenuItem();
        jMFile_item2 = new javax.swing.JMenuItem();
        jMEdit = new javax.swing.JMenu();
        jMFile.setText("File");

        jMFile_item1.setText("vide");
        jMFile_item1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMFile.add(jMFile_item1);
        jMFile_item2.setText("Open");
        jMFile.add(jMFile_item2);
        jMenuBar.add(jMFile);
        jMEdit.setText("Edit");
        jMenuBar.add(jMEdit);

        jMFile_item2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                try {
                    ImportAction(evt);
                } catch (SQLException | ImageProcessingException | IOException | ClassNotFoundException | ParseException | NoGPStag | MetadataException ex) {
                    Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        JLabel lblDate = new JLabel("Recherche\r\n");
        lblDate.setToolTipText("d\u00E9tails");
        lblDate.setHorizontalAlignment(SwingConstants.CENTER);
        lblDate.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblDate.setBounds(0, 0, 137, 20);
        frame.getContentPane().add(lblDate);

        panel = new jPMap(d.width - 105, d.height - 120);
        panel.setBackground(new Color(50, 205, 50));
        panel.setBounds(128, 0, 306, 261);
        panel.setSize(d);
        frame.setJMenuBar(jMenuBar);
        frame.getContentPane().add(panel);

        JLabel lblLe = new JLabel("Le");
        lblLe.setBounds(10, 47, 21, 14);
        frame.getContentPane().add(lblLe);

        txtJjmmaaaa = new JTextField();
        txtJjmmaaaa.setText("JJ/MM/AAAA");
        txtJjmmaaaa.setBounds(29, 44, 86, 20);
        frame.getContentPane().add(txtJjmmaaaa);
        txtJjmmaaaa.setColumns(10);

        JLabel lblDe = new JLabel("Du");
        lblDe.setBounds(10, 99, 21, 14);
        frame.getContentPane().add(lblDe);

        txtJjmmaaaa_1 = new JTextField();
        txtJjmmaaaa_1.setText("JJ/MM/AAAA");
        txtJjmmaaaa_1.setBounds(29, 96, 86, 20);
        frame.getContentPane().add(txtJjmmaaaa_1);
        txtJjmmaaaa_1.setColumns(10);

        JLabel lblNewLabel = new JLabel("Au");
        lblNewLabel.setBounds(10, 127, 21, 14);
        frame.getContentPane().add(lblNewLabel);

        txtJjmmaaaa_2 = new JTextField();
        txtJjmmaaaa_2.setText("JJ/MM/AAAA");
        txtJjmmaaaa_2.setBounds(29, 124, 86, 20);
        frame.getContentPane().add(txtJjmmaaaa_2);
        txtJjmmaaaa_2.setColumns(10);

        JButton btnNewButton = new JButton("Configuration");

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

            }
        });
        btnNewButton.setBounds(0, 238, 126, 23);
        frame.getContentPane().add(btnNewButton);

        JLabel lblOu = new JLabel("OU");
        lblOu.setBounds(54, 75, 21, 14);
        frame.getContentPane().add(lblOu);

        JSeparator separator = new JSeparator();
        separator.setBounds(10, 83, 34, 5);
        frame.getContentPane().add(separator);

        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(80, 83, 34, 2);
        frame.getContentPane().add(separator_1);

        JLabel lblParDate = new JLabel("par date");
        lblParDate.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblParDate.setBounds(42, 19, 65, 14);
        frame.getContentPane().add(lblParDate);

    }
}
