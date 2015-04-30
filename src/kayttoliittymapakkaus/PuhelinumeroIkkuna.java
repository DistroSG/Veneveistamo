/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittymapakkaus;

import datapakkaus.Puhelinnumero;
import java.util.Arrays;

/**
 *
 * @author s1300778
 */
public class PuhelinumeroIkkuna extends Ikkuna {

    public PuhelinumeroIkkuna(String otsikko, String[] columnNames, int comboIndex) {
        super(otsikko, columnNames, comboIndex);
    }

    @Override
    public void suoritaMuutos() {
        values = syottopaneeli.getArvot();

        int hid = Integer.parseInt(values[0]);
        int puhelinumero = Integer.parseInt(values[1]);
        int toimistoID = Integer.parseInt(values[2]);
        rekisteri.muutaPuhelinumeroTietoja(new Puhelinnumero(hid, puhelinumero, toimistoID));
        paivitaValintaLista();

    }

    @Override
    public void suoritaPoisto() {
        int i = (int) malli.getValueAt(taulukko.getSelectedRow(), 0);
        rekisteri.poistaPuhelinumero(i);
        paivitaValintaLista();
    }

    @Override
    public void suoritaLisays() {
        values = syottopaneeli.getArvot();
        try {
            int hid = Integer.parseInt(values[0]);
            int puhelinumero = Integer.parseInt(values[1]);
            int toimistoID = Integer.parseInt(values[2]);
            rekisteri.lisaaPuhelinnumero(new Puhelinnumero(hid, puhelinumero, toimistoID));
            syottopaneeli.tyhjennaKentat();
            paivitaValintaLista();

        } catch (NumberFormatException e) {
            virhe("ElokuvaNro:n ja vuoden pitää olla kokonaislukuja");
        }
    }

    @Override
    public void paivitaValintaLista() {
        int rowCount = malli.getRowCount();

        for (int i = rowCount - 1; i >= 0; i--) {
            malli.removeRow(i);

        }
        haeKaikkiTiedot();
    }

    @Override
    public void haeKaikkiTiedot() {
        for (Puhelinnumero puhelinumero : rekisteri.haePuhelinnumerot()) {
            malli.addRow(Arrays.asList(puhelinumero.getId(), puhelinumero.getPuhelinnumero(), puhelinumero.getToimistoID()));
        }
    }

}
