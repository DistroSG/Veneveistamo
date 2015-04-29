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
public class Tehtava {
    
    private final int id;
    private final String tehtava;

    public Tehtava(int id, String tehtava) {
        this.id = id;
        this.tehtava = tehtava;
    }

    public int getId() {
        return id;
    }

    public String getTehtava() {
        return tehtava;
    }

    @Override
    public String toString() {
        return "Tehtava{" + "id=" + id + ", tehtava=" + tehtava + '}';
    }
    
}
