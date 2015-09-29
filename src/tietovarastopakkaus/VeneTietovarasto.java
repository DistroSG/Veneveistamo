package tietovarastopakkaus;

import datapakkaus.Vene;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * VeneTietovarasto luokka. Jonka avulla saadaan tietokantayhteys ja tiedot
 * VeneHasPerusvarit ja VeneHasMateriaalit taulukoista.
 *
 * @author s1300723
 * @version 1.0
 */
public class VeneTietovarasto extends Tietovarasto {

    /**
     * Palautta kaikki veneiden tiedot.
     *
     * @return kaikki veneiden tiedot.
     */
    @Override
    public List<Vene> haeTiedot() {
        List<Vene> veneet = new ArrayList<>();
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys != null) {
            PreparedStatement hakulause = null;
            ResultSet tulosjoukko = null;
            try {
                String hakuSql = "SELECT vene.id, malliID, malli.malli, takuuID, vene.hinta, alv FROM vene INNER JOIN malli ON vene.malliID=malli.id;";
                hakulause = yhteys.prepareStatement(hakuSql);
                tulosjoukko = hakulause.executeQuery();

                while (tulosjoukko.next()) {
                    veneet.add(new Vene(tulosjoukko.getInt(1),
                            tulosjoukko.getInt(2), tulosjoukko.getString(3), tulosjoukko.getInt(4), tulosjoukko.getInt(5), tulosjoukko.getInt(6)));

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
        return veneet;
    }

    /**
     * Lisätä uusi vene.
     *
     * @param object vene, joka lisätään.
     */
    @Override
    public void lisaaTieto(Object object) {
        Vene uusiVene = (Vene) object;
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return;
        }
        PreparedStatement lisayslause = null;
        try {
            String lisaaSql = "insert into vene"
                    + "(id, malliID, takuuID, hinta, alv) values (?,?,?,?,?)";
            lisayslause = yhteys.prepareStatement(lisaaSql);

            lisayslause.setInt(1, uusiVene.getId());
            lisayslause.setInt(2, uusiVene.getMalliID());
            lisayslause.setInt(3, uusiVene.getTakuuID());
            lisayslause.setInt(4, uusiVene.getHinta());
            lisayslause.setInt(5, uusiVene.getAlv());
            lisayslause.executeUpdate();
        } catch (Exception e) {
        } finally {
            YhteydenHallinta.suljeLause(lisayslause);
            YhteydenHallinta.suljeYhteys(yhteys);
        }
    }

    /**
     * Muuta veneen tiedot.
     *
     * @param object vene, joka muutetaan.
     * @return palauttaa true, jos muuttaminen on onnistunut.
     */
    @Override
    public boolean muutaTietoja(Object object) {
        Vene uusiVene = (Vene) object;
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return false;
        }
        PreparedStatement muutoslause = null;
        try {

            String muutaSql = "update vene "
                    + "set malliID=?,takuuID=?,hinta=?,alv=? "
                    + "where id=?";
            muutoslause = yhteys.prepareStatement(muutaSql);

            muutoslause.setInt(1, uusiVene.getMalliID());
            muutoslause.setInt(2, uusiVene.getTakuuID());
            muutoslause.setInt(3, uusiVene.getHinta());
            muutoslause.setInt(4, uusiVene.getAlv());
            muutoslause.setInt(5, uusiVene.getId());
            return muutoslause.executeUpdate() > 0;
        } catch (Exception e) {
            return false;
        } finally {
            YhteydenHallinta.suljeLause(muutoslause);
            YhteydenHallinta.suljeYhteys(yhteys);
        }
    }

    /**
     * Poista vene.
     *
     * @param veneID poistettavan veneen id. Esim. "1"
     */
    @Override
    public void poistaTieto(int veneID) {
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return;
        }
        PreparedStatement poistolause = null;
        try {
            String poistoSql = "delete from vene where id=?";
            poistolause = yhteys.prepareStatement(poistoSql);
            poistolause.setInt(1, veneID);

            poistolause.executeUpdate();
        } catch (Exception e) {
        } finally {
            YhteydenHallinta.suljeLause(poistolause);
            YhteydenHallinta.suljeYhteys(yhteys);
        }
    }

}
