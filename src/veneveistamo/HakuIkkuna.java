/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package veneveistamo;

import java.awt.Dimension;
import java.util.Vector;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author s1300778
 */
public class HakuIkkuna extends AbstraktiLisaysJaHakuIkkuna {

    protected JPanel paneeli;
    Vector listantiedot = new Vector();
    JList lista = new JList(listantiedot);
    JScrollPane vlista = new JScrollPane(lista);

    public HakuIkkuna(Tietovarasto reksiteri) {
        super(reksiteri);
        paivitaValintaLista();

        paneeli = vasenosa;
        paneeli.add(vlista);
        vlista.setPreferredSize(new Dimension(200, 200));
        vaihadaMuokkaustila(false);
        elokuvaNro.setEditable(false);
        this.pack();

        lista.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                //jos valinta on juuri vaihtumassa palataan kuuntelemaan
                if (e.getValueIsAdjusting()) {
                    return;
                }
                //kun valinta on vaihtunut tehdään halutut tehtävät
                taytaTiedot();

            }

        });

    }

    private void paivitaValintaLista() {

        for (Elokuva elokuva : rekisteri.haeKaikkElokuvat()) {
            listantiedot.addElement(elokuva);
        }

    }

    private void taytaTiedot() {
        Elokuva haettava = (Elokuva) lista.getSelectedValue();
        if (haettava == null) {
            return;
        }
        elokuvaNro.setText("" + haettava.getElokuvaNro());
        nimi.setText("" + haettava.getNimi());
        ohjaaja.setText("" + haettava.getOhjaaja());
        vuosi.setText("" + haettava.getVuosi());
    }

}
