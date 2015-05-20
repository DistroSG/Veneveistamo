package tietovarastopakkaus;

import datapakkaus.HenkilostoHasTehtava;
import datapakkaus.HenkilostoHasTehtavaMuutos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * HenkilostoHasTehtavaTietovarasto luokka. Jolla avulla saadaan
 * tietokantayhteys ja tiedot HenkilostoHasTehtava taulukosta.
 *
 * @author s1300778
 * @version 1.0
 */
public class HenkilostoHasTehtavaTietovarasto extends Tietovarasto {

    /**
     * Palautta kaikki HenkilostoHasTehtava yhteydet.
     *
     * @return kaikki HenkilostoHasTehtava yhteydet.
     */
    @Override
    public List<HenkilostoHasTehtava> haeTiedot() {
        List<HenkilostoHasTehtava> haeHenkilostoHasTehtava = new ArrayList<>();
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys != null) {
            PreparedStatement hakulause = null;
            ResultSet tulosjoukko = null;
            try {
                String hakuSql = "SELECT henkilosto_has_tehtava.henkilosto_id,  CONCAT(henkilosto.sukunimi, \" \" , henkilosto.etunimi) AS Nimi , henkilosto_has_tehtava.tehtava_id, tehtava.tehtava FROM veneveistamo.henkilosto_has_tehtava\n"
                        + "Join veneveistamo.henkilosto on henkilosto.id = henkilosto_has_tehtava.henkilosto_id\n"
                        + "Join veneveistamo.tehtava on tehtava.id = henkilosto_has_tehtava.tehtava_id "
                        + "ORDER BY henkilosto_has_tehtava.henkilosto_id;";
                hakulause = yhteys.prepareStatement(hakuSql);
                tulosjoukko = hakulause.executeQuery();

                while (tulosjoukko.next()) {
                    haeHenkilostoHasTehtava.add(new HenkilostoHasTehtava(tulosjoukko.getInt(1), tulosjoukko.getString(2),
                            tulosjoukko.getInt(3), tulosjoukko.getString(4)));
                }
            } catch (Exception e) {
            } finally {
                YhteydenHallinta.suljeTulosjoukko(tulosjoukko);
                YhteydenHallinta.suljeLause(hakulause);
                YhteydenHallinta.suljeYhteys(yhteys);
            }
        }
        return haeHenkilostoHasTehtava;
    }

    /**
     * Lisätä uusi yhteys.
     *
     * @param object objekti, joka lisätään.
     */
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

    /**
     * Muuta yhteys.
     *
     * @param object muuttuva yhteys HenkilostoHasTehtavaMuutos objektilla
     * @return palautta true, jos muuttaminen on onnistuttu.
     */
    @Override
    public boolean muutaTietoja(Object object) {
        if (object instanceof HenkilostoHasTehtavaMuutos) {
            HenkilostoHasTehtavaMuutos apu = (HenkilostoHasTehtavaMuutos) object;
            return muutaTietoja(apu.getUusi(), apu.getVanha());
        }
        return false;
    }

    /**
     *
     *
     * @param uusiHenkilostoHasTehtava uusi yhetys. Esim "new
     * uusiHenkilostoHasTehtava(1,1)"
     * @param vanhaHenkilostoHasTehtava vanha yhetys. Esim. "new
     * uusiHenkilostoHasTehtava(1,2)"
     * @return palautta true, jos muuttaminen on onnistuttu.
     */
    private boolean muutaTietoja(HenkilostoHasTehtava uusiHenkilostoHasTehtava, HenkilostoHasTehtava vanhaHenkilostoHasTehtava) {
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
            return muutoslause.executeUpdate() > 0;
        } catch (Exception e) {
            return false;
        } finally {
            YhteydenHallinta.suljeLause(muutoslause);
            YhteydenHallinta.suljeYhteys(yhteys);
        }
    }

    /**
     * Poista kaikki merkitty henkilön yhteydet.
     *
     *
     * @param henkilostoID poistettavien yhteyksien henkilön id
     */
    @Override
    public void poistaTieto(int henkilostoID) {
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return;
        }
        PreparedStatement poistolause = null;
        try {
            String poistoSql = "delete from henkilosto_has_tehtava where henkilosto_id=?";
            poistolause = yhteys.prepareStatement(poistoSql);
            poistolause.setInt(1, henkilostoID);

            poistolause.executeUpdate();
        } catch (Exception e) {

        } finally {
            YhteydenHallinta.suljeLause(poistolause);
            YhteydenHallinta.suljeYhteys(yhteys);
        }
    }

    /**
     * Poista konkreettinen yhteys.
     *
     * @param henkilostoID poistettavan yhteyden henkilön id
     * @param tehtavaID poistettavan yhteyden tehtävän id
     */
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

        } finally {
            YhteydenHallinta.suljeLause(poistolause);
            YhteydenHallinta.suljeYhteys(yhteys);
        }
    }
}
