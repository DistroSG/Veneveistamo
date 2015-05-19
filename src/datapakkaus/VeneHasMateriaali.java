/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datapakkaus;

/**
 * Luodaan uutta vene has materiaalit vene id:n ja materiaalit id:n avulla.
 * @param veneID
 * @param materiaaliID 
 * @author s1300723
 * @version 1.0
 */
public class VeneHasMateriaali {
    private final int veneID;
    private final int materiaaliID;

    public VeneHasMateriaali(int veneID, int materiaaliID) {
        this.veneID = veneID;
        this.materiaaliID = materiaaliID;
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
     * Palauttaa materiaalin ID:n.
     *
     * @return materiaalit id
     */
    public int getMateriaaliID() {
        return materiaaliID;
    }
     /**
     * Palauttaa vene has materiaalit kaikki tiedot.
     *
     * @return kaikki tiedot
     */
    @Override
    public String toString() {
        return "VeneHasMateriaali{" + "veneID=" + veneID + ", materiaaliID=" + materiaaliID + '}';
    }
}
