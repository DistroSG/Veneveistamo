/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kayttoliittymapakkaus;

import datapakkaus.Asiakas;
import java.util.Arrays;
import tietovarastopakkaus.AsiakasTietovarasto;

/**
 *
 * @author s1300778
 */
public class AsiakasIkkuna extends Ikkuna {

    private AsiakasTietovarasto rekisteri = new AsiakasTietovarasto();

    public AsiakasIkkuna(String otsikko, String[] columnNames, int comboIndex) {
        super(otsikko, columnNames, comboIndex);
        haeKaikkiTiedot();
    }

    @Override
    public void suoritaMuutos() {
        values = syottopaneeli.getArvot();

        int id = Integer.parseInt(values[0]);
        rekisteri.muutaTietoja(new Asiakas(id, values[1],values[2],values[3],values[4],values[5],values[6],values[7],values[8]));
        paivitaValintaLista();
    }

    @Override
    public void suoritaPoisto() {
        int id = (int) malli.getValueAt(taulukko.getSelectedRow(), 0);
        rekisteri.poistaAsiakas(id);
        paivitaValintaLista();
    }

    @Override
    public void suoritaLisays() {
        values = syottopaneeli.getArvot();
        try {
            int id = Integer.parseInt(values[0]);
            rekisteri.lisaaAsiakas(new Asiakas(id, values[1],values[2],values[3],values[4],values[5],values[6],values[7],values[8]));
            syottopaneeli.tyhjennaKentat();
            paivitaValintaLista();

        } catch (NumberFormatException e) {
            virhe("ElokuvaNro:n ja vuoden pitää olla kokonaislukuja");
        }
    }

    @Override
    public void haeKaikkiTiedot() {
        for (Asiakas asiakas : rekisteri.haeAsiakas()) {
            malli.addRow(Arrays.asList(asiakas.getId(), asiakas.getHenkilotunnus(), asiakas.getSalasana(), asiakas.getSukunimi(), asiakas.getEtunimi(), asiakas.getSahkoposti(), asiakas.getSukupuoli(), asiakas.getPuhelinnumero(), asiakas.getAsiakastyyppi()));
        }
    }

}

