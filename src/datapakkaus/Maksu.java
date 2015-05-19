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
 * @version 1.0
 */
public class Maksu {
    
    private final int eranumero;
    private final int venetilaus_id;
    private final double hinta;
    private final String maksettupaiva;
    private final String erapaiva;

    /**
     * Luo uuden tiedon eranumero, venetilaus_id,hinta, maksettupaiva ja erapaiva Maksu tauluun.
     * 
     * @param eranumero Maksun eränumero esim. 1
     * @param venetilaus_id venetilauksen id esim. 1
     * @param hinta Maksun hinta esim. 10.
     * @param maksettupaiva Maksun maksettupaiva esim. 02-03-2015
     * @param erapaiva maksun eräpäivä esim. 05-05-2015
     */
    public Maksu(int eranumero, int venetilaus_id, double hinta, String maksettupaiva, String erapaiva) {
        this.eranumero = eranumero;
        this.venetilaus_id = venetilaus_id;
        this.hinta = hinta;
        this.maksettupaiva = maksettupaiva;
        this.erapaiva = erapaiva;
    }
    /**
     * Palauttaa eranumero arvon.
     * 
     * @return eranumero
     */
    
    public int getEranumero() {
        return eranumero;
    }
    /**
     * palauttaa venetilaus id
     * 
     * @return venetilaus_id
     */

    public int getveneTilaus_id() {
        return venetilaus_id;
    }
/**
 * palauttaa hinta arvon
 * 
 * @return hinta 
 */
    
    public double getHinta() {
        return hinta;
    }
/**
 * palauttaa maksettupaiva arvon
 * 
 * @return maksettupaiva 
 */
    
    public String getMaksettupaiva() {
        return maksettupaiva;
    }
/**
 * palauttaa erapaiva arvon
 * 
 * @return erapaiva
 */
    public String getErapaiva() {
        return erapaiva;
    }
/**
 * Palauttaa kaikki Maksun tiedot stringissä.
 * @return 
 */
    @Override
    public String toString() {
        return "Maksu{" + "eranumero=" + eranumero + ", tilausID=" + venetilaus_id + ", hinta=" + hinta + ", maksettupaiva=" + maksettupaiva + ", erapaiva=" + erapaiva + '}';
    }


    
    
}
