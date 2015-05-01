/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tietovarastopakkaus;

import datapakkaus.Tehtava;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author s1300778
 */
public class TehtavaTietovarasto extends Tietovarasto {

    @Override
    public List<Tehtava> haeTiedot() {
        List<Tehtava> tehtavat = new ArrayList<>();
        Connection yhteys = yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys != null) {
            PreparedStatement hakulause = null;
            ResultSet tulosjoukko = null;
            try {
                String hakuSql = "SELECT id, tehtava FROM tehtava;";
                hakulause = yhteys.prepareStatement(hakuSql);
                tulosjoukko = hakulause.executeQuery();

                while (tulosjoukko.next()) {
                    tehtavat.add(new Tehtava(tulosjoukko.getInt(1),
                            tulosjoukko.getString(2)));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                YhteydenHallinta.suljeTulosjoukko(tulosjoukko);
                YhteydenHallinta.suljeLause(hakulause);
                YhteydenHallinta.suljeYhteys(yhteys);
            }
        }
        return tehtavat;
    }

    @Override
    public void lisaaTieto(Object object) {
        Tehtava uusiTehtava = (Tehtava) object;
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return;
        }
        PreparedStatement lisayslause = null;
        try {
            String lisaaSql = "insert into tehtava "
                    + "(id,tehtava) values (?,?)";
            lisayslause = yhteys.prepareStatement(lisaaSql);

            lisayslause.setInt(1, uusiTehtava.getId());
            lisayslause.setString(2, uusiTehtava.getTehtava());
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
        Tehtava uusiTehtava = (Tehtava) object;
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return false;
        }
        PreparedStatement muutoslause = null;
        try {
            String muutaSql = "update tehtava "
                    + " set tehtava=? where id=?";
            muutoslause = yhteys.prepareStatement(muutaSql);

            muutoslause.setString(1, uusiTehtava.getTehtava());
            muutoslause.setInt(2, uusiTehtava.getId());
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
    public void poistaTieto(int tehtavaID) {
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return;
        }
        PreparedStatement poistolause = null;
        try {
            String poistoSql = "delete from tehtava where id=?";
            poistolause = yhteys.prepareStatement(poistoSql);
            poistolause.setInt(1, tehtavaID);

            poistolause.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            YhteydenHallinta.suljeLause(poistolause);
            YhteydenHallinta.suljeYhteys(yhteys);
        }
    }

}
