/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tietovarastopakkaus;

import datapakkaus.Toimisto;
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
public class ToimistoTietovarasto extends Tietovarasto {

    @Override
    public List<Toimisto> haeTiedot() {
        List<Toimisto> toimistot = new ArrayList<>();
        Connection yhteys = yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys != null) {
            PreparedStatement hakulause = null;
            ResultSet tulosjoukko = null;
            try {
                String hakuSql = "SELECT id, aukioloajat, katuosoite, postinumero, toimipaikka FROM toimisto;";
                hakulause = yhteys.prepareStatement(hakuSql);
                tulosjoukko = hakulause.executeQuery();

                while (tulosjoukko.next()) {
                    toimistot.add(new Toimisto(tulosjoukko.getInt(1),
                            tulosjoukko.getString(2), tulosjoukko.getString(3), tulosjoukko.getInt(4), tulosjoukko.getString(5)));

                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                YhteydenHallinta.suljeTulosjoukko(tulosjoukko);
                YhteydenHallinta.suljeLause(hakulause);
                YhteydenHallinta.suljeYhteys(yhteys);
            }
        }
        return toimistot;
    }

    @Override
    public void lisaaTieto(Object object) {

        Toimisto uusiToimisto = (Toimisto) object;
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return;
        }
        PreparedStatement lisayslause = null;
        try {
            String lisaaSql = "insert into toimisto"
                    + "(id,aukioloajat, katuosoite, postinumero, toimipaikka) values (?,?,?,?,?)";
            lisayslause = yhteys.prepareStatement(lisaaSql);

            lisayslause.setInt(1, uusiToimisto.getId());
            lisayslause.setString(2, uusiToimisto.getAukioloajat());
            lisayslause.setString(3, uusiToimisto.getKatuosoite());
            lisayslause.setInt(4, uusiToimisto.getPostinumero());
            lisayslause.setString(5, uusiToimisto.getToimipaikka());
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
        Toimisto uusiToimisto = (Toimisto) object;
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return false;
        }
        PreparedStatement muutoslause = null;
        try {

            String muutaSql = "update toimisto "
                    + " set aukioloajat=?,katuosoite=?,postinumero=?,toimipaikka=? "
                    + "where id=?";
            muutoslause = yhteys.prepareStatement(muutaSql);

            muutoslause.setString(1, uusiToimisto.getAukioloajat());
            muutoslause.setString(2, uusiToimisto.getKatuosoite());
            muutoslause.setInt(3, uusiToimisto.getPostinumero());
            muutoslause.setString(4, uusiToimisto.getToimipaikka());
            muutoslause.setInt(5, uusiToimisto.getId());

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
    public void poistaTieto(int toimistoID) {
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return;
        }
        PreparedStatement poistolause = null;
        try {
            String poistoSql = "delete from toimisto where id=?";
            poistolause = yhteys.prepareStatement(poistoSql);
            poistolause.setInt(1, toimistoID);

            poistolause.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            YhteydenHallinta.suljeLause(poistolause);
            YhteydenHallinta.suljeYhteys(yhteys);
        }
    }

}
