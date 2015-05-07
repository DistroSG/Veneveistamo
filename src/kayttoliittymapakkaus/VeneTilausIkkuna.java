/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittymapakkaus;

import datapakkaus.VeneTilaus;
import java.util.Arrays;
import tietovarastopakkaus.VeneTilausTietovarasto;

/**
 *
 * @author Axel
 */
public class VeneTilausIkkuna extends Ikkuna {

    private VeneTilausTietovarasto rekisteri = new VeneTilausTietovarasto();

    public VeneTilausIkkuna(String otsikko, String[] columnNames, int comboIndex) {
        super(otsikko, columnNames, comboIndex);
        haeKaikkiTiedot();
    }

    @Override
    public void suoritaLisays() {
        values = syottopaneeli.getArvot();
        try {
            int id = Integer.parseInt(values[0]);
            int vene_id = Integer.parseInt(values[1]);
            int henkilosto_id = Integer.parseInt(values[2]);
            double hinta = Integer.parseInt(values[3]);
            int kuljetus_id = Integer.parseInt(values[4]);
            rekisteri.lisaaTieto(new VeneTilaus(id, vene_id, henkilosto_id, hinta, kuljetus_id, values[5], values[6]));
            syottopaneeli.tyhjennaKentat();
            paivitaValintaLista();

        } catch (NumberFormatException e) {
            virhe("Eränumeron, VeneTilausID ja hinnan pitää olla kokonaislukuja!");
        }
    }

    @Override
    public void suoritaMuutos() {
        values = syottopaneeli.getArvot();

        int id = Integer.parseInt(values[0]);
        int vene_id = Integer.parseInt(values[1]);
        int henkilosto_id = Integer.parseInt(values[2]);
        double hinta = Integer.parseInt(values[3]);
        int kuljetus_id = Integer.parseInt(values[4]);
        rekisteri.muutaTietoja(new VeneTilaus(id, vene_id, henkilosto_id, hinta, kuljetus_id, values[5], values[6]));
        paivitaValintaLista();
    }

    @Override
    public void suoritaPoisto() {
        int id = (int) malli.getValueAt(taulukko.getSelectedRow(), 0);
        rekisteri.poistaTieto(id);
        paivitaValintaLista();
    }

    @Override
    public void haeKaikkiTiedot() {
        for (VeneTilaus venetilaus : rekisteri.haeTiedot()) {
            malli.addRow(Arrays.asList(venetilaus.getId(), venetilaus.getVene_id(), venetilaus.getHenkilosto_id(), venetilaus.getHinta(), venetilaus.getKuljetus_id(), venetilaus.getVari(), venetilaus.getEdistyminen()));
        }
    }

}
