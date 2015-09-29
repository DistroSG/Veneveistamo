package tietovarastopakkaus;

import datapakkaus.AsiakasHasVeneTilaus;
import datapakkaus.AsiakasHasVeneTilausMuutos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AsiakasHasVeneTilausTietovarasto extends Tietovarasto {

    /**
     * Palautta kaikki AsiakasHasVeneTilaus yhteydet.
     *
     * @return kaikki AsiakasHasVeneTilaus yhteydet.
     */
    @Override
    public List<AsiakasHasVeneTilaus> haeTiedot() {
        List<AsiakasHasVeneTilaus> haeAsiakasHasVeneTilaus = new ArrayList<>();
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys != null) {
            PreparedStatement hakulause = null;
            ResultSet tulosjoukko = null;
            try {
                String hakuSql = "SELECT asiakas_id, veneTilaus_id FROM asiakas_has_veneTilaus;";
                hakulause = yhteys.prepareStatement(hakuSql);
                tulosjoukko = hakulause.executeQuery();

                while (tulosjoukko.next()) {
                    haeAsiakasHasVeneTilaus.add(new AsiakasHasVeneTilaus(tulosjoukko.getInt(1),
                            tulosjoukko.getInt(2)));
                }
            } catch (Exception e) {
            } finally {
                YhteydenHallinta.suljeTulosjoukko(tulosjoukko);
                YhteydenHallinta.suljeLause(hakulause);
                YhteydenHallinta.suljeYhteys(yhteys);
            }
        }
        return haeAsiakasHasVeneTilaus;
    }

    /**
     * Lisätään uusi yhteys.
     *
     * @param object objekti, joka lisätään.
     */
    @Override
    public void lisaaTieto(Object object) {
        AsiakasHasVeneTilaus uusiAsiakasHasVeneTilaus = (AsiakasHasVeneTilaus) object;
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return;
        }
        PreparedStatement lisayslause = null;
        try {
            String lisaaSql = "insert into asiakas_has_veneTilaus "
                    + "(asiakas_id, veneTilaus_id) values (?,?)";
            lisayslause = yhteys.prepareStatement(lisaaSql);

            lisayslause.setInt(1, uusiAsiakasHasVeneTilaus.getAsiakasID());
            lisayslause.setInt(2, uusiAsiakasHasVeneTilaus.getVeneTilausID());
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
     * @param object muuttuva yhteys AsiakasHasVeneTilausMuutos objektilla
     * @return palautta true, jos muuttaminen on onnistuttu.
     */
    @Override
    public boolean muutaTietoja(Object object) {
        if (object instanceof AsiakasHasVeneTilausMuutos) {
            AsiakasHasVeneTilausMuutos apu = (AsiakasHasVeneTilausMuutos) object;
            return muutaTietoja(apu.getUusi(), apu.getVanha());
        }
        return false;
    }

    /**
     *
     *
     * @param uusiAsiakasHasVeneTilaus uusi yhetys. Esim "new
     * uusiAsiakasHasVeneTilaus(1,1)"
     * @param vanhaAsiakasHasVeneTilaus vanha yhetys. Esim. "new
     * vanhaAsiakasHasVeneTilaus(1,2)"
     * @return palautta true, jos muuttaminen on onnistuttu.
     */
    private boolean muutaTietoja(AsiakasHasVeneTilaus uusiAsiakasHasVeneTilaus, AsiakasHasVeneTilaus vanhaAsiakasHasVeneTilaus) {
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return false;
        }
        PreparedStatement muutoslause = null;
        try {
            String muutaSql = "update asiakas_has_veneTilaus "
                    + " set veneTilaus_id=? where asiakas_id=? and veneTilaus_id=?";
            muutoslause = yhteys.prepareStatement(muutaSql);

            muutoslause.setInt(1, uusiAsiakasHasVeneTilaus.getVeneTilausID());
            muutoslause.setInt(2, vanhaAsiakasHasVeneTilaus.getAsiakasID());
            muutoslause.setInt(3, vanhaAsiakasHasVeneTilaus.getVeneTilausID());
            return muutoslause.executeUpdate() > 0;
        } catch (Exception e) {
            return false;
        } finally {
            YhteydenHallinta.suljeLause(muutoslause);
            YhteydenHallinta.suljeYhteys(yhteys);
        }
    }

    /**
     * Poista kaikki merkityt asiakkaan yhteydet.
     *
     *
     * @param asiakasID poistettavien yhteyksien veneTilausID
     */
    @Override
    public void poistaTieto(int asiakasID) {
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return;
        }
        PreparedStatement poistolause = null;
        try {
            String poistoSql = "delete from asiakas_has_venetilaus where veneTilaus_id=?";
            poistolause = yhteys.prepareStatement(poistoSql);
            poistolause.setInt(1, asiakasID);

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
     * @param asiakasID poistettavan yhteyden asiakas id
     * @param venetilausID poistettavan yhteyden venetilaus id
     */
    public void poistaTieto(int venetilausID, int asiakasID) {
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return;
        }
        PreparedStatement poistolause = null;
        try {
            String poistoSql = "delete from asiakas_has_veneTilaus where asiakas_id=? AND veneTilaus_id=?  ";
            poistolause = yhteys.prepareStatement(poistoSql);
            poistolause.setInt(1, venetilausID);
            poistolause.setInt(2, asiakasID);

            poistolause.executeUpdate();
        } catch (Exception e) {

        } finally {
            YhteydenHallinta.suljeLause(poistolause);
            YhteydenHallinta.suljeYhteys(yhteys);
        }
    }
}
