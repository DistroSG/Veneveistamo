package veneveistamo;

import java.util.Objects;

public class Elokuva {

    private final int elokuvaNro;
    private final String nimi;
    private final String ohjaaja;
    private final int vuosi;
    


    public Elokuva(int elokuvaNro, String nimi, String ohjaaja, int vuosi) {
        this.elokuvaNro = elokuvaNro;
        this.nimi = nimi;
        this.ohjaaja = ohjaaja;
        this.vuosi = vuosi;
    }

    public int getElokuvaNro() {
        return elokuvaNro;
    }

    public String getNimi() {
        return nimi;
    }

    public String getOhjaaja() {
        return ohjaaja;
    }

    public int getVuosi() {
        return vuosi;
    }

    @Override
    public String toString() {
        return "ID " + elokuvaNro + ": " + nimi
                + " " + ohjaaja + "(" + vuosi + ')';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + this.elokuvaNro;
        hash = 83 * hash + Objects.hashCode(this.nimi);
        hash = 83 * hash + Objects.hashCode(this.ohjaaja);
        hash = 83 * hash + this.vuosi;
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
        final Elokuva other = (Elokuva) obj;
        if (this.elokuvaNro != other.elokuvaNro) {
            return false;
        }
        if (!Objects.equals(this.nimi, other.nimi)) {
            return false;
        }
        if (!Objects.equals(this.ohjaaja, other.ohjaaja)) {
            return false;
        }
        if (this.vuosi != other.vuosi) {
            return false;
        }
        return true;
    }

}
