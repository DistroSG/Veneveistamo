/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittymapakkaus;

import datapakkaus.Kuljetus;
import java.util.Arrays;
import tietovarastopakkaus.KuljetusTietovarasto;

/**
 *
 * @author s1300727
 */
public class KuljetusIkkuna extends Ikkuna{
    
        private KuljetusTietovarasto rekisteri = new KuljetusTietovarasto();


    public KuljetusIkkuna(String otsikko, String[] sarakenimet, int yhdistelmäIndeksi) {
        super(otsikko, sarakenimet, yhdistelmäIndeksi);
        haeKaikkiTiedot();
    }

    @Override
    public void suoritaLisays() {        arvot = syottopaneeli.getArvot();
        try {
            int id = Integer.parseInt(arvot[0]);
            rekisteri.lisaaTieto(new Kuljetus(id, arvot[1], arvot[2]));
            syottopaneeli.tyhjennaKentat();
            paivitaValintaLista();

        } catch (NumberFormatException e) {
            virhe(" eheheh virhe");
        }    }

    @Override
    public void suoritaMuutos() {
        arvot = syottopaneeli.getArvot();

        int id = Integer.parseInt(arvot[0]);
        rekisteri.muutaTietoja(new Kuljetus(id,arvot[1],arvot[2]));
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
        for (Kuljetus kuljetus : rekisteri.haeTiedot()) {
            malli.addRow(Arrays.asList(kuljetus.getId(), kuljetus.getVastaanottaja(), kuljetus.getVastaanotto()));
        }
    }
    
}
