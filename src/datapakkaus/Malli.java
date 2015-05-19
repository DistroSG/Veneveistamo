/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datapakkaus;

import java.util.Objects;

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

    public Malli(int id, String malli, int masto) {
        this.id = id;
        this.malli = malli;
        this.masto = masto;
    }

    public int getId() {
        return id;
    }

    public String getMalli() {
        return malli;
    }

    public int getMasto() {
        return masto;
    }

    @Override
    public String toString() {
        return "Malli{" + "id=" + id + ", malli=" + malli + ", masto=" + masto + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
        hash = 97 * hash + Objects.hashCode(this.malli);
        hash = 97 * hash + Objects.hashCode(this.masto);
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
        final Malli other = (Malli) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.malli, other.malli)) {
            return false;
        }
        if (!Objects.equals(this.masto, other.masto)) {
            return false;
        }
        return true;
    }


}
