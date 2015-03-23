/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package veneveistamo;

import javax.swing.SwingUtilities;

/**
 *
 * @author s1300778
 */
public class Veneveistamo {

    /**
     * @param args the command line arguments
     */
     public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override

            public void run() {

                new PaaIkkuna().setVisible(true);

            }
        });
    }
}
