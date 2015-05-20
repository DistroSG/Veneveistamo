
package kayttoliittymapakkaus;

import datapakkaus.Arvostelu;
import java.util.Arrays;
import tietovarastopakkaus.ArvosteluTietovarasto;

/**
 *
 * @author s1300776
 */
public final class ArvosteluIkkuna extends Ikkuna {

    private final ArvosteluTietovarasto rekisteri = new ArvosteluTietovarasto();

    public ArvosteluIkkuna(String ikkunanNimi, String[] columnNames) {
        super(ikkunanNimi, columnNames);
         syottopaneeli.setEditoitavissa(4, false);
         syottopaneeli.setEditoitavissa(5, false);
        haeKaikkiTiedot();
    }
  
    @Override
    public void suoritaMuutos() {
        arvot = syottopaneeli.getArvot();

        int id = Integer.parseInt(arvot[0]);
        int asiakasID = Integer.parseInt(arvot[1]);
        rekisteri.muutaTietoja(new Arvostelu( id, asiakasID, arvot[2], arvot[3], arvot[4], arvot[5]));
        paivitaValintaLista();
    }

    @Override
    public void suoritaPoisto() {
        int id = (int) malli.getValueAt(taulukko.getSelectedRow(), 0);
        rekisteri.poistaArvostelu(id);
        paivitaValintaLista();
    }

    @Override
    public void suoritaLisays() {
        arvot = syottopaneeli.getArvot();
        try {
            int id = Integer.parseInt(arvot[0]);
            int asiakasID = Integer.parseInt(arvot[1]);
            rekisteri.lisaaArvostelu(new Arvostelu(id,asiakasID, arvot[2], arvot[3], arvot[4], arvot[5]));
            syottopaneeli.tyhjennaKentat();
            paivitaValintaLista();

        } catch (NumberFormatException e) {
            virhe("Numeroiden pitää olla kokonaislukuja");
        }
    }

@Override
    public void haeKaikkiTiedot() {
        for (Arvostelu arvostelu : rekisteri.haeKaikkArvostelut()) {
            malli.addRow(Arrays.asList( arvostelu.getId(), arvostelu.getAsiakasid(), arvostelu.getArvostelu(), arvostelu.getPikkuarvostelu(),arvostelu.getEtunimi(), arvostelu.getSukunimi()));
        }
    }





}
