/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tietovarastopakkaus;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import datapakkaus.Puhelinnumero;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author s1300778
 */
public class PuhelinnumeroTietovarasto extends Tietovarasto {

    @Override
    public List<Puhelinnumero> haeTiedot() {
        List<Puhelinnumero> puhelinnumerot = new ArrayList<>();
        Connection yhteys = yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys != null) {
            PreparedStatement hakulause = null;
            ResultSet tulosjoukko = null;
            try {
                String hakuSql = "SELECT id, puhelinnumero, toimisto_id FROM puhelinnumero;";
                hakulause = yhteys.prepareStatement(hakuSql);
                tulosjoukko = hakulause.executeQuery();

                while (tulosjoukko.next()) {
                    puhelinnumerot.add(new Puhelinnumero(tulosjoukko.getInt(1),
                            tulosjoukko.getInt(2), tulosjoukko.getInt(3)));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                YhteydenHallinta.suljeTulosjoukko(tulosjoukko);
                YhteydenHallinta.suljeLause(hakulause);
                YhteydenHallinta.suljeYhteys(yhteys);
            }
        }
        return puhelinnumerot;
    }

    @Override
    public void lisaaTieto(Object object) {
        Puhelinnumero uusiPuhelinnumero = (Puhelinnumero) object;
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return;
        }
        PreparedStatement lisayslause = null;
        try {
            String lisaaSql = "insert into puhelinnumero "
                    + "(id,puhelinnumero,toimisto_id) values (?,?,?)";
            lisayslause = yhteys.prepareStatement(lisaaSql);

            lisayslause.setInt(1, uusiPuhelinnumero.getId());
            lisayslause.setInt(2, uusiPuhelinnumero.getPuhelinnumero());
            lisayslause.setInt(3, uusiPuhelinnumero.getToimistoID());
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
    public boolean muutaTietoja(Object object
    ) {
        Puhelinnumero uusiPuhelinnumero = (Puhelinnumero) object;
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return false;
        }
        PreparedStatement muutoslause = null;
        try {
            String muutaSql = "update puhelinnumero"
                    + " set puhelinnumero=?,toimisto_id=? "
                    + "where id=?";
            muutoslause = yhteys.prepareStatement(muutaSql);

            muutoslause.setInt(1, uusiPuhelinnumero.getPuhelinnumero());
            muutoslause.setInt(2, uusiPuhelinnumero.getToimistoID());
            muutoslause.setInt(3, uusiPuhelinnumero.getId());
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
    public void poistaTieto(int puhelinnumeroID
    ) {
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return;
        }
        PreparedStatement poistolause = null;
        try {
            String poistoSql = "delete from puhelinnumero where id=?";
            poistolause = yhteys.prepareStatement(poistoSql);
            poistolause.setInt(1, puhelinnumeroID);

            poistolause.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            YhteydenHallinta.suljeLause(poistolause);
            YhteydenHallinta.suljeYhteys(yhteys);
        }
    }

}
