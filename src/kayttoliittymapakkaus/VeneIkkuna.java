package kayttoliittymapakkaus;

import datapakkaus.Vene;
import java.util.Arrays;
import tietovarastopakkaus.VeneTietovarasto;

/**
 * HenkilostoIkkuna luokka. Jolla asennetaan Henkilosto ikkuna.
 *
 * @author s1300778
 * @version 1.0
 */
public final class VeneIkkuna extends Ikkuna {

    private final VeneTietovarasto rekisteri = new VeneTietovarasto();

    /**
     * Luoda uusi HenkilostoIkkuna ikkuna otsikon, sarakenimien ja
     * yhdistelmäIndeksen avulla
     *
     * @param ikkunanNimi ikunan otsikko
     * @param sarakenimet taulokon sarakenimet
     */
    public VeneIkkuna(String ikkunanNimi, String[] sarakenimet) {
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
        int malli = Integer.parseInt(arvot[1]);
        int takuu = Integer.parseInt(arvot[2]);
        int hinta = Integer.parseInt(arvot[5]);
        int alv = Integer.parseInt(arvot[4]);
        rekisteri.muutaTietoja(new Vene(id, malli, takuu, hinta, alv));
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
            int malli = Integer.parseInt(arvot[1]);
            int takuu = Integer.parseInt(arvot[2]);
            int hinta = Integer.parseInt(arvot[5]);
            int alv = Integer.parseInt(arvot[4]);
            rekisteri.lisaaTieto(new Vene(id, malli, takuu, hinta, alv));
            syottopaneeli.tyhjennaKentat();
            paivitaValintaLista();

        } catch (NumberFormatException e) {
            virhe("ID:n, mallin, takuun, hinnan ja alv:n pitää olla kokonaislukuja");
        }
    }

    /**
     * Hae kaikki tiedot
     */
    @Override
    public void haeKaikkiTiedot() {
        for (Vene vene : rekisteri.haeTiedot()) {
            malli.addRow(Arrays.asList(vene.getId(), vene.getMalli(), vene.getTakuu(), vene.getHinta(), vene.getAlv()));
        }
    }

}
