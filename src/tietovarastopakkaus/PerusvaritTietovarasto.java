/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tietovarastopakkaus;

import datapakkaus.Perusvarit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author s1300778
 */
public class PerusvaritTietovarasto extends Tietovarasto {

    /**
     *
     * @return
     */
    @Override
    public List<Perusvarit> haeTiedot() {
        List<Perusvarit> perusvarit = new ArrayList<>();
        Connection yhteys = yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys != null) {
            PreparedStatement hakulause = null;
            ResultSet tulosjoukko = null;
            try {
                String hakuSql = "SELECT id, perusvarit FROM perusvarit;";
                hakulause = yhteys.prepareStatement(hakuSql);
                tulosjoukko = hakulause.executeQuery();

                while (tulosjoukko.next()) {
                    perusvarit.add(new Perusvarit(tulosjoukko.getString(2),
                            tulosjoukko.getInt(1)));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                YhteydenHallinta.suljeTulosjoukko(tulosjoukko);
                YhteydenHallinta.suljeLause(hakulause);
                YhteydenHallinta.suljeYhteys(yhteys);
            }
        }
        return perusvarit;
    }

    @Override
    public void lisaaTieto(Object object) {
        Perusvarit uusiPerusvari = (Perusvarit) object;
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return;
        }
        PreparedStatement lisayslause = null;
        try {
            String lisaaSql = "insert into perusvarit "
                    + "(id,perusvarit) values (?,?)";
            lisayslause = yhteys.prepareStatement(lisaaSql);

            lisayslause.setInt(1, uusiPerusvari.getId());
            lisayslause.setString(2, uusiPerusvari.getPerusvarit());
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

    @Override
    public boolean muutaTietoja(Object object) {
        Perusvarit uusiPerusvari = (Perusvarit) object;
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return false;
        }
        PreparedStatement muutoslause = null;
        try {
            String muutaSql = "update perusvarit "
                    + " set perusvarit=? where id=?";
            muutoslause = yhteys.prepareStatement(muutaSql);

            muutoslause.setString(1, uusiPerusvari.getPerusvarit());
            muutoslause.setInt(2, uusiPerusvari.getId());
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

    @Override
    public void poistaTieto(int PerusvariID) {
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return;
        }
        PreparedStatement poistolause = null;
        try {
            String poistoSql = "delete from perusvarit where id=?";
            poistolause = yhteys.prepareStatement(poistoSql);
            poistolause.setInt(1, PerusvariID);

            poistolause.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            YhteydenHallinta.suljeLause(poistolause);
            YhteydenHallinta.suljeYhteys(yhteys);
        }
    }

}
