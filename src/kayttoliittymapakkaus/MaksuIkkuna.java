/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittymapakkaus;

import tietovarastopakkaus.MaksuTietovarasto;

/**
 *
 * @author s1300727
 */
public class MaksuIkkuna extends Ikkuna{

        private MaksuTietovarasto rekisteri = new MaksuTietovarasto();

    public MaksuIkkuna(String otsikko, String[] columnNames, int comboIndex) {
        super(otsikko, columnNames, comboIndex);
        haeKaikkiTiedot();
    }

    
    @Override
    public void suoritaLisays() {
        
    }

    @Override
    public void suoritaMuutos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void suoritaPoisto() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void haeKaikkiTiedot() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
