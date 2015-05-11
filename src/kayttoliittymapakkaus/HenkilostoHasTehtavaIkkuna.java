package kayttoliittymapakkaus;

import datapakkaus.HenkilostoHasTehtava;
import datapakkaus.HenkilostoHasTehtavaMuutos;
import java.util.Arrays;
import tietovarastopakkaus.HenkilostoHasTehtavaTietovarasto;

/**
 * HenkilostoHasTehtavaIkkuna luokka. Jolla asennetaan HenkilostoHasTehtava
 * ikkuna.
 *
 * @author s1300778
 * @version 1.0
 */
public final class HenkilostoHasTehtavaIkkuna extends Ikkuna {

    private final HenkilostoHasTehtavaTietovarasto rekisteri = new HenkilostoHasTehtavaTietovarasto();

    /**
     * Luoda uusi HenkilostoHasTehtava ikkuna otsikon, sarakenimien ja
     * yhdistelmäIndeksen avulla
     *
     * @param ikkunanNimi ikunan nimi
     * @param sarakenimet taulokon sarakenimet
     */
    public HenkilostoHasTehtavaIkkuna(String ikkunanNimi, String[] sarakenimet) {
        super(ikkunanNimi, sarakenimet);
        haeKaikkiTiedot();
    }

    /**
     * Suorita muutos
     */
    @Override
    public void suoritaMuutos() {
        arvot = syottopaneeli.getArvot();

        int henkilostoID = (int) malli.getValueAt(taulukko.getSelectedRow(), 0);
        int vanhaTehtavaID = (int) malli.getValueAt(taulukko.getSelectedRow(), 1);
        int uusiTehtavaID = Integer.parseInt(arvot[1]);

        rekisteri.muutaTietoja(new HenkilostoHasTehtavaMuutos(new HenkilostoHasTehtava(henkilostoID, uusiTehtavaID), new HenkilostoHasTehtava(henkilostoID, vanhaTehtavaID)));
        paivitaValintaLista();
    }

    /**
     * Suorita poisto
     */
    @Override
    public void suoritaPoisto() {
        int henkilostoID = (int) malli.getValueAt(taulukko.getSelectedRow(), 0);
        int tehtavaID = (int) malli.getValueAt(taulukko.getSelectedRow(), 1);
        rekisteri.poistaTieto(henkilostoID, tehtavaID);
        paivitaValintaLista();
    }

    /**
     * Suorita lisäys
     */
    @Override
    public void suoritaLisays() {
        arvot = syottopaneeli.getArvot();
        try {
            int henkilostoID = Integer.parseInt(arvot[0]);
            int tehtavaID = Integer.parseInt(arvot[1]);
            rekisteri.lisaaTieto(new HenkilostoHasTehtava(henkilostoID, tehtavaID));
            syottopaneeli.tyhjennaKentat();
            paivitaValintaLista();

        } catch (NumberFormatException e) {
            virhe("HenkilöID:n ja TehtäväID:n pitää olla kokonaislukuja");
        }
    }

    /**
     * Hae kaikki tiedot
     */
    @Override
    public void haeKaikkiTiedot() {
        for (HenkilostoHasTehtava henkiloHasTehtava : rekisteri.haeTiedot()) {
            malli.addRow(Arrays.asList(henkiloHasTehtava.getHenkilostoID(), henkiloHasTehtava.getTehtavaID()));
        }
    }

}
