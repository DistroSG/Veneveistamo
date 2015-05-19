/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datapakkaus;

/**
 * Luodaan uutta vene has perusvarit vene id:n ja perusvarit id:n avulla.
 * @param veneID
 * @param perusvaritID 
 * @author s1300723
 * @version 1.0
 */
public class VeneHasPerusvarit {
    private final int veneID;
    private final int perusvaritID;

    public VeneHasPerusvarit(int veneID, int perusvaritID) {
        this.veneID = veneID;
        this.perusvaritID = perusvaritID;
    }
    /**
     * Palauttaa veneen ID:n.
     *
     * @return vene id
     */

    public int getVeneID() {
        return veneID;
    }
    /**
     * Palauttaa perusvarin ID:n.
     *
     * @return perusvarit id
     */

    public int getPerusvaritID() {
        return perusvaritID;
    }
     /**
     * Palauttaa vene has perusvarit kaikki tiedot.
     *
     * @return kaikki tiedot
     */

    @Override
    public String toString() {
        return "VeneHasPerusvarit{" + "veneID=" + veneID + ", perusvaritID=" + perusvaritID + '}';
    }
}
