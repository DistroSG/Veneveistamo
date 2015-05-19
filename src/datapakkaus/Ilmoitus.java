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
public class Ilmoitus {
    
    private final int id;
    private final String ilmoitus;
    private final double hinta;
    private final int venetilaus_id;

    /**
     * Luo uuden tiedon ID, ilmoitus, hinta ja venetilausID:eelle
     * 
     * @param id Ilmoituksen id. Esim "1"
     * @param ilmoitus Ilmoitus. Esim "Vene on valmiina noudettavaksi."
     * @param hinta Hinta. Esim "10"
     * @param venetilaus_id VeneTilaus_id joka on linkitetty venetilaus tauluun foreingkeyllä Esim "1"
     */
    
    public Ilmoitus(int id, String ilmoitus, double hinta, int venetilaus_id) {
        this.id = id;
        this.ilmoitus = ilmoitus;
        this.hinta = hinta;
        this.venetilaus_id = venetilaus_id;
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
 * Palauttaa Ilmmoitus tiedon
 * 
 * @return ilmoitus
 */
    
    public String getIlmoitus() {
        return ilmoitus;
    }
/**
 * Palauttaa Hinta tiedon
 * 
 * @return hinta
 */
    public double getHinta() {
        return hinta;
    }
/**
 * Palauttaa veneTilaus_ID:n
 * 
 * @return venetilaus_id 
 */
    public int getVenetilaus_id() {
        return venetilaus_id;
    }
/**
 * Palauttaa Ilmoituksen kaikki tiedot stringissä.
 * 
 * @return  Kaikki tiedot
 */
    @Override
    public String toString() {
        return "Ilmoitus{" + "id=" + id + ", ilmoitus=" + ilmoitus + ", hinta=" + hinta + ", venetilaus_id=" + venetilaus_id + '}';
    }


    
    
}
