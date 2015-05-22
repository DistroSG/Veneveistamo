package kayttoliittymapakkaus;

import datapakkaus.Varusteet;
import java.util.Arrays;
import tietovarastopakkaus.VarusteetTietovarasto;

//@author s1300748

public final class VarusteetIkkuna extends Ikkuna {
        private final VarusteetTietovarasto rekisteri = new VarusteetTietovarasto();

 
    public VarusteetIkkuna(String ikkunanNimi, String[] sarakenimet) {
        super(ikkunanNimi, sarakenimet);
        haeKaikkiTiedot();
    }

    public void haeKaikkiTiedot() {
        for (Varusteet varusteet : rekisteri.haeTiedot()) {
            malli.addRow(Arrays.asList(varusteet.getId(), varusteet.getVarusteet(), varusteet.getKuvaus(), varusteet.getTakuu_id(), varusteet.getHinta(), varusteet.getAlv()));
        }
    }

    @Override
    public void suoritaMuutos() {
        arvot = syottopaneeli.getArvot();

        int id = Integer.parseInt(arvot[0]);
        int takuu_id = Integer.parseInt(arvot[3]);
        double hinta = Double.parseDouble(arvot[4]);
        int alv = Integer.parseInt(arvot[5]);
        rekisteri.muutaTietoja(new Varusteet(id, arvot[1], arvot[2], takuu_id, hinta, alv));
        paivitaValintaLista();
    }

    @Override
    public void suoritaLisays() {
        arvot = syottopaneeli.getArvot();
        try {
            int id = Integer.parseInt(arvot[0]);
            int takuu_id = Integer.parseInt(arvot[3]);
            double hinta = Double.parseDouble(arvot[4]);
            int alv = Integer.parseInt(arvot[5]);
            rekisteri.lisaaTieto(new Varusteet(id, arvot[1], arvot[2], takuu_id, hinta, alv));
            
            syottopaneeli.tyhjennaKentat();
            paivitaValintaLista();

        } catch (NumberFormatException e) {
            virhe("ID:n pitää olla kokonaislukuna");
        }
    }
    
    @Override
    public void suoritaPoisto() {
        int id = (int) malli.getValueAt(taulukko.getSelectedRow(), 0);
        rekisteri.poistaTieto(id);
        paivitaValintaLista();
    }

    

}
