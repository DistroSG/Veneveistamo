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
 * MateriaaliIkkuna luokka. Jolla asennetaan Materiaali
 * ikkuna.
 *
 * @author s1300723
 * @version 1.0
 */
public final class MateriaaliIkkuna extends Ikkuna {
    
    private final MateriaaliTietovarasto rekisteri = new MateriaaliTietovarasto();
    /**
     * Luoda uusi Materiaali ikkuna otsikon, sarakenimien ja
     * yhdistelmäIndeksen avulla
     *
     * @param ikkunanNimi ikunan nimi
     * @param sarakenimet taulokon sarakenimet
     */
    public MateriaaliIkkuna(String ikkunanNimi, String[] sarakenimet) {
        super(ikkunanNimi, sarakenimet);
        haeKaikkiTiedot();
    }
    /**
     * Suorita muutos
     */
    @Override
    public void suoritaMuutos() {
        arvot = syottopaneeli.getArvot();

        int id = Integer.parseInt(arvot[0]);
        rekisteri.muutaTietoja(new Materiaali(arvot[1], id));
        paivitaValintaLista();
    }

    /**
     * Suorita poisto
     */
    @Override
    public void suoritaPoisto() {
        int id = (int) malli.getValueAt(taulukko.getSelectedRow(), 0);
        rekisteri.poistaTieto(id);
        paivitaValintaLista();
    }
    /**
     * Suorita lisäys
     */
    @Override
    public void suoritaLisays() {
        arvot = syottopaneeli.getArvot();
        try {
            int id = Integer.parseInt(arvot[0]);
            rekisteri.lisaaTieto(new Materiaali(arvot[1], id));
            syottopaneeli.tyhjennaKentat();
            paivitaValintaLista();

        } catch (NumberFormatException e) {
            virhe("ID:n pitää olla kokonaisluku");
        }
    }

    /**
     * Hae kaikki tiedot
     */
    @Override
    public void haeKaikkiTiedot() {
        for (Materiaali materiaali : rekisteri.haeTiedot()) {
            malli.addRow(Arrays.asList(materiaali.getId(), materiaali.getMateriaali()));
        }
    }

}
