
package datapakkaus;

import java.util.Objects;

public class Osoite {
    private final int id;
    private final String katuosoite;
    private final String postinumero;
    private final String toimipaikka;
    private final String yrityksennimi;
    private final int venetilausid;

    public Osoite(int id, String katuosoite, String postinumero, String toimipaikka, String yrityksennimi, int venetilausid) {
        this.id = id;
        this.katuosoite = katuosoite;
        this.postinumero = postinumero;
        this.toimipaikka = toimipaikka;
        this.yrityksennimi = yrityksennimi;
        this.venetilausid = venetilausid;
    }

    public int getId() {
        return id;
    }

    public String getKatuosoite() {
        return katuosoite;
    }

    public String getPostinumero() {
        return postinumero;
    }

    public String getToimipaikka() {
        return toimipaikka;
    }

    public String getYrityksennimi() {
        return yrityksennimi;
    }

    public int getVenetilausid() {
        return venetilausid;
    }

    @Override
    public String toString() {
        return "Osoite{" + "id=" + id + ", katuosoite=" + katuosoite + ", postinumero=" + postinumero + ", toimipaikka=" + toimipaikka + ", yrityksennimi=" + yrityksennimi + ", venetilausid=" + venetilausid + '}';
    }
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + this.id;
        hash = 23 * hash + Objects.hashCode(this.katuosoite);
        hash = 23 * hash + Objects.hashCode(this.postinumero);
        hash = 23 * hash + Objects.hashCode(this.toimipaikka);
        hash = 23 * hash + Objects.hashCode(this.yrityksennimi);
        hash = 23 * hash + this.venetilausid;
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
        final Osoite other = (Osoite) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.katuosoite, other.katuosoite)) {
            return false;
        }
        if (!Objects.equals(this.postinumero, other.postinumero)) {
            return false;
        }
        if (!Objects.equals(this.toimipaikka, other.toimipaikka)) {
            return false;
        }
        if (!Objects.equals(this.yrityksennimi, other.yrityksennimi)) {
            return false;
        }
        if (this.venetilausid != other.venetilausid) {
            return false;
        }
        return true;
    }
    
    
    
}
