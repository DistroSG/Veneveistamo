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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author s1300778
 */
public class MuutosIkkuna extends JFrame {

    private final JPanel ylaosa = new JPanel(new GridLayout(4, 2));
    private final JPanel pohjapaneeli = new JPanel(new BorderLayout());

    private final JLabel elokuvaNroSelite = new JLabel("ElokuvaNro: ");
    private final JLabel nimiSelite = new JLabel("Nimi: ");
    private final JLabel ohjaajaSelite = new JLabel("Ohjaaja: ");
    private final JLabel vuosiSelite = new JLabel("Vuosi: ");

    private final JTextField elokuvaNro = new JTextField(10);
    private final JTextField nimi = new JTextField(10);
    private final JTextField ohjaaja = new JTextField(10);
    private final JTextField vuosi = new JTextField(10);

    private final JButton heaNappi = new JButton("Hae");
    private final JButton talletaNappi = new JButton("Taleta");

    private final Tietovarasto rekisteri;

    public MuutosIkkuna(Tietovarasto reksiteri) {
        this.rekisteri = reksiteri;

        nimi.setEditable(false);
        ohjaaja.setEditable(false);
        vuosi.setEditable(false);
        ylaosa.add(elokuvaNroSelite);
        ylaosa.add(elokuvaNro);
        ylaosa.add(nimiSelite);
        ylaosa.add(nimi);
        ylaosa.add(ohjaajaSelite);
        ylaosa.add(ohjaaja);
        ylaosa.add(vuosiSelite);
        ylaosa.add(vuosi);

        pohjapaneeli.add(ylaosa, BorderLayout.CENTER);
        pohjapaneeli.add(heaNappi, BorderLayout.PAGE_END);

        this.add(pohjapaneeli);
        this.pack();
        this.setTitle("Hae henkilö");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);

        heaNappi.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                suoritaHakeminen();
            }

        });

        talletaNappi.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                suoritaTallennus();
            }

        });

    }

    private void suoritaHakeminen() {
        try {
            try {

                int hid = Integer.parseInt(elokuvaNro.getText());
                nimi.setText(rekisteri.haeElokuva(hid).getNimi());
                ohjaaja.setText(rekisteri.haeElokuva(hid).getOhjaaja());
                vuosi.setText(rekisteri.haeElokuva(hid).getVuosi() + "");

                elokuvaNro.setEditable(false);
                nimi.setEditable(true);
                ohjaaja.setEditable(true);
                vuosi.setEditable(true);
                pohjapaneeli.remove(heaNappi);
                pohjapaneeli.add(talletaNappi, BorderLayout.PAGE_END);
                pohjapaneeli.updateUI();

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ei ollu numero! \nAnna numero, kiitos!",
                        "Virhe",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Henkilö ei löytty",
                    "Virhe",
                    JOptionPane.ERROR_MESSAGE);

        }

    }

    private void suoritaTallennus() {
        int hid = Integer.parseInt(elokuvaNro.getText());
        int svuosi = Integer.parseInt(vuosi.getText());
        rekisteri.paivitaElokuva(new Elokuva(hid, nimi.getText(),
                ohjaaja.getText(), svuosi));
        Onnistuminen("Muutos onnistui");
        pohjapaneeli.remove(talletaNappi);
        pohjapaneeli.add(heaNappi, BorderLayout.PAGE_END);
        pohjapaneeli.updateUI();
        this.setTitle("Hae henkilö");
        vaihadaMuokkaustila(false);
        tyhjennaKentat();
    }

    protected void Onnistuminen(String viesti) {
        JOptionPane.showMessageDialog(this,
                viesti, "Onnistuminen", JOptionPane.INFORMATION_MESSAGE);
    }

    protected void tyhjennaKentat() {
        elokuvaNro.setText("");
        nimi.setText("");
        ohjaaja.setText("");
        vuosi.setText("");
        elokuvaNro.requestFocus();
    }

    protected void vaihadaMuokkaustila(boolean tila) {
        elokuvaNro.setEditable(!tila);
        nimi.setEditable(tila);
        ohjaaja.setEditable(tila);
        vuosi.setEditable(tila);

    }
}
