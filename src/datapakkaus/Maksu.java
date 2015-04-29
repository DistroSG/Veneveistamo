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
public class Maksu {
    
    private final int eranumero;
    private final int tilausID;
    private final double hinta;
    private final String maksettupaiva;
    private final String erapaiva;

    public Maksu(int eranumero, int tilausID, double hinta, String maksettupaiva, String erapaiva) {
        this.eranumero = eranumero;
        this.tilausID = tilausID;
        this.hinta = hinta;
        this.maksettupaiva = maksettupaiva;
        this.erapaiva = erapaiva;
    }
    
    public int getEranumero() {
        return eranumero;
    }

    public int getTilausID() {
        return tilausID;
    }

    public double getHinta() {
        return hinta;
    }

    public String getMaksettupaiva() {
        return maksettupaiva;
    }

    public String getErapaiva() {
        return erapaiva;
    }

    @Override
    public String toString() {
        return "Maksu{" + "eranumero=" + eranumero + ", tilausID=" + tilausID + ", hinta=" + hinta + ", maksettupaiva=" + maksettupaiva + ", erapaiva=" + erapaiva + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + this.eranumero;
        hash = 47 * hash + this.tilausID;
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.hinta) ^ (Double.doubleToLongBits(this.hinta) >>> 32));
        hash = 47 * hash + Objects.hashCode(this.maksettupaiva);
        hash = 47 * hash + Objects.hashCode(this.erapaiva);
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
        final Maksu other = (Maksu) obj;
        if (this.eranumero != other.eranumero) {
            return false;
        }
        if (this.tilausID != other.tilausID) {
            return false;
        }
        if (Double.doubleToLongBits(this.hinta) != Double.doubleToLongBits(other.hinta)) {
            return false;
        }
        if (!Objects.equals(this.maksettupaiva, other.maksettupaiva)) {
            return false;
        }
        if (!Objects.equals(this.erapaiva, other.erapaiva)) {
            return false;
        }
        return true;
    }
    
    
}
