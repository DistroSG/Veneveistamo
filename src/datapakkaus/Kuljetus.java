/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datapakkaus;

import java.util.Objects;

/**
 *
 * @author s1300727
 */
public class Kuljetus {
    
    private final int id;
    private final String vastaanottaja;
    private final String vastaanotto;
    
    /**
     * Luo uuden tieton ID, vastaanottaja ja vastaanotto.
     * 
     * @param id Kuljetuksen ID esim. 1
     * @param vastaanottaja Kuljetuksen vastaan ottaja esim. Pekka
     * @param vastaanotto Kuljetuksen vastaanotto esim. 10-01-2015.
     */

    public Kuljetus(int id, String vastaanottaja, String vastaanotto) {
        this.id = id;
        this.vastaanottaja = vastaanottaja;
        this.vastaanotto = vastaanotto;
    }
/**
 * Palauttaa ID arvon
 * 
 * @return id
 */
    public int getId() {
        return id;
    }
/**
 * Palauttaa Vastaanottajan tiedon
 * 
 * @return vastaanottaja 
 */
    public String getVastaanottaja() {
        return vastaanottaja;
    }
    /**
     * Palauttaa Vastaanotto tiedon
     * 
     * @return vastaanotto
     */
    public String getVastaanotto() {
        return vastaanotto;
    }
/**
 * Palauttaa Kuljetuksen kaikki tiedot stringissä.
 * 
 * @return Kaikki tiedot
 */
    @Override
    public String toString() {
        return "Kuljetus{" + "id=" + id + ", vastaanottaja=" + vastaanottaja + ", vastaanotto=" + vastaanotto + '}';
    }

    
    
}
