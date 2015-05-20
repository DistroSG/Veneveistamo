package kayttoliittymapakkaus;

import datapakkaus.Henkilosto;
import java.util.Arrays;
import tietovarastopakkaus.HenkilostoTietovarasto;

/**
 * HenkilostoIkkuna luokka. Jolla asennetaan Henkilosto ikkuna.
 *
 * @author s1300778
 * @version 1.0
 */
public final class HenkilostoIkkuna extends Ikkuna {

    private final HenkilostoTietovarasto rekisteri = new HenkilostoTietovarasto();

    /**
     * Luoda uusi HenkilostoIkkuna ikkuna otsikon, sarakenimien ja
     * yhdistelmäIndeksen avulla
     *
     * @param ikkunanNimi ikunan otsikko
     * @param sarakenimet taulokon sarakenimet
     */
    public HenkilostoIkkuna(String ikkunanNimi, String[] sarakenimet) {
        super(ikkunanNimi, sarakenimet);
        syottopaneeli.setEditoitavissa(5, false);
        haeKaikkiTiedot();
    }

    /**
     * Suorita muutos
     */
    @Override
    public void suoritaMuutos() {
        arvot = syottopaneeli.getArvot();

        int id = Integer.parseInt(arvot[0]);
        int toimistoID = Integer.parseInt(arvot[4]);
        rekisteri.muutaTietoja(new Henkilosto(id, arvot[1], arvot[2], arvot[3], toimistoID, arvot[5]));
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
            int toimistoID = Integer.parseInt(arvot[4]);
            rekisteri.lisaaTieto(new Henkilosto(id, arvot[1], arvot[2], arvot[3], toimistoID, arvot[5]));
            syottopaneeli.tyhjennaKentat();
            paivitaValintaLista();

        } catch (NumberFormatException e) {
            virhe("ID:n ja toimiston ID:n pitää olla kokonaislukuja");
        }
    }

    /**
     * Hae kaikki tiedot
     */
    @Override
    public void haeKaikkiTiedot() {
        for (Henkilosto henkilo : rekisteri.haeTiedot()) {
            malli.addRow(Arrays.asList(henkilo.getId(), henkilo.getSukunimi(), henkilo.getEtunimi(), henkilo.getOsasto(), henkilo.getToimistoID(), henkilo.getToimistoKatuosoite()));
        }
    }

}
