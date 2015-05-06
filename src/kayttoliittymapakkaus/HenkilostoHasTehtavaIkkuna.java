/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittymapakkaus;

import datapakkaus.HenkilostoHasTehtava;
import datapakkaus.HenkilostoHasTehtavaTupla;
import java.util.Arrays;
import tietovarastopakkaus.HenkilostoHasTehtavaTietovarasto;

/**
 *
 * @author s1300778
 */
public final class HenkilostoHasTehtavaIkkuna extends Ikkuna {

    private final HenkilostoHasTehtavaTietovarasto rekisteri = new HenkilostoHasTehtavaTietovarasto();

    public HenkilostoHasTehtavaIkkuna(String otsikko, String[] columnNames, int comboIndex) {
        super(otsikko, columnNames, comboIndex);
        haeKaikkiTiedot();
    }

    @Override
    public void suoritaMuutos() {
        values = syottopaneeli.getArvot();

        int henkilostoID = (int) malli.getValueAt(taulukko.getSelectedRow(), 0);
        int vanhaTehtavaID = (int) malli.getValueAt(taulukko.getSelectedRow(), 1);
        int uusiTehtavaID = Integer.parseInt(values[1]);

        rekisteri.muutaTietoja(new HenkilostoHasTehtavaTupla(new HenkilostoHasTehtava(henkilostoID, uusiTehtavaID), new HenkilostoHasTehtava(henkilostoID, vanhaTehtavaID)));
        paivitaValintaLista();
    }

    @Override
    public void suoritaPoisto() {
        int henkilostoID = (int) malli.getValueAt(taulukko.getSelectedRow(), 0);
        int tehtavaID = (int) malli.getValueAt(taulukko.getSelectedRow(), 1);
        rekisteri.poistaTieto(henkilostoID, tehtavaID);
        paivitaValintaLista();
    }

    @Override
    public void suoritaLisays() {
        values = syottopaneeli.getArvot();
        try {
            int henkilostoID = Integer.parseInt(values[0]);
            int tehtavaID = Integer.parseInt(values[1]);
            rekisteri.lisaaTieto(new HenkilostoHasTehtava(henkilostoID, tehtavaID));
            syottopaneeli.tyhjennaKentat();
            paivitaValintaLista();

        } catch (NumberFormatException e) {
            virhe("HenkilöID:n ja TehtäväID:n pitää olla kokonaislukuja");
        }
    }

    @Override
    public void haeKaikkiTiedot() {
        for (HenkilostoHasTehtava henkiloHasTehtava : rekisteri.haeTiedot()) {
            malli.addRow(Arrays.asList(henkiloHasTehtava.getHenkilostoID(), henkiloHasTehtava.getTehtavaID()));
        }
    }

}
