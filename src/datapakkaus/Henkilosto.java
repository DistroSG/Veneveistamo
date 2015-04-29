/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datapakkaus;

/**
 *
 * @author s1300778
 */
public class Henkilosto {

    private final int id;
    private final String sukunimi;
    private final String etunimi;
    private final String osasto;
    private final int toimistoID;

    public Henkilosto(int id, String sukunimi, String etunimi, String osasto, int toimistoID) {
        this.id = id;
        this.sukunimi = sukunimi;
        this.etunimi = etunimi;
        this.osasto = osasto;
        this.toimistoID = toimistoID;
    }

    public int getId() {
        return id;
    }

    public String getSukunimi() {
        return sukunimi;
    }

    public String getEtunimi() {
        return etunimi;
    }

    public String getOsasto() {
        return osasto;
    }

    public int getToimistoID() {
        return toimistoID;
    }

    @Override
    public String toString() {
        return "Henkilosto{" + "id=" + id + ", sukunimi=" + sukunimi + ", etunimi=" + etunimi + ", osasto=" + osasto + ", toimistoID=" + toimistoID + '}';
    }

}
