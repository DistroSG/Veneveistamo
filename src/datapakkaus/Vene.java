/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datapakkaus;
/**
 * Vene luokka. Jolla pidetään tietoja veneistä.
 * 
 * @author s1300723
 * version 1.0
 */
public class Vene {
    private final int id;
    private final int malliID;
    private final String malli;
    private final int takuuID;
    private final int hinta;
    private final int alv;
    /**
     * Luoda uutta mallin id:n, sukunimen, etunimen, osaston ja toimistoID:n
     * avulla.
     *
     * @param id Veneen id. Esim. "1"
     * @param malliID. Esim. "1"
     * @param malli Valitsee mallin toisesta kannasta malliID:n perusteella
     * @param takuuID. Esim. "1"
     * @param hinta Veneen hinta. Esim. "1000"
     * @param alv Veneen alv. Esim. "24"
     */
    public Vene(int id, int malliID, String malli, int takuuID, int hinta, int alv) {
        this.id = id;
        this.malliID = malliID;
        this.malli = malli;
        this.takuuID = takuuID;
        this.hinta = hinta;
        this.alv = alv;
    }
    /**
     * Palauttaa VeneID:n.
     *
     * @return id
     */
    public int getId() {
        return id;
    }
    /**
     * Palauttaa MalliID:n.
     *
     * @return malliID
     */
    public int getMalliID() {
        return malliID;
    }
    /**
     * Palauttaa Mallin.
     *
     * @return malli
     */
    public String getMalli() {
        return malli;
    }
/**
     * Palauttaa TakuuID:n.
     *
     * @return takuuID
     */
    public int getTakuuID() {
        return takuuID;
    }
/**
     * Palauttaa Hinnan.
     *
     * @return hinta
     */
    public int getHinta() {
        return hinta;
    }
/**
     * Palauttaa Alv:n.
     *
     * @return alv
     */
    public int getAlv() {
        return alv;
    }
    /**
     * Palauttaa veneen kaikki tiedot.
     *
     * @return kaikki tiedot
     */
    @Override
    public String toString() {
        return "Vene{" + "id=" + id + ", malliID=" + malliID + ", takuuID=" + takuuID + ", hinta=" + hinta + ", alv=" + alv + '}';
    }
}
