/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package veneveistamo;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
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

    private final JButton lisaaNappi = new JButton("Lisää");
    private final JButton poistaNappi = new JButton("Poista");
    private final JButton muutuNappi = new JButton("Muuta");
    private final JButton haeNappi = new JButton("Hae");
    private final JButton haeKaikkiNappi = new JButton("Hae kaikki");
    private final JButton testNappi = new JButton("Test");

    private final Tietovarasto rekisteri = new Tietovarasto();

    private String[] columnNames;

    private Object[][] data;
    private Taulukkomalli malli;

    public PaaIkkuna() {
        pohjapaneeli.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        pohjapaneeli.add(lisaaNappi);
        pohjapaneeli.add(poistaNappi);
        pohjapaneeli.add(muutuNappi);
        pohjapaneeli.add(haeNappi);
        pohjapaneeli.add(haeKaikkiNappi);
        pohjapaneeli.add(testNappi);

        this.add(pohjapaneeli);
        this.pack();
        this.setTitle("PääIkkuna");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        lisaaNappi.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                suoritalisays();
            }

        });

        poistaNappi.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                suoripoisto();
            }

        });

        muutuNappi.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                suoritaMuutos();
            }

        });

        haeNappi.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                suoritaHakeminen();
            }

        });

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

    private void suoritalisays() {
        new LisaysIkkuna(rekisteri).setVisible(true);
    }

    private void suoripoisto() {
        new PoistoCombo(rekisteri).setVisible(true);
    }

    private void suoritaMuutos() {
        new MuutosIkkuna(rekisteri).setVisible(true);
    }

    private void suoritaHakeminen() {
        new HakuIkkuna(rekisteri).setVisible(true);
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
