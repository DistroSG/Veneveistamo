package kayttoliittymapakkaus;

import datapakkaus.AsiakasHasVeneTilaus;
import datapakkaus.AsiakasHasVeneTilausMuutos;
import java.util.Arrays;
import tietovarastopakkaus.AsiakasHasVeneTilausTietovarasto;

/**
 * HenkilostoHasTehtavaIkkuna luokka. Jolla asennetaan HenkilostoHasTehtava
 * ikkuna.
 *
 * @author s1300776
 * @version 1.0
 */
public final class AsiakasHasVeneTilausIkkuna extends Ikkuna {

    private final AsiakasHasVeneTilausTietovarasto rekisteri = new AsiakasHasVeneTilausTietovarasto();

    /**
     * Luoda uusi HenkilostoHasTehtava ikkuna otsikon, sarakenimien ja
     * yhdistelmäIndeksen avulla
     *
     * @param otsikko ikunan otsikko
     * @param sarakenimet taulokon sarakenimet
     * @param yhdistelmäIndeksi ikkunan numero yhdistelmässä
     */
    public AsiakasHasVeneTilausIkkuna(String ikkunanNimi, String[] sarakenimet) {
        super(ikkunanNimi, sarakenimet);
        haeKaikkiTiedot();
    }


    /**
     * Suorita muutos
     */
    @Override
    public void suoritaMuutos() {
        arvot = syottopaneeli.getArvot();

        int asiakasID = (int) malli.getValueAt(taulukko.getSelectedRow(), 0);
        int vanhaTilausID = (int) malli.getValueAt(taulukko.getSelectedRow(), 1);
        int uusiTilausID = Integer.parseInt(arvot[1]);

        rekisteri.muutaTietoja(new AsiakasHasVeneTilausMuutos(new AsiakasHasVeneTilaus(asiakasID, uusiTilausID), new AsiakasHasVeneTilaus(asiakasID, vanhaTilausID)));
        paivitaValintaLista();
    }

    /**
     * Suorita poisto
     */
    @Override
    public void suoritaPoisto() {
        int asiakasID = (int) malli.getValueAt(taulukko.getSelectedRow(), 0);
        int venetilausID = (int) malli.getValueAt(taulukko.getSelectedRow(), 1);
        rekisteri.poistaTieto(asiakasID, venetilausID);
        paivitaValintaLista();
    }

    /**
     * Suorita lisäys
     */
    @Override
    public void suoritaLisays() {
        arvot = syottopaneeli.getArvot();
        try {
            int asiakasID = Integer.parseInt(arvot[0]);
            int venetilausID = Integer.parseInt(arvot[1]);
            rekisteri.lisaaTieto(new AsiakasHasVeneTilaus(asiakasID, venetilausID));
            syottopaneeli.tyhjennaKentat();
            paivitaValintaLista();

        } catch (NumberFormatException e) {
            virhe("AsiakasID:n ja VeneTilausID:n pitää olla kokonaislukuja");
        }
    }

    /**
     * Hae kaikki tiedot
     */
    @Override
    public void haeKaikkiTiedot() {
        for (AsiakasHasVeneTilaus haeAsiakasHasVeneTilaus : rekisteri.haeTiedot()) {
            malli.addRow(Arrays.asList(haeAsiakasHasVeneTilaus.getAsiakasID(), haeAsiakasHasVeneTilaus.getVeneTilausID()));
        }
    }

}
