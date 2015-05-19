package kayttoliittymapakkaus;

import datapakkaus.Vene;
import java.util.Arrays;
import tietovarastopakkaus.VeneTietovarasto;

/**
 * VeneIkkuna luokka. Jolla asennetaan Vene ikkuna.
 *
 * @author s1300723
 * @version 1.0
 */
public final class VeneIkkuna extends Ikkuna {

    private final VeneTietovarasto rekisteri = new VeneTietovarasto();

    /**
     * Luoda uusi VeneIkkuna ikkuna otsikon, sarakenimien ja
     * yhdistelmäIndeksen avulla
     *
     * @param ikkunanNimi ikunan otsikko
     * @param sarakenimet taulokon sarakenimet
     */
    public VeneIkkuna(String ikkunanNimi, String[] sarakenimet) {
        super(ikkunanNimi, sarakenimet);
        haeKaikkiTiedot();
        syottopaneeli.setEditoitavissa(2, false);
    }

    /**
     * Suorita muutos
     */
    @Override
    public void suoritaMuutos() {
        arvot = syottopaneeli.getArvot();

        int id = Integer.parseInt(arvot[0]);
        int malliID = Integer.parseInt(arvot[1]);
        int takuuID = Integer.parseInt(arvot[3]);
        int hinta = Integer.parseInt(arvot[4]);
        int alv = Integer.parseInt(arvot[5]);
        rekisteri.muutaTietoja(new Vene(id, malliID, arvot[2], takuuID, hinta, alv));
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
            int malliID = Integer.parseInt(arvot[1]);
            int takuuID = Integer.parseInt(arvot[3]);
            int hinta = Integer.parseInt(arvot[4]);
            int alv = Integer.parseInt(arvot[5]);
            rekisteri.lisaaTieto(new Vene(id, malliID, arvot[2], takuuID, hinta, alv));
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
            malli.addRow(Arrays.asList(vene.getId(), vene.getMalliID(),vene.getMalli(), vene.getTakuuID(), vene.getHinta(), vene.getAlv()));
        }
    }

}
