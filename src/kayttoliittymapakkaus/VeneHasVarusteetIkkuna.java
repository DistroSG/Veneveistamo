package kayttoliittymapakkaus;

import datapakkaus.VeneHasVarusteet;
import datapakkaus.VeneHasVarusteetMuutos;
import java.util.Arrays;
import tietovarastopakkaus.VeneHasVarusteetTietovarasto;

//@author s1300748

public final class VeneHasVarusteetIkkuna extends Ikkuna {
        private final VeneHasVarusteetTietovarasto rekisteri = new VeneHasVarusteetTietovarasto();

 
    public VeneHasVarusteetIkkuna(String ikkunanNimi, String[] sarakenimet) {
        super(ikkunanNimi, sarakenimet);
        haeKaikkiTiedot();
    }

    public void haeKaikkiTiedot() {
        for (VeneHasVarusteet venehasvarusteet : rekisteri.haeTiedot()) {
            malli.addRow(Arrays.asList(venehasvarusteet.getVene_id(), venehasvarusteet.getErikoisvarusteet_id(), venehasvarusteet.getVakiovarusteet()));
        }
    }

    @Override
    public void suoritaMuutos() {
        arvot = syottopaneeli.getArvot();

        int vene_id = (int) malli.getValueAt(taulukko.getSelectedRow(), 0);
        int vanhaErikoisvarusteet_id = (int) malli.getValueAt(taulukko.getSelectedRow(), 1);
        int uusiErikoisvarusteet_id = Integer.parseInt(arvot[1]);
        boolean vakiovarusteet = Boolean.parseBoolean(arvot[2]);
        rekisteri.muutaTietoja(new VeneHasVarusteetMuutos(new VeneHasVarusteet(vene_id, uusiErikoisvarusteet_id, vakiovarusteet), new VeneHasVarusteet(vene_id, vanhaErikoisvarusteet_id, vakiovarusteet)));
        paivitaValintaLista();
    }

    @Override
    public void suoritaLisays() {
        arvot = syottopaneeli.getArvot();
        try {
            int vene_id = Integer.parseInt(arvot[0]);
            int erikoisvarusteet_id = Integer.parseInt(arvot[1]);
            boolean vakiovarusteet = Boolean.parseBoolean(arvot[2]);
            rekisteri.lisaaTieto(new VeneHasVarusteet(vene_id, erikoisvarusteet_id, vakiovarusteet));
            
            syottopaneeli.tyhjennaKentat();
            paivitaValintaLista();

        } catch (NumberFormatException e) {
            virhe("ID:n pitää olla kokonaislukuna");
        }
    }
    
    @Override
    public void suoritaPoisto() {
        int vene_id = (int) malli.getValueAt(taulukko.getSelectedRow(), 0);
        int erikoisvarusteet_id = (int) malli.getValueAt(taulukko.getSelectedRow(), 1);
        rekisteri.poistaTieto(vene_id, erikoisvarusteet_id);
        paivitaValintaLista();
    }

    

}
