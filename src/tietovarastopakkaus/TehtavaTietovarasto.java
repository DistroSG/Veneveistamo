package tietovarastopakkaus;

import datapakkaus.Tehtava;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * TehtavaTietovarasto luokka. Jolla avulla saadaan tietokantayhteys ja tiedot
 * Tehtava taulukosta.
 *
 * @author s1300778
 * @version 1.0
 */
public class TehtavaTietovarasto extends Tietovarasto {

    /**
     * Palautta kaikki tehtävän tiedot.
     *
     * @return kaikki tehtävän tiedot.
     */
    @Override
    public List<Tehtava> haeTiedot() {
        List<Tehtava> tehtavat = new ArrayList<>();
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
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
            } finally {
                YhteydenHallinta.suljeTulosjoukko(tulosjoukko);
                YhteydenHallinta.suljeLause(hakulause);
                YhteydenHallinta.suljeYhteys(yhteys);
            }
        }
        return tehtavat;
    }

    /**
     * Lisätä uusi tehtävä.
     *
     * @param object tehtävä, joka lisätään.
     */
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
     * Muuta tehtävän tiedot.
     *
     * @param object tehtävä, joka muutetaan.
     * @return palautta true, jos muuttaminen on onnistuttu.
     */
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
     * @param tehtavaID poistettavan tehtävän id. Esim. "1"
     */
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
        } finally {
            YhteydenHallinta.suljeLause(poistolause);
            YhteydenHallinta.suljeYhteys(yhteys);
        }
    }

}
