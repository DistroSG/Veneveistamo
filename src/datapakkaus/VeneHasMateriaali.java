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
public class VeneHasMateriaali {
    private final int veneID;
    private final int materiaaliID;

    public VeneHasMateriaali(int veneID, int materiaaliID) {
        this.veneID = veneID;
        this.materiaaliID = materiaaliID;
    }

    public int getVeneID() {
        return veneID;
    }

    public int getMateriaaliID() {
        return materiaaliID;
    }

    @Override
    public String toString() {
        return "VeneHasMateriaali{" + "veneID=" + veneID + ", materiaaliID=" + materiaaliID + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.veneID;
        hash = 47 * hash + this.materiaaliID;
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
        final VeneHasMateriaali other = (VeneHasMateriaali) obj;
        if (this.veneID != other.veneID) {
            return false;
        }
        if (this.materiaaliID != other.materiaaliID) {
            return false;
        }
        return true;
    }
    
}
