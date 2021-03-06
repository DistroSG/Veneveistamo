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
public final class MaksuIkkuna extends Ikkuna {

    private final MaksuTietovarasto rekisteri = new MaksuTietovarasto();

    public MaksuIkkuna(String ikkunanNimi, String[] columnNames) {
        super(ikkunanNimi, columnNames);
        haeKaikkiTiedot();
    }

    @Override
    public void suoritaLisays() {
        arvot = syottopaneeli.getArvot();
        try {
            int eranumero = Integer.parseInt(arvot[0]);
            int veneTilaus_id = Integer.parseInt(arvot[1]);
            double hinta = Integer.parseInt(arvot[2]);
            rekisteri.lisaaTieto(new Maksu(eranumero, veneTilaus_id, hinta, arvot[3], arvot[4]));
            syottopaneeli.tyhjennaKentat();
            paivitaValintaLista();

        } catch (NumberFormatException e) {
            virhe("Eränumeron, VeneTilausID ja hinnan pitää olla kokonaislukuja! HUOM! päivät syötetään YYYY-MM-DD muodossa");
        }
    }

    @Override
    public void suoritaMuutos() {
        arvot = syottopaneeli.getArvot();

        int eranumero = Integer.parseInt(arvot[0]);
        int veneTilaus_id = Integer.parseInt(arvot[1]);
        double hinta = Double.parseDouble(arvot[2]);
        rekisteri.muutaTietoja(new Maksu(eranumero, veneTilaus_id, hinta, arvot[3],arvot[4]));
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
