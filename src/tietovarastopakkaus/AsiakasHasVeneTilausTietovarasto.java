package tietovarastopakkaus;

import datapakkaus.AsiakasHasVeneTilaus;
import datapakkaus.AsiakasHasVeneTilausMuutos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AsiakasHasVeneTilausTietovarasto extends Tietovarasto{


    /**
     * Palautta kaikki HenkilostoHasTehtava yhteydet.
     *
     * @return kaikki HenkilostoHasTehtava yhteydet.
     */
    @Override
    public List<AsiakasHasVeneTilaus> haeTiedot() {
        List<AsiakasHasVeneTilaus> haeAsiakasHasVeneTilaus = new ArrayList<>();
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys != null) {
            PreparedStatement hakulause = null;
            ResultSet tulosjoukko = null;
            try {
                String hakuSql = "SELECT henkilosto_id, tehtava_id FROM asiakas_has_venetilaus;";
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
     * Lisätä uusi yhteys.
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
            String lisaaSql = "insert into asiakas_has_venetilaus "
                    + "(asiakas_id, venetilaus_id) values (?,?)";
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
     * @param object muuttuva yhteys HenkilostoHasTehtavaMuutos objektilla
     * @return palautta true, jos muuttaminen on onnistuttu.
     */
    @Override
    public boolean muutaTietoja(Object object) {
        if (object instanceof AsiakasHasVeneTilaus) {
            AsiakasHasVeneTilausMuutos apu = (AsiakasHasVeneTilausMuutos) object;
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
    private boolean muutaTietoja(AsiakasHasVeneTilaus uusiAsiakasHasVeneTilaus, AsiakasHasVeneTilaus vanhaAsiakasHasVeneTilaus) {
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return false;
        }
        PreparedStatement muutoslause = null;
        try {
            String muutaSql = "update asiakas_has_venetilaus "
                    + " set venetilaus_id=? where asiakas_id=? and venetilaus_id=?";
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
     * Poista kaikki merkitty henkilön yhteydet.
     *
     *
     * @param asiakasID poistettavien yhteyksien henkilön id
     */
    @Override
    public void poistaTieto(int asiakasID) {
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return;
        }
        PreparedStatement poistolause = null;
        try {
            String poistoSql = "delete from asiakas_has_venetilaus where venetilaus_id=?";
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
     * @param henkilostoID poistettavan yhteyden henkilön id
     * @param tehtavaID poistettavan yhteyden tehtävän id
     */
    public void poistaTieto(int venetilausID, int asiakasID) {
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return;
        }
        PreparedStatement poistolause = null;
        try {
            String poistoSql = "delete from asiakas_has_venetilaus where asiakas_id=? AND venetilaus_id=?  ";
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

