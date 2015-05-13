package kayttoliittymapakkaus;

import datapakkaus.VeneHasMateriaali;
import datapakkaus.VeneHasMateriaaliMuutos;
import java.util.Arrays;
import tietovarastopakkaus.VeneHasMateriaaliTietovarasto;

/**
 * VeneHasMateriaaliIkkuna luokka. Jolla asennetaan VeneHasMateriaali
 * ikkuna.
 *
 * @author s1300723
 * @version 1.0
 */
public final class VeneHasMateriaaliIkkuna extends Ikkuna {

    private final VeneHasMateriaaliTietovarasto rekisteri = new VeneHasMateriaaliTietovarasto();

    /**
     * Luoda uusi VeneHasMateriaali ikkuna otsikon, sarakenimien ja
     * yhdistelmäIndeksen avulla
     *
     * @param ikkunanNimi ikunan nimi
     * @param sarakenimet taulokon sarakenimet
     */
    public VeneHasMateriaaliIkkuna(String ikkunanNimi, String[] sarakenimet) {
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
        int vanhaMateriaaliID = (int) malli.getValueAt(taulukko.getSelectedRow(), 1);
        int uusiMateriaaliID = Integer.parseInt(arvot[1]);

        rekisteri.muutaTietoja(new VeneHasMateriaaliMuutos(new VeneHasMateriaali(veneID, uusiMateriaaliID), new VeneHasMateriaali(veneID, vanhaMateriaaliID)));
        paivitaValintaLista();
    }

    /**
     * Suorita poisto
     */
    @Override
    public void suoritaPoisto() {
        int materiaaliID = (int) malli.getValueAt(taulukko.getSelectedRow(), 0);
        int veneID = (int) malli.getValueAt(taulukko.getSelectedRow(), 1);
        rekisteri.poistaTieto(veneID, materiaaliID);
        paivitaValintaLista();
    }

    /**
     * Suorita lisäys
     */
    @Override
    public void suoritaLisays() {
        arvot = syottopaneeli.getArvot();
        try {
            int materiaaliID = Integer.parseInt(arvot[0]);
            int veneID = Integer.parseInt(arvot[1]);
            rekisteri.lisaaTieto(new VeneHasMateriaali(veneID, materiaaliID));
            syottopaneeli.tyhjennaKentat();
            paivitaValintaLista();

        } catch (NumberFormatException e) {
            virhe("MateriaaliID:n ja VeneID:n pitää olla kokonaislukuja");
        }
    }

    /**
     * Hae kaikki tiedot
     */
    @Override
    public void haeKaikkiTiedot() {
        for (VeneHasMateriaali veneHasMateriaali : rekisteri.haeTiedot()) {
            malli.addRow(Arrays.asList(veneHasMateriaali.getVeneID(), veneHasMateriaali.getMateriaaliID()));
        }
    }

}
