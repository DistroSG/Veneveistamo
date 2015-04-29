/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittymapakkaus;

import javax.swing.SwingUtilities;

/**
 *
 * ayy lmao wow
 *
 * @author s1300778
 */
public class Veneveistamo {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Ikkuna("Test").setVisible(true);
            }
        });
    }
}
