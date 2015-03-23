/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package veneveistamo;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author s1300778
 */
public class PoistoCombo extends JFrame {

    private final JPanel pohjapaneeli = new JPanel(new BorderLayout());
    private final JPanel tietopaneeli = new JPanel(new GridLayout(4, 2));
    private final DefaultComboBoxModel malli = new DefaultComboBoxModel();
    private final JComboBox valintalista = new JComboBox(malli);
    private final JButton poistaNappi = new JButton("Poista");

    private final JLabel idSelite = new JLabel("Henkilön ID: ");
    private final JLabel etunimiSelite = new JLabel("Etunimi: ");
    private final JLabel sukunimiSelite = new JLabel("Sukunimi: ");
    private final JLabel syntymavuosiSelite = new JLabel("Syntymävuosi: ");

    private final JTextField id = new JTextField(10);
    private final JTextField etunimi = new JTextField(10);
    private final JTextField sukunimi = new JTextField(10);
    private final JTextField syntymavuosi = new JTextField(10);

    private final Tietovarasto rekisteri;

    public PoistoCombo(Tietovarasto reksiteri) {
        this.rekisteri = reksiteri;

        paivitaValintaLista();

        tietopaneeli.add(idSelite);
        tietopaneeli.add(id);
        tietopaneeli.add(etunimiSelite);
        tietopaneeli.add(etunimi);
        tietopaneeli.add(sukunimiSelite);
        tietopaneeli.add(sukunimi);
        tietopaneeli.add(syntymavuosiSelite);
        tietopaneeli.add(syntymavuosi);

        pohjapaneeli.add(valintalista, BorderLayout.PAGE_START);
        pohjapaneeli.add(tietopaneeli, BorderLayout.CENTER);
        pohjapaneeli.add(poistaNappi, BorderLayout.PAGE_END);

        id.setEditable(false);
        etunimi.setEditable(false);
        sukunimi.setEditable(false);
        syntymavuosi.setEditable(false);

        this.add(pohjapaneeli);
        this.pack();
        this.setTitle("Lisää henkilö");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);

        poistaNappi.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                suoritaPoisto();
            }

        });

        valintalista.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                taytaTiedot();
            }

        });

    }

    private void suoritaPoisto() {
        Elokuva poistettava = (Elokuva) valintalista.getSelectedItem();
        if (poistettava == null) {
            return;
        }
        rekisteri.poistaElokuva(poistettava.getElokuvaNro());
        paivitaValintaLista();
    }

    private void taytaTiedot() {
        Elokuva poistettava = (Elokuva) valintalista.getSelectedItem();
        if (poistettava == null) {
            return;
        }
        id.setText("" + poistettava.getElokuvaNro());
        etunimi.setText("" + poistettava.getNimi());
        sukunimi.setText("" + poistettava.getOhjaaja());
        syntymavuosi.setText("" + poistettava.getVuosi());
    }

    private void paivitaValintaLista() {
        malli.removeAllElements();
        for (Elokuva henkilo : rekisteri.haeKaikkElokuvat()) {
            malli.addElement(henkilo);
        }
        taytaTiedot();
    }
}
