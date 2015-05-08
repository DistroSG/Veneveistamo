/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittymapakkaus;

import datapakkaus.Materiaali;
import java.util.Arrays;
import tietovarastopakkaus.MateriaaliTietovarasto;


/**
 *
 * @author s1300778
 */
public final class MateriaaliIkkuna extends Ikkuna {
    
    private MateriaaliTietovarasto rekisteri = new MateriaaliTietovarasto();

    public MateriaaliIkkuna(String otsikko, String[] columnNames, int comboIndex) {
        super(otsikko, columnNames, comboIndex);
        haeKaikkiTiedot();
    }

    @Override
    public void suoritaMuutos() {
        arvot = syottopaneeli.getArvot();

        int id = Integer.parseInt(arvot[0]);
        rekisteri.muutaTietoja(new Materiaali(arvot[1], id));
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
            rekisteri.lisaaTieto(new Materiaali(arvot[1], id));
            syottopaneeli.tyhjennaKentat();
            paivitaValintaLista();

        } catch (NumberFormatException e) {
            virhe("ElokuvaNro:n ja vuoden pitää olla kokonaislukuja");
        }
    }

    @Override
    public void haeKaikkiTiedot() {
        for (Materiaali materiaali : rekisteri.haeTiedot()) {
            malli.addRow(Arrays.asList(materiaali.getId(), materiaali.getMateriaali()));
        }
    }

}
