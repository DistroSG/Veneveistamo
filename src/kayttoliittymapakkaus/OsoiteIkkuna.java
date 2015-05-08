/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittymapakkaus;

import datapakkaus.Osoite;
import java.util.Arrays;
import tietovarastopakkaus.OsoiteTietovarasto;

/**
 *
 * @author s1300778
 */
public class OsoiteIkkuna extends Ikkuna {

    private OsoiteTietovarasto rekisteri = new OsoiteTietovarasto();

    public OsoiteIkkuna(String otsikko, String[] columnNames, int comboIndex) {
        super(otsikko, columnNames, comboIndex);
        haeKaikkiTiedot();
    }

    @Override
    public void suoritaMuutos() {
        arvot = syottopaneeli.getArvot();

        int id = Integer.parseInt(arvot[0]);
        int venetilausid = Integer.parseInt(arvot[5]);
        rekisteri.muutaTietoja(new Osoite(id, arvot[1], arvot[2], arvot[3],arvot[4], venetilausid));
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
        arvot = syottopaneeli.getArvot();
        try {
            int id = Integer.parseInt(arvot[0]);
            int venetilausid = Integer.parseInt(arvot[5]);
            rekisteri.lisaaTieto(new Osoite(id, arvot[1], arvot[2], arvot[3],arvot[4], venetilausid));
            syottopaneeli.tyhjennaKentat();
            paivitaValintaLista();

        } catch (NumberFormatException e) {
            virhe("ElokuvaNro:n ja vuoden pitää olla kokonaislukuja");
        }
    }

    @Override
    public void haeKaikkiTiedot() {
        for (Osoite osoite : rekisteri.haeTiedot()) {
            malli.addRow(Arrays.asList(osoite.getId(), osoite.getKatuosoite(), osoite.getPostinumero(), osoite.getToimipaikka(),osoite.getYrityksennimi(), osoite.getVenetilausid()));
        }
    }


}

