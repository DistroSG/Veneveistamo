/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittymapakkaus;

import datapakkaus.Henkilosto;
import java.util.Arrays;
import tietovarastopakkaus.HenkilostoTietovarasto;

/**
 *
 * @author s1300778
 */
public final class HenkilostoIkkuna extends Ikkuna {

    private HenkilostoTietovarasto rekisteri = new HenkilostoTietovarasto();

    public HenkilostoIkkuna(String otsikko, String[] columnNames, int comboIndex) {
        super(otsikko, columnNames, comboIndex);
        haeKaikkiTiedot();
    }

    @Override
    public void suoritaMuutos() {
        values = syottopaneeli.getArvot();

        int id = Integer.parseInt(values[0]);
        int toimistoID = Integer.parseInt(values[4]);
        rekisteri.muutaTietoja(new Henkilosto(id, values[1], values[2], values[3], toimistoID));
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
            int toimistoID = Integer.parseInt(values[4]);
            rekisteri.lisaaTieto(new Henkilosto(id, values[1], values[2], values[3], toimistoID));
            syottopaneeli.tyhjennaKentat();
            paivitaValintaLista();

        } catch (NumberFormatException e) {
            virhe("ElokuvaNro:n ja vuoden pitää olla kokonaislukuja");
        }
    }

    @Override
    public void paivitaValintaLista() {
        int rowCount = malli.getRowCount();

        for (int id = rowCount - 1; id >= 0; id--) {
            malli.removeRow(id);

        }
        haeKaikkiTiedot();
    }

    @Override
    public void haeKaikkiTiedot() {
        for (Henkilosto henkilo : rekisteri.haeTiedot()) {
            malli.addRow(Arrays.asList(henkilo.getId(), henkilo.getSukunimi(), henkilo.getEtunimi(), henkilo.getOsasto(), henkilo.getToimistoID()));
        }
    }

}
