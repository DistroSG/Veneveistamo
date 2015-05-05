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
public class Materiaali {
    private final String materiaali;
    private final int id;

    public Materiaali(String materiaali, int id) {
        this.materiaali = materiaali;
        this.id = id;
    }

    public String getMateriaali() {
        return materiaali;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Materiaali{" + "materiaali=" + materiaali + ", id=" + id + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.materiaali);
        hash = 97 * hash + this.id;
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
        final Materiaali other = (Materiaali) obj;
        if (!Objects.equals(this.materiaali, other.materiaali)) {
            return false;
        }
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
