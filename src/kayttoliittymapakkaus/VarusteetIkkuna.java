package kayttoliittymapakkaus;

import datapakkaus.Varusteet;
import java.util.Arrays;
import tietovarastopakkaus.VarusteetTietovarasto;

//@author s1300748

public class VarusteetIkkuna extends Ikkuna {
        private final VarusteetTietovarasto rekisteri = new VarusteetTietovarasto();

 
    public VarusteetIkkuna(String ikkunanNimi, String[] sarakenimet) {
        super(ikkunanNimi, sarakenimet);
        haeKaikkiTiedot();
    }

    public void haeVarusteet() {
        for (Varusteet varusteet : rekisteri.haeVarusteet()) {
            malli.addRow(Arrays.asList(varusteet.getId(), varusteet.getKuvaus(), varusteet.getKuva(), varusteet.getTakuu_id(), varusteet.getHinta(), varusteet.getAlv()));
        }
    }

    @Override
    public void suoritaMuutos() {
        arvot = syottopaneeli.getArvot();

        int id = Integer.parseInt(arvot[0]);
        int takuu_id = Integer.parseInt(arvot[3]);
        double hinta = Double.parseDouble(arvot[4]);
        double alv = Double.parseDouble(arvot[5]);
        rekisteri.muutaTietoja(new Varusteet(id, arvot[1], arvot[2], arvot[3], takuu_id, hinta, alv));
        paivitaValintaLista();
    }

    @Override
    public void suoritaLisays() {
        arvot = syottopaneeli.getArvot();
        try {
            int id = Integer.parseInt(arvot[0]);
            int takuu_id = Integer.parseInt(arvot[3]);
            double hinta = Integer.parseInt(arvot[4]);
            double alv = Integer.parseInt(arvot[5]);
            rekisteri.lisaaTieto(new Varusteet(id, arvot[1], arvot[2], arvot[3], takuu_id, hinta, alv));
            
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

    @Override
    public void haeKaikkiTiedot() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
