/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datapakkaus;

/**
 *
 * @author s1300723
 */
public class VeneHasPerusvarit {
    private final int veneID;
    private final int perusvaritID;

    public VeneHasPerusvarit(int veneID, int perusvaritID) {
        this.veneID = veneID;
        this.perusvaritID = perusvaritID;
    }

    public int getVeneID() {
        return veneID;
    }

    public int getPerusvaritID() {
        return perusvaritID;
    }

    @Override
    public String toString() {
        return "VeneHasPerusvarit{" + "veneID=" + veneID + ", perusvaritID=" + perusvaritID + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.veneID;
        hash = 97 * hash + this.perusvaritID;
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
        final VeneHasPerusvarit other = (VeneHasPerusvarit) obj;
        if (this.veneID != other.veneID) {
            return false;
        }
        if (this.perusvaritID != other.perusvaritID) {
            return false;
        }
        return true;
    }
    
    
}
