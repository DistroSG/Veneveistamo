/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package veneveistamo;

import java.awt.GridBagLayout;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *penis
 * @author s1300778
 */
public abstract class AbstraktiLisaysJaHakuIkkuna extends JFrame {

    private final JPanel oikeanosa = new JPanel();
    protected JPanel vasenosa = new JPanel();
    private final JPanel pohjapaneeli = new JPanel();

    private final JLabel elokuvaNroSelite = new JLabel("ElokuvaNro: ");
    private final JLabel nimiSelite = new JLabel("Nimi: ");
    private final JLabel ohjaajaSelite = new JLabel("Ohjaaja: ");
    private final JLabel vuosiSelite = new JLabel("Vuosi: ");

    protected JTextField elokuvaNro = new JTextField(10);
    protected JTextField nimi = new JTextField(40);
    protected JTextField ohjaaja = new JTextField(30);
    protected JTextField vuosi = new JTextField(4);

    protected Tietovarasto rekisteri;

    public AbstraktiLisaysJaHakuIkkuna(Tietovarasto reksiteri) {
        this.rekisteri = reksiteri;

        asetteleKomponentit();
        this.add(pohjapaneeli);
        this.setLayout(new GridBagLayout());

        this.setTitle("tehtava84");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);

    }

    private void asetteleKomponentit() {
        GroupLayout asettelu = new GroupLayout(oikeanosa);
        oikeanosa.setLayout(asettelu);

        asettelu.setAutoCreateContainerGaps(false);
        asettelu.setAutoCreateGaps(true);

        //asetellaan X-suuntaan
        GroupLayout.ParallelGroup elokuvaNroRyhmaX = asettelu.createParallelGroup();
        elokuvaNroRyhmaX.addComponent(elokuvaNroSelite);
        elokuvaNroRyhmaX.addComponent(elokuvaNro, GroupLayout.PREFERRED_SIZE,
                GroupLayout.DEFAULT_SIZE,
                GroupLayout.PREFERRED_SIZE);

        GroupLayout.ParallelGroup nimiRyhmaX = asettelu.createParallelGroup();
        nimiRyhmaX.addComponent(nimiSelite);
        nimiRyhmaX.addComponent(nimi, GroupLayout.PREFERRED_SIZE,
                GroupLayout.DEFAULT_SIZE,
                GroupLayout.PREFERRED_SIZE);

        GroupLayout.ParallelGroup ohjaajaRyhmaX = asettelu.createParallelGroup();
        ohjaajaRyhmaX.addComponent(ohjaajaSelite);
        ohjaajaRyhmaX.addComponent(ohjaaja, GroupLayout.PREFERRED_SIZE,
                GroupLayout.DEFAULT_SIZE,
                GroupLayout.PREFERRED_SIZE);

        GroupLayout.ParallelGroup vuosiRyhmaX = asettelu.createParallelGroup();
        vuosiRyhmaX.addComponent(vuosiSelite);
        vuosiRyhmaX.addComponent(vuosi, GroupLayout.PREFERRED_SIZE,
                GroupLayout.DEFAULT_SIZE,
                GroupLayout.PREFERRED_SIZE);

        GroupLayout.ParallelGroup pohjaX = asettelu.createParallelGroup();
        pohjaX.addGroup(elokuvaNroRyhmaX);
        pohjaX.addGroup(nimiRyhmaX);
        pohjaX.addGroup(ohjaajaRyhmaX);
        pohjaX.addGroup(vuosiRyhmaX);

        asettelu.setHorizontalGroup(pohjaX);

        //asetellaan Y-suuntaan
        GroupLayout.SequentialGroup elokuvaRyhmaY = asettelu.createSequentialGroup();
        elokuvaRyhmaY.addComponent(elokuvaNroSelite);
        elokuvaRyhmaY.addComponent(elokuvaNro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);

        GroupLayout.SequentialGroup nimiRyhmaY = asettelu.createSequentialGroup();
        nimiRyhmaY.addComponent(nimiSelite);
        nimiRyhmaY.addComponent(nimi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);

        GroupLayout.SequentialGroup ohjaajaRyhmaY = asettelu.createSequentialGroup();
        ohjaajaRyhmaY.addComponent(ohjaajaSelite);
        ohjaajaRyhmaY.addComponent(ohjaaja, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);

        GroupLayout.SequentialGroup vuosiRyhmaY = asettelu.createSequentialGroup();
        vuosiRyhmaY.addComponent(vuosiSelite);
        vuosiRyhmaY.addComponent(vuosi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);

        GroupLayout.SequentialGroup pohjaY = asettelu.createSequentialGroup();
        pohjaY.addGroup(elokuvaRyhmaY);
        pohjaY.addGroup(nimiRyhmaY);
        pohjaY.addGroup(ohjaajaRyhmaY);
        pohjaY.addGroup(vuosiRyhmaY);

        asettelu.setVerticalGroup(pohjaY);

        GroupLayout asetteluPohja = new GroupLayout(pohjapaneeli);
        pohjapaneeli.setLayout(asetteluPohja);

        asetteluPohja.setAutoCreateContainerGaps(true);
        asetteluPohja.setAutoCreateGaps(true);

        //asetellaan X-suuntaan
        GroupLayout.SequentialGroup pohjaXP = asetteluPohja.createSequentialGroup();
        pohjaXP.addComponent(vasenosa);
        pohjaXP.addComponent(oikeanosa);

        asetteluPohja.setHorizontalGroup(pohjaXP);

        //asetellaan Y-suuntaan
        GroupLayout.ParallelGroup pohjaYP = asetteluPohja.createParallelGroup();
        pohjaYP.addComponent(vasenosa);
        pohjaYP.addComponent(oikeanosa);

        asetteluPohja.setVerticalGroup(pohjaYP);

    }

    public AbstraktiLisaysJaHakuIkkuna() {
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

    protected void virhe(String viesti) {
        JOptionPane.showMessageDialog(this,
                viesti, "Virhe", JOptionPane.ERROR_MESSAGE);
    }

    protected void Onnistuminen(String viesti) {
        JOptionPane.showMessageDialog(this,
                viesti, "Onnistuminen", JOptionPane.INFORMATION_MESSAGE);
    }
}
