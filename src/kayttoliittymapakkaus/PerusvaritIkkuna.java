/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittymapakkaus;

import datapakkaus.Perusvarit;
import java.util.Arrays;
import tietovarastopakkaus.PerusvaritTietovarasto;


/**
 *
 * @author s1300778
 */
public final class PerusvaritIkkuna extends Ikkuna {
    
    private PerusvaritTietovarasto rekisteri = new PerusvaritTietovarasto();

    public PerusvaritIkkuna(String otsikko, String[] columnNames, int comboIndex) {
        super(otsikko, columnNames, comboIndex);
        haeKaikkiTiedot();
    }

    @Override
    public void suoritaMuutos() {
        arvot = syottopaneeli.getArvot();

        int id = Integer.parseInt(arvot[0]);
        rekisteri.muutaTietoja(new Perusvarit(arvot[1], id));
        paivitaValintaLista();
    }

    @Override
    public void suoritaPoisto() {
        int id = (int) taulukkoMalli.getValueAt(taulukko.getSelectedRow(), 0);
        rekisteri.poistaTieto(id);
        paivitaValintaLista();
    }

    @Override
    public void suoritaLisays() {
        arvot = syottopaneeli.getArvot();
        try {
            int id = Integer.parseInt(arvot[0]);
            rekisteri.lisaaTieto(new Perusvarit(arvot[1], id));
            syottopaneeli.tyhjennaKentat();
            paivitaValintaLista();

        } catch (NumberFormatException e) {
            virhe("ElokuvaNro:n ja vuoden pitää olla kokonaislukuja");
        }
    }

    @Override
    public void haeKaikkiTiedot() {
        for (Perusvarit perusvarit : rekisteri.haeTiedot()) {
            taulukkoMalli.addRow(Arrays.asList(perusvarit.getId(), perusvarit.getPerusvarit()));
        }
    }

}
