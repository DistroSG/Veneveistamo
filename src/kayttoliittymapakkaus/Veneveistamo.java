package kayttoliittymapakkaus;

import javax.swing.SwingUtilities;

/**
 * Veneveistamo luokka. Jolla suorita ohjelma.
 *
 * @author s1300778
 * @version 1.0
 */
public class Veneveistamo {

    /**
     *Main metodi, joka suorita ohjelma.
     * @param args ei käytetään.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Kotiikkuna("Kotiikkuna").setVisible(true);
            }
        });
    }
}
