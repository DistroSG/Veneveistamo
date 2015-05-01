/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittymapakkaus;

import datapakkaus.Puhelinnumero;
import java.util.Arrays;
import tietovarastopakkaus.PuhelinnumeroTietovarasto;

/**
 *
 * @author s1300778
 */
public final class PuhelinumeroIkkuna extends Ikkuna {

    private PuhelinnumeroTietovarasto rekisteri = new PuhelinnumeroTietovarasto();

    public PuhelinumeroIkkuna(String otsikko, String[] columnNames, int comboIndex) {
        super(otsikko, columnNames, comboIndex);
        haeKaikkiTiedot();
    }

    @Override
    public void suoritaMuutos() {
        values = syottopaneeli.getArvot();

        int id = Integer.parseInt(values[0]);
        int puhelinumero = Integer.parseInt(values[1]);
        int toimistoID = Integer.parseInt(values[2]);
        rekisteri.muutaTietoja(new Puhelinnumero(id, puhelinumero, toimistoID));
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
            int puhelinumero = Integer.parseInt(values[1]);
            int toimistoID = Integer.parseInt(values[2]);
            rekisteri.lisaaTieto(new Puhelinnumero(id, puhelinumero, toimistoID));
            syottopaneeli.tyhjennaKentat();
            paivitaValintaLista();

        } catch (NumberFormatException e) {
            virhe("ElokuvaNro:n ja vuoden pitää olla kokonaislukuja");
        }
    }

    @Override
    public void haeKaikkiTiedot() {
        for (Puhelinnumero puhelinumero : rekisteri.haeTiedot()) {
            malli.addRow(Arrays.asList(puhelinumero.getId(), puhelinumero.getPuhelinnumero(), puhelinumero.getToimistoID()));
        }
    }

}
