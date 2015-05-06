package tietovarastopakkaus;

import datapakkaus.Puhelinnumero;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * PuhelinnumeroTietovarasto luokka. Jolla avulla saadaan tietokantayhteys ja
 * tiedot Puhelinnumero taulukosta.
 *
 * @author s1300778
 * @version 1.0
 */
public class PuhelinnumeroTietovarasto extends Tietovarasto {

    /**
     * Palautta kaikki puhelinnumeroiden tiedot.
     *
     * @return kaikki puhelinnumeroiden tiedot.
     */
    @Override
    public List<Puhelinnumero> haeTiedot() {
        List<Puhelinnumero> puhelinnumerot = new ArrayList<>();
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
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
            } finally {
                YhteydenHallinta.suljeTulosjoukko(tulosjoukko);
                YhteydenHallinta.suljeLause(hakulause);
                YhteydenHallinta.suljeYhteys(yhteys);
            }
        }
        return puhelinnumerot;
    }

    /**
     * Lisätä uusi puhelinnumero.
     *
     * @param object puhelinnumero, joka lisätään.
     */
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

    /**
     * Muuta puhelinnumeron tiedot.
     *
     * @param object puhelinnumero, joka muutetaan.
     * @return palautta true, jos muuttaminen on onnistuttu.
     */
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
            return muutoslause.executeUpdate() > 0;
        } catch (Exception e) {
            return false;
        } finally {
            YhteydenHallinta.suljeLause(muutoslause);
            YhteydenHallinta.suljeYhteys(yhteys);
        }
    }

    /**
     * Poista puhelinnumero.
     *
     * @param puhelinnumeroID poistettavan puhelinnumeron id. Esim. "1"
     */
    @Override
    public void poistaTieto(int puhelinnumeroID) {
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
        } finally {
            YhteydenHallinta.suljeLause(poistolause);
            YhteydenHallinta.suljeYhteys(yhteys);
        }
    }

}
