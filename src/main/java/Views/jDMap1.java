package Views;

import Models.InitMap;
import com.drew.imaging.ImageProcessingException;
import java.io.IOException;
import java.sql.SQLException;
import org.jxmapviewer.viewer.GeoPosition;

/**
 * @author Manon / Eshan
 */

public class jDMap1 extends javax.swing.JDesktopPane {
	
    private final InitMap map;

    /**
     *
     * Classe servant à afficher la carte et les outils permettant de zommer.
     *
     * @param w
     * @param h
     * @throws com.drew.imaging.ImageProcessingException
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    
    public jDMap1(int w, int h) throws ImageProcessingException, IOException, ClassNotFoundException, SQLException {
        this.map = new InitMap(w-3, h-2);
        initComponents();
        
        this.jDPMap.add(map);
        
        this.setSize(w-3, h-2);

    }

    public int getzoom() {
        return map.getZoomSlider().getValue();
    }

    public void setzoom(int z) {
        this.map.setZoom(z);
    }

    public GeoPosition getPosition() {
        return this.map.getAddressLocation();
    }

    public void setGeoLoc(GeoPosition g) {
        this.map.setAddressLocation(g);
    }
    
    private void initComponents() {

        jBZoomIn = new javax.swing.JButton();
        jDPMap = new javax.swing.JDesktopPane();
        jBZoomOut = new javax.swing.JButton();
        jSZoom = new javax.swing.JSlider();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102), 2));
        setPreferredSize(new java.awt.Dimension(800, 600));

        jBZoomIn.setText("-");
        jBZoomIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBZoomInActionPerformed(evt);
            }
        });

        jDPMap.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        jBZoomOut.setText("+");
        jBZoomOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBZoomOutActionPerformed(evt);
            }
        });

        jSZoom.setMajorTickSpacing(17);
        jSZoom.setMaximum(17);
        jSZoom.setMinimum(2);
        jSZoom.setMinorTickSpacing(1);
        jSZoom.setPaintTicks(true);
        jSZoom.setSnapToTicks(true);
        jSZoom.setValue(map.getZoomSlider().getValue());
        jSZoom.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSZoomStateChanged(evt);
            }
        });

        setLayer(jBZoomIn, javax.swing.JLayeredPane.DEFAULT_LAYER);
        setLayer(jDPMap, javax.swing.JLayeredPane.DEFAULT_LAYER);
        setLayer(jBZoomOut, javax.swing.JLayeredPane.DEFAULT_LAYER);
        setLayer(jSZoom, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDPMap)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBZoomOut, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSZoom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBZoomIn, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jBZoomIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBZoomOut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSZoom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDPMap))
        );
    }

    private void jBZoomOutActionPerformed(java.awt.event.ActionEvent evt) {        
        int val=this.map.getZoomSlider().getValue() - 1 ;
        this.map.setZoom(val);
        this.jSZoom.setValue(val);        
    }

    private void jBZoomInActionPerformed(java.awt.event.ActionEvent evt) {
    	int val=this.map.getZoomSlider().getValue() + 1 ;
        this.map.setZoom(val);
        this.jSZoom.setValue(val);
    }
    
    private void jSZoomStateChanged(javax.swing.event.ChangeEvent evt) {
        map.setZoom(jSZoom.getValue());
    }

    private javax.swing.JButton jBZoomIn;
    private javax.swing.JButton jBZoomOut;
    private javax.swing.JDesktopPane jDPMap;
    private javax.swing.JSlider jSZoom;
}