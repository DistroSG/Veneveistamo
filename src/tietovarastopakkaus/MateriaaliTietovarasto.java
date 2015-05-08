/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tietovarastopakkaus;

import datapakkaus.Materiaali;
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
public class MateriaaliTietovarasto extends Tietovarasto {

    @Override
    public List<Materiaali> haeTiedot() {
        List<Materiaali> materiaalit = new ArrayList<>();
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys != null) {
            PreparedStatement hakulause = null;
            ResultSet tulosjoukko = null;
            try {
                String hakuSql = "SELECT id, materiaali FROM materiaali;";
                hakulause = yhteys.prepareStatement(hakuSql);
                tulosjoukko = hakulause.executeQuery();

                while (tulosjoukko.next()) {
                    materiaalit.add(new Materiaali(tulosjoukko.getString(2),
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
        return materiaalit;
    }

    @Override
    public void lisaaTieto(Object object) {
        Materiaali uusiMateriaali = (Materiaali) object;
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return;
        }
        PreparedStatement lisayslause = null;
        try {
            String lisaaSql = "insert into materiaali "
                    + "(id,materiaali) values (?,?)";
            lisayslause = yhteys.prepareStatement(lisaaSql);

            lisayslause.setInt(1, uusiMateriaali.getId());
            lisayslause.setString(2, uusiMateriaali.getMateriaali());
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
        Materiaali uusiMateriaali = (Materiaali) object;
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return false;
        }
        PreparedStatement muutoslause = null;
        try {
            String muutaSql = "update materiaali "
                    + " set materiaali=? where id=?";
            muutoslause = yhteys.prepareStatement(muutaSql);

            muutoslause.setString(1, uusiMateriaali.getMateriaali());
            muutoslause.setInt(2, uusiMateriaali.getId());
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
    public void poistaTieto(int MateriaaliID) {
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return;
        }
        PreparedStatement poistolause = null;
        try {
            String poistoSql = "delete from materiaali where id=?";
            poistolause = yhteys.prepareStatement(poistoSql);
            poistolause.setInt(1, MateriaaliID);

            poistolause.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            YhteydenHallinta.suljeLause(poistolause);
            YhteydenHallinta.suljeYhteys(yhteys);
        }
    }

}
