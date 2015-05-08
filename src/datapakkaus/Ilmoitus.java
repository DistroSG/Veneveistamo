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
public class Ilmoitus {
    
    private final int id;
    private final String ilmoitus;
    private final double hinta;
    private final int venetilaus_id;

    public Ilmoitus(int id, String ilmoitus, double hinta, int venetilaus_id) {
        this.id = id;
        this.ilmoitus = ilmoitus;
        this.hinta = hinta;
        this.venetilaus_id = venetilaus_id;
    }

    public int getId() {
        return id;
    }

    public String getIlmoitus() {
        return ilmoitus;
    }

    public double getHinta() {
        return hinta;
    }

    public int getVenetilaus_id() {
        return venetilaus_id;
    }

    @Override
    public String toString() {
        return "Ilmoitus{" + "id=" + id + ", ilmoitus=" + ilmoitus + ", hinta=" + hinta + ", venetilaus_id=" + venetilaus_id + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.id;
        hash = 53 * hash + Objects.hashCode(this.ilmoitus);
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.hinta) ^ (Double.doubleToLongBits(this.hinta) >>> 32));
        hash = 53 * hash + this.venetilaus_id;
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
        final Ilmoitus other = (Ilmoitus) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.ilmoitus, other.ilmoitus)) {
            return false;
        }
        if (Double.doubleToLongBits(this.hinta) != Double.doubleToLongBits(other.hinta)) {
            return false;
        }
        if (this.venetilaus_id != other.venetilaus_id) {
            return false;
        }
        return true;
    }
    
    
}
