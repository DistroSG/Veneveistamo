package kayttoliittymapakkaus;

import datapakkaus.Tehtava;
import java.util.Arrays;
import tietovarastopakkaus.TehtavaTietovarasto;

/**
 * TehtavaIkkuna luokka. Jolla asennetaan Tehtava ikkuna.
 *
 * @author s1300778
 * @version 1.0
 */
public final class TehtavaIkkuna extends Ikkuna {

    private final TehtavaTietovarasto rekisteri = new TehtavaTietovarasto();

    /**
     * Luoda uusi TehtavaIkkuna ikkuna otsikon, sarakenimien ja
     * yhdistelmäIndeksen avulla
     *
     * @param ikkunanNimi ikunan otsikko
     * @param sarakenimet taulokon sarakenimet
     */
    public TehtavaIkkuna(String ikkunanNimi, String[] sarakenimet) {
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
        rekisteri.muutaTietoja(new Tehtava(id, arvot[1]));
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
            rekisteri.lisaaTieto(new Tehtava(id, arvot[1]));
            syottopaneeli.tyhjennaKentat();
            paivitaValintaLista();

        } catch (NumberFormatException e) {
            virhe("ID:n pitää olla kokonaislukuna");
        }
    }

    /**
     * Hae kaikki tiedot
     */
    @Override
    public void haeKaikkiTiedot() {
        for (Tehtava tehtava : rekisteri.haeTiedot()) {
            malli.addRow(Arrays.asList(tehtava.getId(), tehtava.getTehtava()));
        }
    }

}
