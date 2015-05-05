/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittymapakkaus;

import datapakkaus.Maksu;
import java.util.Arrays;
import tietovarastopakkaus.MaksuTietovarasto;

/**
 *
 * @author s1300727
 */
public class MaksuIkkuna extends Ikkuna {

    private MaksuTietovarasto rekisteri = new MaksuTietovarasto();

    public MaksuIkkuna(String otsikko, String[] columnNames, int comboIndex) {
        super(otsikko, columnNames, comboIndex);
        haeKaikkiTiedot();
    }

    @Override
    public void suoritaLisays() {
        values = syottopaneeli.getArvot();
        try {
            int eranumero = Integer.parseInt(values[0]);
            int veneTilaus_id = Integer.parseInt(values[1]);
            double hinta = Integer.parseInt(values[2]);
            rekisteri.lisaaTieto(new Maksu(eranumero, veneTilaus_id, hinta, values[3], values[4]));
            syottopaneeli.tyhjennaKentat();
            paivitaValintaLista();

        } catch (NumberFormatException e) {
            virhe("Eränumeron, VeneTilausID ja hinnan pitää olla kokonaislukuja!");
        }
    }

    @Override
    public void suoritaMuutos() {
        values = syottopaneeli.getArvot();

        int eranumero = Integer.parseInt(values[0]);
        int veneTilaus_id = Integer.parseInt(values[1]);
        double hinta = Integer.parseInt(values[2]);
        rekisteri.muutaTietoja(new Maksu(eranumero, veneTilaus_id, hinta, values[3],values[4]));
        paivitaValintaLista();

    }

    @Override
    public void suoritaPoisto() {
        int eranumero = (int) malli.getValueAt(taulukko.getSelectedRow(), 0);
        rekisteri.poistaTieto(eranumero);
        paivitaValintaLista();
    }

    @Override
    public void haeKaikkiTiedot() {
        for (Maksu maksu : rekisteri.haeTiedot()) {
            malli.addRow(Arrays.asList(maksu.getEranumero(), maksu.getveneTilaus_id(), maksu.getHinta(), maksu.getMaksettupaiva(), maksu.getErapaiva()));
        }
    }

}
