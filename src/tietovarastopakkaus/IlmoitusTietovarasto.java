/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tietovarastopakkaus;

import datapakkaus.Ilmoitus;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * IlmoitusTietovarasto luokka jonka avulla muodostetaan yhteys oikeean tauluun tietokannassa.
 *
 * @author s1300727
 * @version 1.0
 */
public class IlmoitusTietovarasto extends Tietovarasto{
/**
 * Hakee kaikki tiedot Ilmoitus taulusta.
 * 
 * @return Ilmoitus tiedot 
 */
    @Override
    public List<Ilmoitus> haeTiedot() {
                List<Ilmoitus> ilmoitukset = new ArrayList<>();
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys != null) {
            PreparedStatement hakulause = null;
            ResultSet tulosjoukko = null;
            try {
                String hakuSql = "SELECT id, ilmotus, hinta, veneTilaus_id FROM ilmotus;";
                hakulause = yhteys.prepareStatement(hakuSql);
                tulosjoukko = hakulause.executeQuery();

                while (tulosjoukko.next()) {
                    ilmoitukset.add(new Ilmoitus(tulosjoukko.getInt(1),
                            tulosjoukko.getString(2), tulosjoukko.getDouble(3), tulosjoukko.getInt(4)));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                YhteydenHallinta.suljeTulosjoukko(tulosjoukko);
                YhteydenHallinta.suljeLause(hakulause);
                YhteydenHallinta.suljeYhteys(yhteys);
            }
        }
        return ilmoitukset;
    }
/**
 * Lisätään uusi Ilmoitus tieto tietokantaan.
 * 
 * @param object ilmoitus
 */
    @Override
    public void lisaaTieto(Object object) {
         Ilmoitus uusiIlmoitus = (Ilmoitus) object;
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return;
        }
        PreparedStatement lisayslause = null;
        try {
            String lisaaSql = "insert into ilmotus"
                    + "(id, ilmotus, hinta, veneTilaus_id) values (?,?,?,?)";
            lisayslause = yhteys.prepareStatement(lisaaSql);

            lisayslause.setInt(1, uusiIlmoitus.getId());
            lisayslause.setString(2, uusiIlmoitus.getIlmoitus());
            lisayslause.setDouble(3, uusiIlmoitus.getHinta());
            lisayslause.setInt(4, uusiIlmoitus.getVenetilaus_id());
            lisayslause.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            YhteydenHallinta.suljeLause(lisayslause);
            YhteydenHallinta.suljeYhteys(yhteys);
        }
    }
    /**
     * Muuttaa Ilmoitus tietoja tietokannassa
     * 
     * @param object ilmoitus
     * @return palauttaa true, jos muuttaminen on onnistunut.
     */

    @Override
    public boolean muutaTietoja(Object object) {
                Ilmoitus uusiIlmoitus = (Ilmoitus) object;
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return false;
        }
        PreparedStatement muutoslause = null;
        try {

            String muutaSql = "update ilmotus "
                    + "set ilmotus=?,hinta=?,veneTilaus_id=?"
                    + "where id=?";
            muutoslause = yhteys.prepareStatement(muutaSql);

            muutoslause.setInt(4, uusiIlmoitus.getId());
            muutoslause.setString(1, uusiIlmoitus.getIlmoitus());
            muutoslause.setDouble(2, uusiIlmoitus.getHinta());
            muutoslause.setInt(3, uusiIlmoitus.getVenetilaus_id());
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
 * Poistaa ilmoituksen tietokannasta ID:en perusteella.
 * 
 * @param id poistetaan tieto ID:en perusteella. 
 */
    @Override
    public void poistaTieto(int id) {
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return;
        }
        PreparedStatement poistolause = null;
        try {
            String poistoSql = "delete from ilmotus where id=?";
            poistolause = yhteys.prepareStatement(poistoSql);
            poistolause.setInt(1, id);

            poistolause.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            YhteydenHallinta.suljeLause(poistolause);
            YhteydenHallinta.suljeYhteys(yhteys);
        }
    }    }
    

