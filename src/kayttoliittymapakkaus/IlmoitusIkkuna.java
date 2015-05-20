/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittymapakkaus;

import datapakkaus.Ilmoitus;
import java.util.Arrays;
import tietovarastopakkaus.IlmoitusTietovarasto;

/**
 *
 * @author s1300727
 */
public class IlmoitusIkkuna extends Ikkuna{

        private final IlmoitusTietovarasto rekisteri = new IlmoitusTietovarasto();

    
    public IlmoitusIkkuna(String ikkunanNimi, String[] sarakenimet) {
        super(ikkunanNimi, sarakenimet);
        haeKaikkiTiedot();
    }

    @Override
    public void suoritaLisays() {
                arvot = syottopaneeli.getArvot();
        try {
            int id = Integer.parseInt(arvot[0]);
            double hinta = Double.parseDouble(arvot[2]);
            int veneTilaus_id = Integer.parseInt(arvot[3]);
            
            rekisteri.lisaaTieto(new Ilmoitus(id, arvot[1], hinta, veneTilaus_id));
            syottopaneeli.tyhjennaKentat();
            paivitaValintaLista();

        } catch (NumberFormatException e) {
            virhe("ID/veneTilasus ID:n täytyy olla kokonaislukuja!");
        }
    }

    @Override
    public void suoritaMuutos() {
                arvot = syottopaneeli.getArvot();

            int id = Integer.parseInt(arvot[0]);
            int veneTilaus_id = Integer.parseInt(arvot[3]);
            double hinta = Double.parseDouble(arvot[2]);
            rekisteri.lisaaTieto(new Ilmoitus(id, arvot[1], hinta, veneTilaus_id));
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
        for (Ilmoitus ilmoitus : rekisteri.haeTiedot()) {
            malli.addRow(Arrays.asList(ilmoitus.getId(), ilmoitus.getIlmoitus(), ilmoitus.getHinta(), ilmoitus.getVenetilaus_id()));
        }    }
    
}
