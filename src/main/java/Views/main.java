package Views;

import Controllers.DeleteAction;
import Controllers.ImportAction;
import Exceptions.NoGPStag;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.MetadataException;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * @author Manon / Ehsan
 */

public class main extends javax.swing.JFrame {
	/* 
	 * Classe principal : Initialise tous les composants de l'interface 
	 */
	
    private final Dimension d = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
    private jDMap1 jDmap;
    public static String path;

    public main() {
        
        initComponents();
        this.setSize(d);

        try {

            this.jDmap = new jDMap1(d.width - 30, d.height - 170);
            this.jDPMap.add(jDmap);

        } catch (ImageProcessingException | IOException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void initComponents() {

        jTPane1 = new javax.swing.JTabbedPane();
        jDPMap = new javax.swing.JDesktopPane();
        jIntFViewer = new javax.swing.JInternalFrame();
        jToolBar1 = new javax.swing.JToolBar();
        jBSlideShow = new javax.swing.JButton();
        jBDeleteimg = new javax.swing.JButton();
        PreviewPane = new javax.swing.JScrollPane();
        jDPViewer = new javax.swing.JDesktopPane();
        jMBar = new javax.swing.JMenuBar();
        jM1 = new javax.swing.JMenu();
        jM1Item1 = new javax.swing.JMenuItem();
        jM1Item2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jM2 = new javax.swing.JMenu();
        jM2Item1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jTPane1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jDPMapLayout = new javax.swing.GroupLayout(jDPMap);
        jDPMap.setLayout(jDPMapLayout);
        jDPMapLayout.setHorizontalGroup(
            jDPMapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 714, Short.MAX_VALUE)
        );
        
        jDPMapLayout.setVerticalGroup(
            jDPMapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 637, Short.MAX_VALUE)
        );

        jTPane1.addTab("Carte", jDPMap);

        jIntFViewer.setBorder(null);
        jIntFViewer.setVisible(true);

        jToolBar1.setRollover(true);
        jToolBar1.setFloatable(false);
        jBSlideShow.setText("SlideShow");
        jBSlideShow.setFocusable(false);
        jBSlideShow.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jBSlideShow.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jBSlideShow);

        jBDeleteimg.setText("Suppression");
        jBDeleteimg.setFocusable(false);
        jBDeleteimg.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jBDeleteimg.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jBDeleteimg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBDeleteimgActionPerformed(evt);
            }
        });
        jToolBar1.add(jBDeleteimg);

        javax.swing.GroupLayout jIntFViewerLayout = new javax.swing.GroupLayout(jIntFViewer.getContentPane());
        jIntFViewer.getContentPane().setLayout(jIntFViewerLayout);
        jIntFViewerLayout.setHorizontalGroup(
            jIntFViewerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PreviewPane)
            .addComponent(jDPViewer, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        
        jIntFViewerLayout.setVerticalGroup(
            jIntFViewerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jIntFViewerLayout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jDPViewer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PreviewPane, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        ((javax.swing.plaf.basic.BasicInternalFrameUI) jIntFViewer.getUI
            ()).setNorthPane(null);

        jTPane1.addTab("Viewer", jIntFViewer);

        jM1.setText("File");

        jM1Item1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        jM1Item1.setText("Import Photo");
        
        jM1Item1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jM1It1Act1ImportPh(evt);
            }
        });
        
        jM1.add(jM1Item1);

        jM1Item2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        jM1Item2.setText("Import Folder");
        
        jM1Item2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jM1It1Act1ImportFolder(evt);
            }
        });
        
        jM1.add(jM1Item2);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Import Folder & subs");
        
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jM1It1Act1ImportF_subF(evt);
            }
        });
        
        jM1.add(jMenuItem1);

        jMBar.add(jM1);

        jM2.setText("Edit");

        jM2Item1.setText("jMenuItem3");
        jM2.add(jM2Item1);

        jMBar.add(jM2);

        setJMenuBar(jMBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTPane1)
                .addContainerGap())
        );

        pack();
    }

    private void jM1It1Act1ImportPh(java.awt.event.ActionEvent evt) {
        
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jFileChooser.setMultiSelectionEnabled(true);
        int res = jFileChooser.showOpenDialog(this);
        if (res == JFileChooser.APPROVE_OPTION) {
            try {
                File[] resource = jFileChooser.getSelectedFiles();

                ImportAction imf = new ImportAction(resource);
                imf.files();
                int zom = this.jDmap.getzoom();
                this.jDPMap.removeAll();
                jDmap = new jDMap1(d.width - 30, d.height - 170);
                jDmap.setzoom(zom);
                this.jDPMap.add(jDmap);
            } catch (IOException ex) {

            } catch (ImageProcessingException | ClassNotFoundException | SQLException | NullPointerException | NoGPStag | MetadataException | ParseException ex) {
                System.out.println(ex.getMessage());
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void jBDeleteimgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBDeleteimgActionPerformed
        new DeleteAction(path);   
    }

    private void jM1It1Act1ImportFolder(java.awt.event.ActionEvent evt) {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jFileChooser.setMultiSelectionEnabled(false);

        int res = jFileChooser.showOpenDialog(this);
        if (res == JFileChooser.APPROVE_OPTION) {

            File[] resource = {jFileChooser.getSelectedFile()};
            ImportAction imf = new ImportAction(resource);

            try {
                imf.folders();
                int zom = this.jDmap.getzoom();
                this.jDPMap.removeAll();
                jDmap = new jDMap1(d.width - 30, d.height - 170);
                jDmap.setzoom(zom);
                this.jDPMap.add(jDmap);
            } catch (SQLException | NullPointerException | NoGPStag | IOException | MetadataException | ImageProcessingException | ParseException | ClassNotFoundException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    private void jM1It1Act1ImportF_subF(java.awt.event.ActionEvent evt) {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jFileChooser.setMultiSelectionEnabled(true);

        int res = jFileChooser.showOpenDialog(this);
        if (res == JFileChooser.APPROVE_OPTION) {

            File[] resource = jFileChooser.getSelectedFiles();

            ImportAction imf = new ImportAction(resource);
            try {
                imf.folderSubfolder();
                int zom = this.jDmap.getzoom();
                this.jDPMap.removeAll();
                jDmap = new jDMap1(d.width - 30, d.height - 170);
                jDmap.setzoom(zom);
                this.jDPMap.add(jDmap);
            } catch (SQLException | NullPointerException | NoGPStag | IOException | MetadataException | ImageProcessingException | ParseException | ClassNotFoundException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                String laf = UIManager.getSystemLookAndFeelClassName();
                try {
                    UIManager.setLookAndFeel(laf);
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                }

                new main().setVisible(true);
            }
        });
    }

    private javax.swing.JScrollPane PreviewPane;
    private javax.swing.JButton jBDeleteimg;
    private javax.swing.JButton jBSlideShow;
    private javax.swing.JDesktopPane jDPMap;
    public static javax.swing.JDesktopPane jDPViewer;
    private javax.swing.JInternalFrame jIntFViewer;
    private javax.swing.JMenu jM1;
    private javax.swing.JMenuItem jM1Item1;
    private javax.swing.JMenuItem jM1Item2;
    private javax.swing.JMenu jM2;
    private javax.swing.JMenuItem jM2Item1;
    private javax.swing.JMenuBar jMBar;
    private javax.swing.JMenuItem jMenuItem1;
    public static javax.swing.JTabbedPane jTPane1;
    private javax.swing.JToolBar jToolBar1;
}