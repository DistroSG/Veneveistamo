/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittymapakkaus;

import datapakkaus.Arvostelu;
import java.util.Arrays;
import tietovarastopakkaus.ArvosteluTietovarasto;

/**
 *
 * @author s1300778
 */
public class ArvosteluIkkuna extends Ikkuna {

    private ArvosteluTietovarasto rekisteri = new ArvosteluTietovarasto();

    public ArvosteluIkkuna(String otsikko, String[] columnNames, int comboIndex) {
        super(otsikko, columnNames, comboIndex);
        haeKaikkiTiedot();
    }
  
    @Override
    public void suoritaMuutos() {
        arvot = syottopaneeli.getArvot();

        int id = Integer.parseInt(arvot[0]);
        int asiakasID = Integer.parseInt(arvot[1]);
        rekisteri.muutaTietoja(new Arvostelu(id,asiakasID, arvot[2], arvot[3]));
        paivitaValintaLista();
    }

    @Override
    public void suoritaPoisto() {
        int id = (int) malli.getValueAt(taulukko.getSelectedRow(), 0);
        rekisteri.poistaArvostelu(id);
        paivitaValintaLista();
    }

    @Override
    public void suoritaLisays() {
        arvot = syottopaneeli.getArvot();
        try {
            int id = Integer.parseInt(arvot[0]);
            int asiakasID = Integer.parseInt(arvot[1]);
            rekisteri.lisaaArvostelu(new Arvostelu(id,asiakasID, arvot[2], arvot[3]));
            syottopaneeli.tyhjennaKentat();
            paivitaValintaLista();

        } catch (NumberFormatException e) {
            virhe("ElokuvaNro:n ja vuoden pitää olla kokonaislukuja");
        }
    }
@Override
    public void haeKaikkiTiedot() {
        for (Arvostelu arvostelu : rekisteri.haeKaikkArvostelut()) {
            malli.addRow(Arrays.asList(arvostelu.getId(), arvostelu.getAsiakasid(), arvostelu.getArvostelu(), arvostelu.getPikkuarvostelu()));
        }
    }





}
