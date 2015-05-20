/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datapakkaus;
/**
 * Malli luokka. Jolla pidetään tietoja malleista.
 * 
 * @author s1300723
 * version 1.0
 */
public class Malli {

    private final int id;
    private final String malli;
    private final int masto;
    /**
     * Luoda uutta mallin id:n, sukunimen, etunimen, osaston ja toimistoID:n
     * avulla.
     *
     * @param id Mallin id. Esim. "1"
     * @param malli Mallin malli. Esim. "Puuvene"
     * @param masto Mallin masto. Esim. "1"
     */
    public Malli(int id, String malli, int masto) {
        this.id = id;
        this.malli = malli;
        this.masto = masto;
    }
    /**
     * Palauttaa Mallin ID.
     *
     * @return ID
     */
    public int getId() {
        return id;
    }
    /**
     * Palauttaa Mallin Mallin.
     *
     * @return Malli
     */
    public String getMalli() {
        return malli;
    }
    /**
     * Palauttaa Mallin Maston.
     *
     * @return Masto
     */
    public int getMasto() {
        return masto;
    }
    /**
     * Palauttaa mallin kaikki tiedot.
     *
     * @return kaikki tiedot
     */
    @Override
    public String toString() {
        return "Malli{" + "id=" + id + ", malli=" + malli + ", masto=" + masto + '}';
    }
}
