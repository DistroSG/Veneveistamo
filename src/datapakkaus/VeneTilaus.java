/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datapakkaus;

import java.util.Objects;

/**
 *
 * @author Axeld
 */
public class VeneTilaus {
    
    private final int id;
    private final int vene_id;
    private final int henkilosto_id;
    private final double hinta;
    private final int kuljetus_id;
    private final String vari;
    private final String edistyminen;

    public VeneTilaus(int id, int vene_id, int henkilosto_id, double hinta, int kuljetus_id, String vari, String edistyminen) {
        this.id = id;
        this.vene_id = vene_id;
        this.henkilosto_id = henkilosto_id;
        this.hinta = hinta;
        this.kuljetus_id = kuljetus_id;
        this.vari = vari;
        this.edistyminen = edistyminen;
    }



    public int getId() {
        return id;
    }

    public int getVene_id() {
        return vene_id;
    }

    public int getHenkilosto_id() {
        return henkilosto_id;
    }

    public double getHinta() {
        return hinta;
    }

    public int getKuljetus_id() {
        return kuljetus_id;
    }

    public String getVari() {
        return vari;
    }

    public String getEdistyminen() {
        return edistyminen;
    }

    @Override
    public String toString() {
        return "venetilaus{" + "id=" + id + ", vene_id=" + vene_id + ", henkilosto_id=" + henkilosto_id + ", hinta=" + hinta + ", kuljetus_id=" + kuljetus_id + ", vari=" + vari + ", edistyminen=" + edistyminen + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.id;
        hash = 59 * hash + this.vene_id;
        hash = 59 * hash + this.henkilosto_id;
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.hinta) ^ (Double.doubleToLongBits(this.hinta) >>> 32));
        hash = 59 * hash + this.kuljetus_id;
        hash = 59 * hash + Objects.hashCode(this.vari);
        hash = 59 * hash + Objects.hashCode(this.edistyminen);
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
        final VeneTilaus other = (VeneTilaus) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.vene_id != other.vene_id) {
            return false;
        }
        if (this.henkilosto_id != other.henkilosto_id) {
            return false;
        }
        if (Double.doubleToLongBits(this.hinta) != Double.doubleToLongBits(other.hinta)) {
            return false;
        }
        if (this.kuljetus_id != other.kuljetus_id) {
            return false;
        }
        if (!Objects.equals(this.vari, other.vari)) {
            return false;
        }
        if (!Objects.equals(this.edistyminen, other.edistyminen)) {
            return false;
        }
        return true;
    }
    
    
}
