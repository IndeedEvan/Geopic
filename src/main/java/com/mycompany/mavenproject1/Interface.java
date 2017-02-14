package com.mycompany.mavenproject1;

import com.drew.imaging.ImageProcessingException;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class Interface {

	private JFrame frame;
        private jPanelF panel;
	private JTextField txtJjmmaaaa;
	private JTextField txtJjmmaaaa_1;
	private JTextField txtJjmmaaaa_2;

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
	 */
	public Interface() throws ImageProcessingException, IOException, ClassNotFoundException {
		initialize();
                this.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
               
	}
        private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {
                         
                            JFileChooser jFileChooser = new JFileChooser();
                            jFileChooser.setMultiSelectionEnabled(true);
                            jFileChooser.showOpenDialog(frame);
    
                            File[] resource = jFileChooser.getSelectedFiles();
           
                            Import_Folder imf=new Import_Folder(resource);
                                    try {
                                        imf.test();
                                        int zom=this.panel.getzoom();
                                        
                                        
                                        
                                
                                
                                //JOptionPane.showMessageDialog(null, "Hey");
                                    } catch (SQLException | ClassNotFoundException ex) {
                                        Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
                            }
                }
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws ImageProcessingException, IOException, ClassNotFoundException {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblDate = new JLabel("Recherche\r\n");
		lblDate.setToolTipText("d\u00E9tails");
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDate.setBounds(0, 0, 137, 20);
		frame.getContentPane().add(lblDate);
		Dimension d=new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height);
                panel = new jPanelF(d.width-100,d.height-100);
		//JPanel panel = new JPanel();
		panel.setBackground(new Color(50, 205, 50));
		panel.setBounds(128, 0, 306, 261);
                panel.setSize(d);
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
