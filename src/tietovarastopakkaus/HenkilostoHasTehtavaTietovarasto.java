/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tietovarastopakkaus;

import datapakkaus.HenkilostoHasTehtava;
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
public class HenkilostoHasTehtavaTietovarasto extends Tietovarasto {

    @Override
    public List<HenkilostoHasTehtava> haeTiedot() {
        List<HenkilostoHasTehtava> haeHenkilostoHasTehtava = new ArrayList<>();
        Connection yhteys = yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys != null) {
            PreparedStatement hakulause = null;
            ResultSet tulosjoukko = null;
            try {
                String hakuSql = "SELECT henkilosto_id, tehtava_id FROM henkilosto_has_tehtava;";
                hakulause = yhteys.prepareStatement(hakuSql);
                tulosjoukko = hakulause.executeQuery();

                while (tulosjoukko.next()) {
                    haeHenkilostoHasTehtava.add(new HenkilostoHasTehtava(tulosjoukko.getInt(1),
                            tulosjoukko.getInt(2)));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                YhteydenHallinta.suljeTulosjoukko(tulosjoukko);
                YhteydenHallinta.suljeLause(hakulause);
                YhteydenHallinta.suljeYhteys(yhteys);
            }
        }
        return haeHenkilostoHasTehtava;
    }

    @Override
    public void lisaaTieto(Object object) {
        HenkilostoHasTehtava uusiHenkilostoHasTehtava = (HenkilostoHasTehtava) object;
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return;
        }
        PreparedStatement lisayslause = null;
        try {
            String lisaaSql = "insert into henkilosto_has_tehtava "
                    + "(henkilosto_id, tehtava_id) values (?,?)";
            lisayslause = yhteys.prepareStatement(lisaaSql);

            lisayslause.setInt(1, uusiHenkilostoHasTehtava.getHenkilostoID());
            lisayslause.setInt(2, uusiHenkilostoHasTehtava.getTehtavaID());
            lisayslause.executeUpdate();
        } catch (SQLException ex) {
                if (ex.getErrorCode() == 1062) {
                   hastaulukkoIdVirheIlmoitus();
                }
        } finally {
            YhteydenHallinta.suljeLause(lisayslause);
            YhteydenHallinta.suljeYhteys(yhteys);
        }
    }

    @Override
    public boolean muutaTietoja(Object object) {
        return false;
    }

    public boolean muutaTietoja(HenkilostoHasTehtava uusiHenkilostoHasTehtava, HenkilostoHasTehtava vanhaHenkilostoHasTehtava) {
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return false;
        }
        PreparedStatement muutoslause = null;
        try {
            String muutaSql = "update henkilosto_has_tehtava "
                    + " set tehtava_id=? where henkilosto_id=? and tehtava_id=?";
            muutoslause = yhteys.prepareStatement(muutaSql);

            muutoslause.setInt(1, uusiHenkilostoHasTehtava.getTehtavaID());
            muutoslause.setInt(2, vanhaHenkilostoHasTehtava.getHenkilostoID());
            muutoslause.setInt(3, vanhaHenkilostoHasTehtava.getTehtavaID());
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
    public void poistaTieto(int id) {
    }

    public void poistaTieto(int henkilostoID, int tehtavaID) {
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return;
        }
        PreparedStatement poistolause = null;
        try {
            String poistoSql = "delete from henkilosto_has_tehtava where henkilosto_id=? AND tehtava_id=?  ";
            poistolause = yhteys.prepareStatement(poistoSql);
            poistolause.setInt(1, henkilostoID);
            poistolause.setInt(2, tehtavaID);

            poistolause.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            YhteydenHallinta.suljeLause(poistolause);
            YhteydenHallinta.suljeYhteys(yhteys);
        }
    }
}
