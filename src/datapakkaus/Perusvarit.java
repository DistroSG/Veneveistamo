/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datapakkaus;

import java.util.Objects;

/**
 *
 * @author s1300723
 */
public class Perusvarit {
    private final String perusvarit;
    private final int id;

    public Perusvarit(String perusvarit, int id) {
        this.perusvarit = perusvarit;
        this.id = id;
    }

    public String getPerusvarit() {
        return perusvarit;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Perusvarit{" + "perusvarit=" + perusvarit + ", id=" + id + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.perusvarit);
        hash = 47 * hash + this.id;
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
        final Perusvarit other = (Perusvarit) obj;
        if (!Objects.equals(this.perusvarit, other.perusvarit)) {
            return false;
        }
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    

}
