package kayttoliittymapakkaus;

import datapakkaus.VeneHasPerusvarit;
import datapakkaus.VeneHasPerusvaritMuutos;
import java.util.Arrays;
import tietovarastopakkaus.VeneHasPerusvaritTietovarasto;

/**
 * VeneHasMateriaaliIkkuna luokka. Jolla asennetaan VeneHasMateriaali
 * ikkuna.
 *
 * @author s1300723
 * @version 1.0
 */
public final class VeneHasPerusvaritIkkuna extends Ikkuna {

    private final VeneHasPerusvaritTietovarasto rekisteri = new VeneHasPerusvaritTietovarasto();

    /**
     * Luoda uusi VeneHasMateriaali ikkuna otsikon, sarakenimien ja
     * yhdistelmäIndeksen avulla
     *
     * @param ikkunanNimi ikunan nimi
     * @param sarakenimet taulokon sarakenimet
     */
    public VeneHasPerusvaritIkkuna(String ikkunanNimi, String[] sarakenimet) {
        super(ikkunanNimi, sarakenimet);
        haeKaikkiTiedot();
    }

    /**
     * Suorita muutos
     */
    @Override
    public void suoritaMuutos() {
        arvot = syottopaneeli.getArvot();

        int veneID = (int) malli.getValueAt(taulukko.getSelectedRow(), 0);
        int vanhaPerusvaritD = (int) malli.getValueAt(taulukko.getSelectedRow(), 1);
        int uusiPerusvaritD = Integer.parseInt(arvot[1]);

        rekisteri.muutaTietoja(new VeneHasPerusvaritMuutos(new VeneHasPerusvarit(veneID, uusiPerusvaritD), new VeneHasPerusvarit(veneID, vanhaPerusvaritD)));
        paivitaValintaLista();
    }

    /**
     * Suorita poisto
     */
    @Override
    public void suoritaPoisto() {
        int perusvaritID = (int) malli.getValueAt(taulukko.getSelectedRow(), 0);
        int veneID = (int) malli.getValueAt(taulukko.getSelectedRow(), 1);
        rekisteri.poistaTieto(veneID, perusvaritID);
        paivitaValintaLista();
    }

    /**
     * Suorita lisäys
     */
    @Override
    public void suoritaLisays() {
        arvot = syottopaneeli.getArvot();
        try {
            int perusvaritID = Integer.parseInt(arvot[0]);
            int veneID = Integer.parseInt(arvot[1]);
            rekisteri.lisaaTieto(new VeneHasPerusvarit(veneID, perusvaritID));
            syottopaneeli.tyhjennaKentat();
            paivitaValintaLista();

        } catch (NumberFormatException e) {
            virhe("perusvaritID:n ja VeneID:n pitää olla kokonaislukuja");
        }
    }

    /**
     * Hae kaikki tiedot
     */
    @Override
    public void haeKaikkiTiedot() {
        for (VeneHasPerusvarit VeneHasPerusvarit : rekisteri.haeTiedot()) {
            malli.addRow(Arrays.asList(VeneHasPerusvarit.getVeneID(), VeneHasPerusvarit.getPerusvaritID()));
        }
    }

}
