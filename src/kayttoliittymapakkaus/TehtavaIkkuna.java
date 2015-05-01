/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittymapakkaus;

import datapakkaus.Tehtava;
import java.util.Arrays;
import tietovarastopakkaus.TehtavaTietovarasto;


/**
 *
 * @author s1300778
 */
public final class TehtavaIkkuna extends Ikkuna {
    
    private TehtavaTietovarasto rekisteri = new TehtavaTietovarasto();

    public TehtavaIkkuna(String otsikko, String[] columnNames, int comboIndex) {
        super(otsikko, columnNames, comboIndex);
        haeKaikkiTiedot();
    }

    @Override
    public void suoritaMuutos() {
        values = syottopaneeli.getArvot();

        int id = Integer.parseInt(values[0]);
        rekisteri.muutaTietoja(new Tehtava(id, values[1]));
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
            rekisteri.lisaaTieto(new Tehtava(id, values[1]));
            syottopaneeli.tyhjennaKentat();
            paivitaValintaLista();

        } catch (NumberFormatException e) {
            virhe("ElokuvaNro:n ja vuoden pitää olla kokonaislukuja");
        }
    }

    @Override
    public void haeKaikkiTiedot() {
        for (Tehtava tehtava : rekisteri.haeTiedot()) {
            malli.addRow(Arrays.asList(tehtava.getId(), tehtava.getTehtava()));
        }
    }

}
