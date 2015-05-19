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
    private final int malli;
    private final int takuu;
    private final int hinta;
    private final int alv;

    public Vene(int id, int malli, int takuu, int hinta, int alv) {
        this.id = id;
        this.malli = malli;
        this.takuu = takuu;
        this.hinta = hinta;
        this.alv = alv;
    }

    public int getId() {
        return id;
    }

    public int getMalli() {
        return malli;
    }

    public int getTakuu() {
        return takuu;
    }

    public int getHinta() {
        return hinta;
    }

    public int getAlv() {
        return alv;
    }

    @Override
    public String toString() {
        return "Vene{" + "id=" + id + ", malli=" + malli + ", takuu=" + takuu + ", hinta=" + hinta + ", alv=" + alv + '}';
    }
}
