/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datapakkaus;

import java.util.Objects;

/**
 *
 * @author s1300776
 */
public class Asiakas {
    private final int id;
    private final String henkilotunnus;
    private final String salasana;
    private final String sukunimi;
    private final String etunimi;
    private final String sahkoposti;
    private final String sukupuoli;
    private final String puhelinnumero;
    private final String asiakastyyppi;

        public Asiakas(int id, String henkilotunnus, String salasana, String sukunimi, String etunimi, String sahkoposti, String sukupuoli, String puhelinnumero, String asiakastyyppi) {
        this.id = id;
        this.henkilotunnus = henkilotunnus;
        this.salasana = salasana;
        this.sukunimi = sukunimi;
        this.etunimi = etunimi;
        this.sahkoposti = sahkoposti;
        this.sukupuoli = sukupuoli;
        this.puhelinnumero = puhelinnumero;
        this.asiakastyyppi = asiakastyyppi;
    }

    public Asiakas(int id, String henkilotunnus, String value, String salasana, String value0, String sukunimi, String value1, String etunimi, String value2, String sahkoposti, String value3, String sukupuoli, String value4, String asiakastyyppi, String value5) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
    
    public int getId() {
        return id;
    }

    public String getHenkilotunnus() {
        return henkilotunnus;
    }

    public String getSalasana() {
        return salasana;
    }

    public String getSukunimi() {
        return sukunimi;
    }

    public String getEtunimi() {
        return etunimi;
    }

    public String getSahkoposti() {
        return sahkoposti;
    }

    public String getSukupuoli() {
        return sukupuoli;
    }

    public String getPuhelinnumero() {
        return puhelinnumero;
    }

    public String getAsiakastyyppi() {
        return asiakastyyppi;
    }
    
    
    
    @Override
    public String toString() {
        return "asiakas{" + "id=" + id + ", henkilotunnus=" + henkilotunnus + ", salasana=" + salasana + ", sukunimi=" + sukunimi + ", etunimi=" + etunimi + ", sahkoposti=" + sahkoposti + ", sukupuoli=" + sukupuoli + ", puhelinnumero=" + puhelinnumero + ", asiakastyyppi=" + asiakastyyppi + '}';
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.id;
        hash = 97 * hash + Objects.hashCode(this.henkilotunnus);
        hash = 97 * hash + Objects.hashCode(this.salasana);
        hash = 97 * hash + Objects.hashCode(this.sukunimi);
        hash = 97 * hash + Objects.hashCode(this.etunimi);
        hash = 97 * hash + Objects.hashCode(this.sahkoposti);
        hash = 97 * hash + Objects.hashCode(this.sukupuoli);
        hash = 97 * hash + Objects.hashCode(this.puhelinnumero);
        hash = 97 * hash + Objects.hashCode(this.asiakastyyppi);
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
        final Asiakas other = (Asiakas) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.henkilotunnus, other.henkilotunnus)) {
            return false;
        }
        if (!Objects.equals(this.salasana, other.salasana)) {
            return false;
        }
        if (!Objects.equals(this.sukunimi, other.sukunimi)) {
            return false;
        }
        if (!Objects.equals(this.etunimi, other.etunimi)) {
            return false;
        }
        if (!Objects.equals(this.sahkoposti, other.sahkoposti)) {
            return false;
        }
        if (!Objects.equals(this.sukupuoli, other.sukupuoli)) {
            return false;
        }
        if (!Objects.equals(this.puhelinnumero, other.puhelinnumero)) {
            return false;
        }
        if (!Objects.equals(this.asiakastyyppi, other.asiakastyyppi)) {
            return false;
        }
        return true;
    }


    
}
