/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Geopicy;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.border.Border;

/**
 *
 * @author Ehsan
 */



public class Prgsbarre extends JFrame {
    JProgressBar progressBar;
    
  public Prgsbarre(int prgval ) {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    progressBar = new JProgressBar();
    progressBar.setValue(prgval);
    progressBar.setStringPainted(true);
    Border border = BorderFactory.createTitledBorder("Loading...");
    progressBar.setBorder(border);
    add(progressBar);
    setSize(300, 100);
    setLocationRelativeTo(null);
    setUndecorated(true);
    setVisible(true);

    
  }
  public void Prgsupdate(int prgval) throws InterruptedException {
      this.progressBar.setValue(prgval);
      this.progressBar.setStringPainted(true);
      if (prgval==100) {
          this.dispose();
         // main.m.setVisible(true);
      }
      Thread.sleep(1000);
      
  }
  
  public int PrgsGetval(){
      return this.progressBar.getValue();
  }
}


