package kayttoliittymapakkaus;

import datapakkaus.Toimisto;
import java.util.Arrays;
import tietovarastopakkaus.ToimistoTietovarasto;

/**
 * ToimistoIkkuna luokka. Jolla asennetaan Toimisto ikkuna.
 *
 * @author s1300778
 * @version 1.0
 */
public final class ToimistoIkkuna extends Ikkuna {

    private final ToimistoTietovarasto rekisteri = new ToimistoTietovarasto();

    /**
     * Luoda uusi HenkilostoHasTehtava ikkuna otsikon, sarakenimien ja
     * yhdistelmäIndeksen avulla
     *
     * @param ikkunanNimi ikunan nimi
     * @param sarakenimet taulokon sarakenimet
     */
    public ToimistoIkkuna(String ikkunanNimi, String[] sarakenimet) {
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
        rekisteri.muutaTietoja(new Toimisto(id, arvot[1], arvot[2], arvot[3], arvot[4]));
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
     * Suorita lisays
     */
    @Override
    public void suoritaLisays() {
        arvot = syottopaneeli.getArvot();
        try {
            int id = Integer.parseInt(arvot[0]);
            rekisteri.lisaaTieto(new Toimisto(id, arvot[1], arvot[2], arvot[3], arvot[4]));
            syottopaneeli.tyhjennaKentat();
            paivitaValintaLista();

        } catch (NumberFormatException e) {
            virhe("ID:n ja ¨puhelinnumeron pitää olla kokonaislukuja");
        }
    }

    /**
     * Hae kaikki tiedot
     */
    @Override
    public void haeKaikkiTiedot() {
        for (Toimisto toimisto : rekisteri.haeTiedot()) {
            malli.addRow(Arrays.asList(toimisto.getId(), toimisto.getAukioloajat(), toimisto.getKatuosoite(), toimisto.getPostinumero(), toimisto.getToimipaikka()));
        }
    }

}
