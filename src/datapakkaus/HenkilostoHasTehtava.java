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
public class HenkilostoHasTehtava {

    private final int henkilostoID;
    private final int tehtavaID;

    public HenkilostoHasTehtava(int henkilostoID, int tehtavaID) {
        this.henkilostoID = henkilostoID;
        this.tehtavaID = tehtavaID;
    }

    public int getHenkilostoID() {
        return henkilostoID;
    }

    public int getTehtavaID() {
        return tehtavaID;
    }

    @Override
    public String toString() {
        return "HenkilostoHasTehtava{" + "henkilostoID=" + henkilostoID + ", tehtavaID=" + tehtavaID + '}';
    }

}
