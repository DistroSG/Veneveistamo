/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package veneveistamo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author s1300778
 */
public class Ikkuna extends JFrame {

    private final JPanel pohjapaneeli = new JPanel();

    private final JTextField hakuKentta = new JTextField("Haku", 10);

    private final JButton lisaaNappi = new JButton("Lisää");

    private final Tietovarasto rekisteri;

    private final String[] sarakeNimet;

    private final Object[][] tiedot;

    private final JTable taulukko;
    private final JScrollPane vieritettavaRuutu;

    public Ikkuna(Tietovarasto rekisteri,String[] sarakeNimet, Object[][] tiedot, String otsikko) {

        this.rekisteri = rekisteri;
        this.sarakeNimet = sarakeNimet;
        this.tiedot = tiedot;
        
        taulukko = new JTable(tiedot, sarakeNimet);
        //taulukko.setPreferredScrollableViewportSize(new Dimension(500, 70));
        taulukko.setFillsViewportHeight(true);
        vieritettavaRuutu = new JScrollPane(taulukko);
        

        asetteleKomponentit();
        this.add(pohjapaneeli);
        this.setLayout(new GridBagLayout());

        this.setTitle(otsikko);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        taulukko.setAutoCreateRowSorter(true);
//        taulukko.setShowGrid(true);
//        taulukko.setGridColor(Color.YELLOW); 
//        taulukko.setBackground(Color.CYAN);

    }

    private void asetteleKomponentit() {
        GroupLayout asettelu = new GroupLayout(pohjapaneeli);
        pohjapaneeli.setLayout(asettelu);

        asettelu.setAutoCreateContainerGaps(true);
        asettelu.setAutoCreateGaps(true);

        //asetellaan X-suuntaan
        GroupLayout.SequentialGroup ylaosaRyhmaX = asettelu.createSequentialGroup();
        ylaosaRyhmaX.addComponent(hakuKentta, GroupLayout.PREFERRED_SIZE,
                GroupLayout.DEFAULT_SIZE,
                GroupLayout.PREFERRED_SIZE);
        ylaosaRyhmaX.addComponent(lisaaNappi);

        GroupLayout.ParallelGroup pohjaX = asettelu.createParallelGroup();
        pohjaX.addGroup(ylaosaRyhmaX);
        pohjaX.addComponent(vieritettavaRuutu, 300, 500, 1000);

        asettelu.setHorizontalGroup(pohjaX);

        //asetellaan Y-suuntaan
        GroupLayout.ParallelGroup ylaosaRyhmaY = asettelu.createParallelGroup();
        ylaosaRyhmaY.addComponent(hakuKentta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);
        ylaosaRyhmaY.addComponent(lisaaNappi);

        GroupLayout.SequentialGroup pohjaY = asettelu.createSequentialGroup();
        pohjaY.addGroup(ylaosaRyhmaY);
        pohjaY.addComponent(vieritettavaRuutu, 300, 500, 1000);

        asettelu.setVerticalGroup(pohjaY);

    }

}
