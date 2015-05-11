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
public final class AsiakasIkkuna extends Ikkuna {

    private final AsiakasTietovarasto rekisteri = new AsiakasTietovarasto();

    public AsiakasIkkuna(String ikkunanNimi, String[] columnNames) {
        super(ikkunanNimi, columnNames);
        haeKaikkiTiedot();
    }

    @Override
    public void suoritaMuutos() {
        arvot = syottopaneeli.getArvot();

        int id = Integer.parseInt(arvot[0]);
        rekisteri.muutaTietoja(new Asiakas(id, arvot[1],arvot[2],arvot[3],arvot[4],arvot[5],arvot[6],arvot[7],arvot[8]));
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
        arvot = syottopaneeli.getArvot();
        try {
            int id = Integer.parseInt(arvot[0]);
            rekisteri.lisaaAsiakas(new Asiakas(id, arvot[1],arvot[2],arvot[3],arvot[4],arvot[5],arvot[6],arvot[7],arvot[8]));
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

