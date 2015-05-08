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
public class Kuljetus {
    
    private final int id;
    private final String vastaanottaja;
    private final String vastaanotto;

    public Kuljetus(int id, String vastaanottaja, String vastaanotto) {
        this.id = id;
        this.vastaanottaja = vastaanottaja;
        this.vastaanotto = vastaanotto;
    }

    public int getId() {
        return id;
    }

    public String getVastaanottaja() {
        return vastaanottaja;
    }

    public String getVastaanotto() {
        return vastaanotto;
    }

    @Override
    public String toString() {
        return "Kuljetus{" + "id=" + id + ", vastaanottaja=" + vastaanottaja + ", vastaanotto=" + vastaanotto + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.id;
        hash = 53 * hash + Objects.hashCode(this.vastaanottaja);
        hash = 53 * hash + Objects.hashCode(this.vastaanotto);
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
        final Kuljetus other = (Kuljetus) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.vastaanottaja, other.vastaanottaja)) {
            return false;
        }
        if (!Objects.equals(this.vastaanotto, other.vastaanotto)) {
            return false;
        }
        return true;
    }
    
    
}
