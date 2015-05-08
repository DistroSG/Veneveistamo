package tietovarastopakkaus;

import datapakkaus.Henkilosto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * HenkilostoTietovarasto luokka. Jolla avulla saadaan tietokantayhteys ja
 * tiedot HenkilostoHasTehtava taulukosta.
 *
 * @author s1300778
 * @version 1.0
 */
public class HenkilostoTietovarasto extends Tietovarasto {

    /**
     * Palautta kaikki henkilöiden tiedot.
     *
     * @return kaikki henkilöiden tiedot.
     */
    @Override
    public List<Henkilosto> haeTiedot() {
        List<Henkilosto> henkilot = new ArrayList<>();
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys != null) {
            PreparedStatement hakulause = null;
            ResultSet tulosjoukko = null;
            try {
                String hakuSql = "SELECT id, sukunimi, etunimi, osasto, toimisto_id FROM henkilosto;";
                hakulause = yhteys.prepareStatement(hakuSql);
                tulosjoukko = hakulause.executeQuery();

                while (tulosjoukko.next()) {
                    henkilot.add(new Henkilosto(tulosjoukko.getInt(1),
                            tulosjoukko.getString(2), tulosjoukko.getString(3), tulosjoukko.getString(4), tulosjoukko.getInt(5)));
                }
            } catch (SQLException ex) {
                if (ex.getErrorCode() == 1062) {
                    idVirheIlmoitus();
                }
            } finally {
                YhteydenHallinta.suljeTulosjoukko(tulosjoukko);
                YhteydenHallinta.suljeLause(hakulause);
                YhteydenHallinta.suljeYhteys(yhteys);
            }
        }
        return henkilot;
    }

    /**
     * Lisätä uusi henkilö.
     *
     * @param object henkilö, joka lisätään.
     */
    @Override
    public void lisaaTieto(Object object) {
        Henkilosto uusiHenkilo = (Henkilosto) object;
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return;
        }
        PreparedStatement lisayslause = null;
        try {
            String lisaaSql = "insert into henkilosto"
                    + "(id, sukunimi, etunimi, osasto, toimisto_id) values (?,?,?,?,?)";
            lisayslause = yhteys.prepareStatement(lisaaSql);

            lisayslause.setInt(1, uusiHenkilo.getId());
            lisayslause.setString(2, uusiHenkilo.getSukunimi());
            lisayslause.setString(3, uusiHenkilo.getEtunimi());
            lisayslause.setString(4, uusiHenkilo.getOsasto());
            lisayslause.setInt(5, uusiHenkilo.getToimistoID());
            lisayslause.executeUpdate();
        } catch (Exception e) {
        } finally {
            YhteydenHallinta.suljeLause(lisayslause);
            YhteydenHallinta.suljeYhteys(yhteys);
        }
    }

    /**
     * Muuta henkilön tiedot.
     *
     * @param object hinkilö, joka muutetaan.
     * @return palautta true, jos muuttaminen on onnistuttu.
     */
    @Override
    public boolean muutaTietoja(Object object) {
        Henkilosto uusiHenkilo = (Henkilosto) object;
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return false;
        }
        PreparedStatement muutoslause = null;
        try {

            String muutaSql = "update henkilosto "
                    + "set sukunimi=?,etunimi=?,osasto=?,toimisto_id=? "
                    + "where id=?";
            muutoslause = yhteys.prepareStatement(muutaSql);

            muutoslause.setString(1, uusiHenkilo.getSukunimi());
            muutoslause.setString(2, uusiHenkilo.getEtunimi());
            muutoslause.setString(3, uusiHenkilo.getOsasto());
            muutoslause.setInt(4, uusiHenkilo.getToimistoID());
            muutoslause.setInt(5, uusiHenkilo.getId());
            return muutoslause.executeUpdate() > 0;
        } catch (Exception e) {

            return false;
        } finally {
            YhteydenHallinta.suljeLause(muutoslause);
            YhteydenHallinta.suljeYhteys(yhteys);
        }
    }

    /**
     * Poista henkilö.
     *
     * @param henkiloID poistettavan henkilön id. Esim. "1"
     */
    @Override
    public void poistaTieto(int henkiloID) {
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return;
        }
        PreparedStatement poistolause = null;
        try {
            String poistoSql = "delete from henkilosto where id=?";
            poistolause = yhteys.prepareStatement(poistoSql);
            poistolause.setInt(1, henkiloID);

            poistolause.executeUpdate();
        } catch (Exception e) {
        } finally {
            YhteydenHallinta.suljeLause(poistolause);
            YhteydenHallinta.suljeYhteys(yhteys);
        }
    }

}
