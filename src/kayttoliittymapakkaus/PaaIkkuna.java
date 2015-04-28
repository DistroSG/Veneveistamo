/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittymapakkaus;

import taulukkopakkaus.Taulukkomalli;
import tietovarastopakkaus.Tietovarasto;
import datapakkaus.Elokuva;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author s1300778
 */
public class PaaIkkuna extends JFrame {

    private final JPanel pohjapaneeli = new JPanel(new GridLayout(6, 1, 5, 5));

    private final JButton haeKaikkiNappi = new JButton("Hae kaikki");
 
    private final JMenuItem testNappi = new JMenuItem("Test");
    
    private final JMenuBar menu= new JMenuBar();
    
    private final JMenu valinta=new JMenu("Valinnat");

    private final Tietovarasto rekisteri = new Tietovarasto();

    private String[] columnNames;

    private Taulukkomalli malli;

    public PaaIkkuna() {
        pohjapaneeli.add(menu);
        pohjapaneeli.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        pohjapaneeli.add(testNappi);
        
 
        valinta.add(testNappi);
        menu.add(valinta);

        this.add(pohjapaneeli);
        this.pack();
        this.setTitle("PääIkkuna");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        haeKaikkiNappi.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                suorihaekaikki();
            }

        });

        testNappi.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                suoritaTestaus();
            }

        });

    }

    private void suoritaTestaus() {

        columnNames = new String[]{"ElokuvaNro", "Nimi", "Ohjaaja", "Vuosi"};
        malli = new Taulukkomalli(columnNames);
        for (Elokuva elokuva : rekisteri.haeKaikkElokuvat()) {
            malli.addRow(Arrays.asList(elokuva.getElokuvaNro(), elokuva.getNimi(), elokuva.getOhjaaja(), elokuva.getVuosi()));
        }

        Ikkuna test = new Ikkuna(rekisteri, malli, "Test");
        test.setVisible(true);

    }

    private void suorihaekaikki() {
        JTextArea hakulause = new JTextArea(10, 40);
        JScrollPane vieritys = new JScrollPane(hakulause);
        for (Elokuva elokuva : rekisteri.haeKaikkElokuvat()) {
            hakulause.append(elokuva + "\n");
        }
        JOptionPane.showMessageDialog(this, vieritys, "Hae Kaikki", JOptionPane.INFORMATION_MESSAGE);
    }

}
