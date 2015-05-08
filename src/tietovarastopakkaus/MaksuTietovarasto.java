/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tietovarastopakkaus;

import datapakkaus.Maksu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import java.util.List;

/**
 *
 * @author s1300727
 */
public class MaksuTietovarasto extends Tietovarasto {

    @Override
    public List<Maksu> haeTiedot() {
        List<Maksu> maksut = new ArrayList<>();
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys != null) {
            PreparedStatement hakulause = null;
            ResultSet tulosjoukko = null;
            try {
                String hakuSql = "SELECT eranumero, veneTilaus_id, hinta, maksettupaiva, erapaiva FROM maksu;";
                hakulause = yhteys.prepareStatement(hakuSql);
                tulosjoukko = hakulause.executeQuery();

                while (tulosjoukko.next()) {
                    maksut.add(new Maksu(tulosjoukko.getInt(1),
                            tulosjoukko.getInt(2), tulosjoukko.getDouble(3), tulosjoukko.getString(4), tulosjoukko.getString(5)));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                YhteydenHallinta.suljeTulosjoukko(tulosjoukko);
                YhteydenHallinta.suljeLause(hakulause);
                YhteydenHallinta.suljeYhteys(yhteys);
            }
        }
        return maksut;
    }



    @Override
public void lisaaTieto(Object object) {
        Maksu uusiMaksu = (Maksu) object;
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return;
        }
        PreparedStatement lisayslause = null;
        try {
            String lisaaSql = "insert into maksu"
                    + "(eranumero, veneTilaus_id, hinta, maksettupaiva, erapaiva) values (?,?,?,?,?)";
            lisayslause = yhteys.prepareStatement(lisaaSql);

            lisayslause.setInt(1, uusiMaksu.getEranumero());
            lisayslause.setInt(2, uusiMaksu.getveneTilaus_id());
            lisayslause.setDouble(3, uusiMaksu.getHinta());
            lisayslause.setString(4, uusiMaksu.getMaksettupaiva());
            lisayslause.setString(5, uusiMaksu.getErapaiva());
            lisayslause.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            YhteydenHallinta.suljeLause(lisayslause);
            YhteydenHallinta.suljeYhteys(yhteys);
        }
    }

    @Override
        public boolean muutaTietoja(Object object) {
        Maksu uusiMaksu = (Maksu) object;
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return false;
        }
        PreparedStatement muutoslause = null;
        try {

            String muutaSql = "update maksu "
                    + "set veneTilaus_id=?,hinta=?,maksettupaiva=?,erapaiva=? "
                    + "where eranumero=?";
            muutoslause = yhteys.prepareStatement(muutaSql);

            muutoslause.setInt(5, uusiMaksu.getEranumero());
            muutoslause.setInt(1, uusiMaksu.getveneTilaus_id());
            muutoslause.setDouble(2, uusiMaksu.getHinta());
            muutoslause.setString(3, uusiMaksu.getMaksettupaiva());
            muutoslause.setString(4, uusiMaksu.getErapaiva());
            if (muutoslause.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            YhteydenHallinta.suljeLause(muutoslause);
            YhteydenHallinta.suljeYhteys(yhteys);
        }    }

    @Override
    public void poistaTieto(int eranumero) {
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return;
        }
        PreparedStatement poistolause = null;
        try {
            String poistoSql = "delete from maksu where eranumero=?";
            poistolause = yhteys.prepareStatement(poistoSql);
            poistolause.setInt(1, eranumero);

            poistolause.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            YhteydenHallinta.suljeLause(poistolause);
            YhteydenHallinta.suljeYhteys(yhteys);
        }
    }
    
    
}
