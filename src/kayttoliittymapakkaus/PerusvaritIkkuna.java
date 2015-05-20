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
 * PerusvaritIkkuna luokka. Jolla asennetaan Perusvarit
 * ikkuna.
 *
 * @author s1300723
 * @version 1.0
 */
public final class PerusvaritIkkuna extends Ikkuna {
    
    private final PerusvaritTietovarasto rekisteri = new PerusvaritTietovarasto();
    /**
     * Luoda uusi Perusvarit ikkuna otsikon, sarakenimien ja
     * yhdistelmäIndeksen avulla
     *
     * @param ikkunanNimi ikunan nimi
     * @param sarakenimet taulokon sarakenimet
     */
    public PerusvaritIkkuna(String ikkunanNimi, String[] sarakenimet) {
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
        rekisteri.muutaTietoja(new Perusvarit(arvot[1], id));
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
            rekisteri.lisaaTieto(new Perusvarit(arvot[1], id));
            syottopaneeli.tyhjennaKentat();
            paivitaValintaLista();

        } catch (NumberFormatException e) {
            virhe("ID:n pitää olla kokonaisluku");
        }
    }

    /**
     * Hae kaikki tiedot
     */
    @Override
    public void haeKaikkiTiedot() {
        for (Perusvarit perusvarit : rekisteri.haeTiedot()) {
            malli.addRow(Arrays.asList(perusvarit.getId(), perusvarit.getPerusvarit()));
        }
    }

}
