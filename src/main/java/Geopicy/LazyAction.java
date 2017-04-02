/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Geopicy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author Ehsan
 */
public abstract class LazyAction implements Runnable { 

    public LazyAction(int millis) {
        Timer t = new Timer(millis, new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                         try{
                            LazyAction.this.run(); }
                         catch(Exception ex){
                            //Your error handling code
                         }
                        }

                    });
                    t.setRepeats(false);
                    t.start();
       }}