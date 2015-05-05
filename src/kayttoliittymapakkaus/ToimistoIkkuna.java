/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittymapakkaus;

import datapakkaus.Toimisto;
import java.util.Arrays;
import tietovarastopakkaus.ToimistoTietovarasto;

/**
 *
 * @author s1300778
 */
public final class ToimistoIkkuna extends Ikkuna {

    private ToimistoTietovarasto rekisteri = new ToimistoTietovarasto();

    public ToimistoIkkuna(String otsikko, String[] columnNames, int comboIndex) {
        super(otsikko, columnNames, comboIndex);
        haeKaikkiTiedot();

    }

    @Override
    public void suoritaMuutos() {
        values = syottopaneeli.getArvot();

        int id = Integer.parseInt(values[0]);
        int postinumero = Integer.parseInt(values[3]);
        rekisteri.muutaTietoja(new Toimisto(id, values[1], values[2], postinumero, values[4]));
        paivitaValintaLista();
    }

    @Override
    public void suoritaPoisto() {
        int id = (int) malli.getValueAt(taulukko.getSelectedRow(), 0);
        rekisteri.poistaTieto(id);
        paivitaValintaLista();
    }

    @Override
    public void suoritaLisays() {
        values = syottopaneeli.getArvot();
        try {
            int id = Integer.parseInt(values[0]);
            int puhelinnumero = Integer.parseInt(values[3]);
            rekisteri.lisaaTieto(new Toimisto(id, values[1], values[2], puhelinnumero, values[4]));
            syottopaneeli.tyhjennaKentat();
            paivitaValintaLista();

        } catch (NumberFormatException e) {
            virhe("ID:n ja ¨puhelinnumeron pitää olla kokonaislukuja");
        }
    }

    @Override
    public void haeKaikkiTiedot() {
        for (Toimisto toimisto : rekisteri.haeTiedot()) {
            malli.addRow(Arrays.asList(toimisto.getId(), toimisto.getAukioloajat(), toimisto.getKatuosoite(), toimisto.getPostinumero(), toimisto.getToimipaikka()));
        }
    }

}
