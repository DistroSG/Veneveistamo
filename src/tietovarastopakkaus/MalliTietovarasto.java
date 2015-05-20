/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tietovarastopakkaus;

import datapakkaus.Malli;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * MalliTietovarasto luokka. Jonka avulla saadaan tietokantayhteys ja tiedot
 * Vene taulukkoon.
 *
 * @author s1300723
 * @version 1.0
 */
public class MalliTietovarasto extends Tietovarasto {

      /**
     * Palautta kaikki mallien tiedot.
     *
     * @return kaikki mallien tiedot.
     */
    @Override
    public List<Malli> haeTiedot() {
        List<Malli> mallit = new ArrayList<>();
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys != null) {
            PreparedStatement hakulause = null;
            ResultSet tulosjoukko = null;
            try {
                String hakuSql = "SELECT id, malli, masto FROM malli;";
                hakulause = yhteys.prepareStatement(hakuSql);
                tulosjoukko = hakulause.executeQuery();

                while (tulosjoukko.next()) {
                    mallit.add(new Malli(tulosjoukko.getInt(1),
                            tulosjoukko.getString(2),tulosjoukko.getInt(3)));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                YhteydenHallinta.suljeTulosjoukko(tulosjoukko);
                YhteydenHallinta.suljeLause(hakulause);
                YhteydenHallinta.suljeYhteys(yhteys);
            }
        }
        return mallit;
    }
    /**
     * Lisätä uusi malli.
     *
     * @param object malli, joka lisätään.
     */
    @Override
    public void lisaaTieto(Object object) {
        Malli uusiMalli = (Malli) object;
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return;
        }
        PreparedStatement lisayslause = null;
        try {
            String lisaaSql = "insert into malli "
                    + "(id,malli,masto) values (?,?,?)";
            lisayslause = yhteys.prepareStatement(lisaaSql);

            lisayslause.setInt(1, uusiMalli.getId());
            lisayslause.setString(2, uusiMalli.getMalli());
            lisayslause.setInt(3, uusiMalli.getMasto());
            lisayslause.executeUpdate();
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1062) {
                idVirheIlmoitus();
            }
        } finally {
            YhteydenHallinta.suljeLause(lisayslause);
            YhteydenHallinta.suljeYhteys(yhteys);
        }
    }
    /**
     * Muuta mallien tiedot.
     *
     * @param object malli, joka muutetaan.
     * @return palauttaa true, jos muuttaminen on onnistunut.
     */
    @Override
    public boolean muutaTietoja(Object object) {
        Malli uusiMalli = (Malli) object;
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return false;
        }
        PreparedStatement muutoslause = null;
        try {
            String muutaSql = "update malli "
                    + " set malli=?, masto=? where id=?";
            muutoslause = yhteys.prepareStatement(muutaSql);

            muutoslause.setInt(3, uusiMalli.getId());
            muutoslause.setString(1, uusiMalli.getMalli());
            muutoslause.setInt(2, uusiMalli.getMasto());
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
        }
    }
    /**
     * Poista malli.
     *
     * @param MalliID poistettavan mallin id. Esim. "1"
     */
    @Override
    public void poistaTieto(int MalliID) {
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return;
        }
        PreparedStatement poistolause = null;
        try {
            String poistoSql = "delete from malli where id=?";
            poistolause = yhteys.prepareStatement(poistoSql);
            poistolause.setInt(1, MalliID);

            poistolause.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            YhteydenHallinta.suljeLause(poistolause);
            YhteydenHallinta.suljeYhteys(yhteys);
        }
    }

}
