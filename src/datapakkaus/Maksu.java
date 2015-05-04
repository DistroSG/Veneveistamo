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
    private final int venetilaus_id;
    private final double hinta;
    private final String maksettupaiva;
    private final String erapaiva;

    public Maksu(int eranumero, int venetilaus_id, double hinta, String maksettupaiva, String erapaiva) {
        this.eranumero = eranumero;
        this.venetilaus_id = venetilaus_id;
        this.hinta = hinta;
        this.maksettupaiva = maksettupaiva;
        this.erapaiva = erapaiva;
    }
    
    public int getEranumero() {
        return eranumero;
    }

    public int getveneTilaus_id() {
        return venetilaus_id;
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
        return "Maksu{" + "eranumero=" + eranumero + ", tilausID=" + venetilaus_id + ", hinta=" + hinta + ", maksettupaiva=" + maksettupaiva + ", erapaiva=" + erapaiva + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + this.eranumero;
        hash = 47 * hash + this.venetilaus_id;
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
        if (this.venetilaus_id != other.venetilaus_id) {
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
