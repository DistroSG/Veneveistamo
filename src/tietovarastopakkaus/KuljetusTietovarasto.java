/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tietovarastopakkaus;

import datapakkaus.Kuljetus;
import datapakkaus.Maksu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * KuljetusTietovarasto luokka jonka avulla muodostetaan yhteys oikeean tauluun.
 *
 * @author s1300727
 * @version 1.0
 */
public class KuljetusTietovarasto extends Tietovarasto{
/**
 * Hakee kaikki tiedot Kuljetus taulusta.
 * 
 * @return Kuljetus tiedot
 */
    @Override
    public List<Kuljetus> haeTiedot() {
        List<Kuljetus> kuljetukset = new ArrayList<>();
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys != null) {
            PreparedStatement hakulause = null;
            ResultSet tulosjoukko = null;
            try {
                String hakuSql = "SELECT id, vastaanottaja, vastaanotto FROM kuljetus;";
                hakulause = yhteys.prepareStatement(hakuSql);
                tulosjoukko = hakulause.executeQuery();

                while (tulosjoukko.next()) {
                    kuljetukset.add(new Kuljetus(tulosjoukko.getInt(1),
                            tulosjoukko.getString(2), tulosjoukko.getString(3)));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                YhteydenHallinta.suljeTulosjoukko(tulosjoukko);
                YhteydenHallinta.suljeLause(hakulause);
                YhteydenHallinta.suljeYhteys(yhteys);
            }
        }
        return kuljetukset;    }
/**
 * Lisää tietoa kuljetus tauluun.
 * 
 * @param object Kuljetus
 */
    @Override
    public void lisaaTieto(Object object) {
        Kuljetus uusiKuljetus = (Kuljetus) object;
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return;
        }
        PreparedStatement lisayslause = null;
        try {
            String lisaaSql = "insert into kuljetus"
                    + "(id, vastaanottaja, vastaanotto) values (?,?,?)";
            lisayslause = yhteys.prepareStatement(lisaaSql);

            lisayslause.setInt(1, uusiKuljetus.getId());
            lisayslause.setString(2, uusiKuljetus.getVastaanottaja());
            lisayslause.setString(3, uusiKuljetus.getVastaanotto());

            lisayslause.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            YhteydenHallinta.suljeLause(lisayslause);
            YhteydenHallinta.suljeYhteys(yhteys);
        }    }
/**
 * Muutetaan tietoa Kuljetus taulussa.
 * 
 * @param object
 * @return palauttaa true jos muutos on onnistunut. 
 */
    
    @Override
    public boolean muutaTietoja(Object object) {
              Kuljetus uusiKuljetus = (Kuljetus) object;
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return false;
        }
        PreparedStatement muutoslause = null;
        try {

            String muutaSql = "update kuljetus "
                    + "set vastaanottaja=?,vastaanotto=? "
                    + "where id=?";
            muutoslause = yhteys.prepareStatement(muutaSql);

            muutoslause.setInt(3, uusiKuljetus.getId());
            muutoslause.setString(1, uusiKuljetus.getVastaanottaja());
            muutoslause.setString(2, uusiKuljetus.getVastaanotto());

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
     * Poistaa tiedon Kuljetus taulusta
     * @param id poistaa tiedon ID:en perusteella
     */
    
    @Override
    public void poistaTieto(int id) {
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return;
        }
        PreparedStatement poistolause = null;
        try {
            String poistoSql = "delete from kuljetus where id=?";
            poistolause = yhteys.prepareStatement(poistoSql);
            poistolause.setInt(1, id);

            poistolause.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            YhteydenHallinta.suljeLause(poistolause);
            YhteydenHallinta.suljeYhteys(yhteys);
        }
    }
    
    
}
