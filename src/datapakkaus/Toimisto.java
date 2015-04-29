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
public class Toimisto {

    private final int id;
    private final String aukiolajit;
    private final String katuosoite;
    private final int postinumero;
    private final String toimipaikka;

    public Toimisto(int id, String aukiolajit, String katuosoite, int postinumero, String toimipaikka) {
        this.id = id;
        this.aukiolajit = aukiolajit;
        this.katuosoite = katuosoite;
        this.postinumero = postinumero;
        this.toimipaikka = toimipaikka;
    }

    public int getId() {
        return id;
    }

    public String getAukiolajit() {
        return aukiolajit;
    }

    public String getKatuosoite() {
        return katuosoite;
    }

    public int getPostinumero() {
        return postinumero;
    }

    public String getToimipaikka() {
        return toimipaikka;
    }

    @Override
    public String toString() {
        return "Toimisto{" + "id=" + id + ", aukiolajit=" + aukiolajit + ", katuosoite=" + katuosoite + ", postinumero=" + postinumero + ", toimipaikka=" + toimipaikka + '}';
    }

}
