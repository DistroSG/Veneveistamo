package kayttoliittymapakkaus;

import datapakkaus.Puhelinnumero;
import java.util.Arrays;
import tietovarastopakkaus.PuhelinnumeroTietovarasto;

/**
 * PuhelinumeroIkkuna luokka. Jolla asennetaan Puhelinumero ikkuna.
 *
 * @author s1300778
 * @version 1.0
 */
public final class PuhelinumeroIkkuna extends Ikkuna {

    private final PuhelinnumeroTietovarasto rekisteri = new PuhelinnumeroTietovarasto();

    /**
     * Luoda uusi PuhelinumeroIkkuna ikkuna otsikon, sarakenimien ja
     * yhdistelmäIndeksen avulla
     *
     * @param ikkunanNimi ikunan nimi
     * @param sarakenimet taulokon sarakenimet
          */
    public PuhelinumeroIkkuna(String ikkunanNimi, String[] sarakenimet) {
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
        int puhelinumero = Integer.parseInt(arvot[1]);
        int toimistoID = Integer.parseInt(arvot[2]);
        rekisteri.muutaTietoja(new Puhelinnumero(id, puhelinumero, toimistoID));
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
            int puhelinnumero = Integer.parseInt(arvot[1]);
            int toimistoID = Integer.parseInt(arvot[2]);
            rekisteri.lisaaTieto(new Puhelinnumero(id, puhelinnumero, toimistoID));
            syottopaneeli.tyhjennaKentat();
            paivitaValintaLista();

        } catch (NumberFormatException e) {
            virhe("ID:n, puhelinnumeron ja toimistoID:n pitää olla kokonaislukuja");
        }
    }

    /**
     * Hae kaikki tiedot
     */
    @Override
    public void haeKaikkiTiedot() {
        for (Puhelinnumero puhelinumero : rekisteri.haeTiedot()) {
            malli.addRow(Arrays.asList(puhelinumero.getId(), puhelinumero.getPuhelinnumero(), puhelinumero.getToimistoID()));
        }
    }

}
