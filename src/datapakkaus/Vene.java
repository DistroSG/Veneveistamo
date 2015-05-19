/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datapakkaus;

/**
 *
 * @author s1300723
 */
public class Vene {
    private final int id;
    private final int malliID;
    private final String malli;
    private final int takuuID;
    private final int hinta;
    private final int alv;

    public Vene(int id, int malliID, String malli, int takuuID, int hinta, int alv) {
        this.id = id;
        this.malliID = malliID;
        this.malli = malli;
        this.takuuID = takuuID;
        this.hinta = hinta;
        this.alv = alv;
    }

    public int getId() {
        return id;
    }

    public int getMalliID() {
        return malliID;
    }
    
    public String getMalli() {
        return malli;
    }

    public int getTakuuID() {
        return takuuID;
    }

    public int getHinta() {
        return hinta;
    }

    public int getAlv() {
        return alv;
    }

    @Override
    public String toString() {
        return "Vene{" + "id=" + id + ", malliID=" + malliID + ", takuuID=" + takuuID + ", hinta=" + hinta + ", alv=" + alv + '}';
    }
}
