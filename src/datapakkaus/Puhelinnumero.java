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
public class Puhelinnumero {

    private final int id;
    private final int puhelinnumero;
    private final int toimistoID;

    public Puhelinnumero(int id, int puhelinnumero, int toimistoID) {
        this.id = id;
        this.puhelinnumero = puhelinnumero;
        this.toimistoID = toimistoID;
    }

    public int getId() {
        return id;
    }

    public int getPuhelinnumero() {
        return puhelinnumero;
    }

    public int getToimistoID() {
        return toimistoID;
    }

    @Override
    public String toString() {
        return "Puhelinnumero{" + "id=" + id + ", puhelinnumero=" + puhelinnumero + ", toimistoID=" + toimistoID + '}';
    }

}
