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
public class Malli {

    private final int id;
    private final String malli;
    private final int masto;

    public Malli(int id, String malli, int masto) {
        this.id = id;
        this.malli = malli;
        this.masto = masto;
    }

    public int getId() {
        return id;
    }

    public String getMalli() {
        return malli;
    }

    public int getMasto() {
        return masto;
    }

    @Override
    public String toString() {
        return "Malli{" + "id=" + id + ", malli=" + malli + ", masto=" + masto + '}';
    }

}
