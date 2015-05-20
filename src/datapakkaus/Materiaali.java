/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datapakkaus;

/**
 * Materiaali luokka. Jolla pidetään tietoja materiaaleista.
 * 
 * @author s1300723
 * version 1.0
 */
public class Materiaali {
    private final String materiaali;
    private final int id;
    /**
     * Luoda uutta mallin id:n, sukunimen, etunimen, osaston ja toimistoID:n
     * avulla.
     *
     * @param id Mallin id. Esim. "1"
     * @param materiaali Mallin malli. Esim. "Puuvene"
     */
    public Materiaali(String materiaali, int id) {
        this.materiaali = materiaali;
        this.id = id;
    }
    /**
     * Palauttaa Materiaalin.
     *
     * @return Materiaali
     */
    public String getMateriaali() {
        return materiaali;
    }
    /**
     * Palauttaa Materiaalin ID.
     *
     * @return ID
     */
    public int getId() {
        return id;
    }
    /**
     * Palauttaa materiaalin kaikki tiedot.
     *
     * @return kaikki tiedot
     */
    @Override
    public String toString() {
        return "Materiaali{" + "materiaali=" + materiaali + ", id=" + id + '}';
    }
}
