package tietovarastopakkaus;

import datapakkaus.VeneHasPerusvarit;
import datapakkaus.VeneHasPerusvaritMuutos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * VeneHasPerusvaritTietovarasto luokka. Jonka avulla saadaan
 * tietokantayhteys ja tiedot VeneHasPerusvarit taulukosta.
 *
 * @author s1300723
 * @version 1.0
 */
public class VeneHasPerusvaritTietovarasto extends Tietovarasto {

    /**
     * Palauttaa kaikki VeneHasPerusvarit yhteydet.
     *
     * @return kaikki VeneHasPerusvarit yhteydet.
     */
    @Override
    public List<VeneHasPerusvarit> haeTiedot() {
        List<VeneHasPerusvarit> haeVeneHasPerusvarit = new ArrayList<>();
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys != null) {
            PreparedStatement hakulause = null;
            ResultSet tulosjoukko = null;
            try {
                String hakuSql = "SELECT vene_id, perusvarit_id FROM vene_has_perusvarit;";
                hakulause = yhteys.prepareStatement(hakuSql);
                tulosjoukko = hakulause.executeQuery();

                while (tulosjoukko.next()) {
                    haeVeneHasPerusvarit.add(new VeneHasPerusvarit(tulosjoukko.getInt(1),
                            tulosjoukko.getInt(2)));
                }
            } catch (Exception e) {
            } finally {
                YhteydenHallinta.suljeTulosjoukko(tulosjoukko);
                YhteydenHallinta.suljeLause(hakulause);
                YhteydenHallinta.suljeYhteys(yhteys);
            }
        }
        return haeVeneHasPerusvarit;
    }

    /**
     * Lisää uuden yhteyden.
     *
     * @param object objekti, joka lisätään.
     */
    @Override
    public void lisaaTieto(Object object) {
        VeneHasPerusvarit uusiVeneHasPerusvarit = (VeneHasPerusvarit) object;
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return;
        }
        PreparedStatement lisayslause = null;
        try {
            String lisaaSql = "insert into vene_has_materiaali "
                    + "(vene_id, materiaali_id) values (?,?)";
            lisayslause = yhteys.prepareStatement(lisaaSql);

            lisayslause.setInt(1, uusiVeneHasPerusvarit.getVeneID());
            lisayslause.setInt(2, uusiVeneHasPerusvarit.getPerusvaritID());
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
     * @param object muuttuva yhteys VeneHasPerusvaritMuutos objektilla
     * @return palauttaa true, jos muuttaminen on onnistunut.
     */
    @Override
    public boolean muutaTietoja(Object object) {
        if (object instanceof VeneHasPerusvaritMuutos) {
            VeneHasPerusvaritMuutos apu = (VeneHasPerusvaritMuutos) object;
            return muutaTietoja(apu.getUusi(), apu.getVanha());
        }
        return false;
    }

    /**
     *
     *
     * @param uusiVeneHasPerusvarit uusi yhetys. Esim "new
     * uusiVeneHasPerusvarit(1,1)"
     * @param vanhaVeneHasPerusvarit vanha yhetys. Esim. "new
     * vanhaVeneHasPerusvarit(1,2)"
     * @return palautta true, jos muuttaminen on onnistuttu.
     */
    private boolean muutaTietoja(VeneHasPerusvarit uusiVeneHasPerusvarit, VeneHasPerusvarit vanhaVeneHasPerusvarit) {
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return false;
        }
        PreparedStatement muutoslause = null;
        try {
            String muutaSql = "update vene_has_perusvarit "
                    + " set perusvarit_id=? where vene_id=? and perusvarit_id=?";
            muutoslause = yhteys.prepareStatement(muutaSql);

            muutoslause.setInt(1, uusiVeneHasPerusvarit.getPerusvaritID());
            muutoslause.setInt(2, vanhaVeneHasPerusvarit.getVeneID());
            muutoslause.setInt(3, vanhaVeneHasPerusvarit.getPerusvaritID());
            return muutoslause.executeUpdate() > 0;
        } catch (Exception e) {
            return false;
        } finally {
            YhteydenHallinta.suljeLause(muutoslause);
            YhteydenHallinta.suljeYhteys(yhteys);
        }
    }

    /**
     * Poista kaikki merkityt perusvarit yhteydet.
     * 
     *
     * @param perusvaritID poistettavien yhteyksien perusvaritID
     */
    @Override
    public void poistaTieto(int perusvaritID) {
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return;
        }
        PreparedStatement poistolause = null;
        try {
            String poistoSql = "delete from vene_has_perusvarit where perusvarit_id=?";
            poistolause = yhteys.prepareStatement(poistoSql);
            poistolause.setInt(1, perusvaritID);

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
     * @param perusvaritID poistettavan yhteyden perusvärin id
     * @param veneID poistettavan yhteyden vene id
     */
    public void poistaTieto(int perusvaritID, int veneID) {
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return;
        }
        PreparedStatement poistolause = null;
        try {
            String poistoSql = "delete from vene_has_perusvarit where perusvarit_id=? AND vene_id=?  ";
            poistolause = yhteys.prepareStatement(poistoSql);
            poistolause.setInt(1, perusvaritID);
            poistolause.setInt(2, veneID);

            poistolause.executeUpdate();
        } catch (Exception e) {

        } finally {
            YhteydenHallinta.suljeLause(poistolause);
            YhteydenHallinta.suljeYhteys(yhteys);
        }
    }
}
