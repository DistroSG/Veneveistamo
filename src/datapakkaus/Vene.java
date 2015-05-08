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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + this.id;
        hash = 31 * hash + this.malli;
        hash = 31 * hash + this.takuu;
        hash = 31 * hash + this.hinta;
        hash = 31 * hash + this.alv;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vene other = (Vene) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.malli != other.malli) {
            return false;
        }
        if (this.takuu != other.takuu) {
            return false;
        }
        if (this.hinta != other.hinta) {
            return false;
        }
        if (this.alv != other.alv) {
            return false;
        }
        return true;
    }
    

}
