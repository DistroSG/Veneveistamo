/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tietovarastopakkaus;

import datapakkaus.VeneTilaus;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *VeneTilaustietovarasto luokka jonka avulla muodostetaan yhteys oikeen tauluun kannassa.
 * 
 * @author Axel
 * @version 1.0
 */
public class VeneTilausTietovarasto extends Tietovarasto {
/**
 * Hakee kaikki tiedot VeneTilaus taulusta.
 * 
 * @return Venetilaus tiedot.
 */
    @Override
    public List<VeneTilaus> haeTiedot() {
        List<VeneTilaus> venetilaus = new ArrayList<>();
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys != null) {
            PreparedStatement hakulause = null;
            ResultSet tulosjoukko = null;
            try {
                String hakuSql = "SELECT venetilaus.id, vene_id, henkilosto_id, hinta, kuljetus_id,kuljetus.vastaanottaja , "
                        + "vari, edistyminen FROM venetilaus INNER JOIN kuljetus ON venetilaus.kuljetus_id=kuljetus.id;";
                hakulause = yhteys.prepareStatement(hakuSql);
                tulosjoukko = hakulause.executeQuery();

                while (tulosjoukko.next()) {
                    venetilaus.add(new VeneTilaus(tulosjoukko.getInt(1),
                            tulosjoukko.getInt(2), tulosjoukko.getInt(3), tulosjoukko.getDouble(4), tulosjoukko.getInt(5), tulosjoukko.getString(6), tulosjoukko.getString(7), tulosjoukko.getString(8)));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                YhteydenHallinta.suljeTulosjoukko(tulosjoukko);
                YhteydenHallinta.suljeLause(hakulause);
                YhteydenHallinta.suljeYhteys(yhteys);
            }
        }
        return venetilaus;
    }
/**
 * Lisätään uusi VeneTilaus tieto tietokantaan.
 * 
 * @param object VeneTilaus 
 */
    @Override
    public void lisaaTieto(Object object) {
        VeneTilaus uusiVeneTilaus = (VeneTilaus) object;
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return;
        }
        PreparedStatement lisayslause = null;
        try {
            String lisaaSql = "insert into venetilaus"
                    + "(id, vene_id, henkilosto_id, hinta, kuljetus_id, vari, edistyminen) values (?,?,?,?,?,?,?)";
            lisayslause = yhteys.prepareStatement(lisaaSql);

            lisayslause.setInt(1, uusiVeneTilaus.getId());
            lisayslause.setInt(2, uusiVeneTilaus.getVene_id());
            lisayslause.setInt(3, uusiVeneTilaus.getHenkilosto_id());
            lisayslause.setDouble(4, uusiVeneTilaus.getHinta());
            lisayslause.setInt(5, uusiVeneTilaus.getKuljetus_id());
            lisayslause.setString(6, uusiVeneTilaus.getVari());
            lisayslause.setString(7, uusiVeneTilaus.getEdistyminen());
            lisayslause.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            YhteydenHallinta.suljeLause(lisayslause);
            YhteydenHallinta.suljeYhteys(yhteys);
        }
    }
/**
 * Muutetaan tietoa VeneTilaus tietokannassa.
 * 
 * @param object VeneTilaus
 * @return palauttaa true, jos muuttaminen on onnistunut.
 */
    @Override
    public boolean muutaTietoja(Object object) {
        VeneTilaus uusiVeneTilaus = (VeneTilaus) object;
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return false;
        }
        PreparedStatement muutoslause = null;
        try {

            String muutaSql = "update venetilaus "
                    + "set vene_id=?,henkilosto_id=?,hinta=?,kuljetus_id=?,vari=?,edistyminen=? "
                    + "where id=?";
            muutoslause = yhteys.prepareStatement(muutaSql);

            muutoslause.setInt(7, uusiVeneTilaus.getId());
            muutoslause.setInt(1, uusiVeneTilaus.getVene_id());
            muutoslause.setInt(2, uusiVeneTilaus.getHenkilosto_id());
            muutoslause.setDouble(3, uusiVeneTilaus.getHinta());
            muutoslause.setInt(4, uusiVeneTilaus.getKuljetus_id());
            muutoslause.setString(5, uusiVeneTilaus.getVari());
            muutoslause.setString(6, uusiVeneTilaus.getEdistyminen());
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
 * Poistaa tiedon VeneTilaus taulusta.
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
            String poistoSql = "delete from venetilaus where id=?";
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
