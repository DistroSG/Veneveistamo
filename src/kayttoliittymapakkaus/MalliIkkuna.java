/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittymapakkaus;

import datapakkaus.Malli;
import java.util.Arrays;
import tietovarastopakkaus.MalliTietovarasto;
/**
 * MalliIkkuna luokka. Jolla asennetaan Malli
 * ikkuna.
 *
 * @author s1300723
 * @version 1.0
 */
public final class MalliIkkuna extends Ikkuna {

    private final MalliTietovarasto rekisteri = new MalliTietovarasto();
    /**
     * Luoda uusi Malli ikkuna otsikon, sarakenimien ja
     * yhdistelmäIndeksen avulla
     *
     * @param ikkunanNimi ikunan nimi
     * @param sarakenimet taulokon sarakenimet
     */
    public MalliIkkuna(String ikkunanNimi, String[] sarakenimet) {
        super(ikkunanNimi, sarakenimet);
        haeKaikkiTiedot();
    }
    /**
     * Suorita muutos
     */
    @Override
    public void suoritaMuutos() {
        arvot = syottopaneeli.getArvot();

        int id = Integer.parseInt(arvot[0]);
        int masto = Integer.parseInt(arvot[3]);
        double hinta = Double.parseDouble(arvot[2]);
        rekisteri.muutaTietoja(new Malli(id, arvot[1], hinta, masto));
        paivitaValintaLista();

    }

    /**
     * Suorita poisto
     */
    @Override
    public void suoritaPoisto() {
        int id = (int) malli.getValueAt(taulukko.getSelectedRow(), 0);
        rekisteri.poistaTieto(id);
        paivitaValintaLista();
    }
    /**
     * Suorita lisäys
     */
    @Override
    public void suoritaLisays() {
        arvot = syottopaneeli.getArvot();
        try {
            int id = Integer.parseInt(arvot[0]);
            int masto = Integer.parseInt(arvot[3]);
            double hinta = Double.parseDouble(arvot[2]);
            rekisteri.lisaaTieto(new Malli(id, arvot[1], hinta, masto));
            syottopaneeli.tyhjennaKentat();
            paivitaValintaLista();

        } catch (NumberFormatException e) {
            virhe("ID:n ja Maston:n pitää olla kokonaislukuja");
        }
    }

    /**
     * Hae kaikki tiedot
     */
    @Override
    public void haeKaikkiTiedot() {
        for (Malli malli1 : rekisteri.haeTiedot()) {
            malli.addRow(Arrays.asList(malli1.getId(), malli1.getMalli(), malli1.getHinta(), malli1.getMasto()));
        }
    }

}
