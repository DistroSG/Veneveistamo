package tietovarastopakkaus;

import datapakkaus.VeneHasMateriaali;
import datapakkaus.VeneHasMateriaaliMuutos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * VeneHasMateriaaliTietovarasto luokka. Jonka avulla saadaan
 * tietokantayhteys ja tiedot VeneHasMateriaali taulukosta.
 *
 * @author s1300723
 * @version 1.0
 */
public class VeneHasMateriaaliTietovarasto extends Tietovarasto {

    /**
     * Palautta kaikki VeneHasMateriaali yhteydet.
     *
     * @return kaikki VeneHasMateriaali yhteydet.
     */
    @Override
    public List<VeneHasMateriaali> haeTiedot() {
        List<VeneHasMateriaali> haeVeneHasMateriaali = new ArrayList<>();
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys != null) {
            PreparedStatement hakulause = null;
            ResultSet tulosjoukko = null;
            try {
                String hakuSql = "SELECT vene_id, materiaali_id FROM vene_has_materiaali;";
                hakulause = yhteys.prepareStatement(hakuSql);
                tulosjoukko = hakulause.executeQuery();

                while (tulosjoukko.next()) {
                    haeVeneHasMateriaali.add(new VeneHasMateriaali(tulosjoukko.getInt(1),
                            tulosjoukko.getInt(2)));
                }
            } catch (Exception e) {
            } finally {
                YhteydenHallinta.suljeTulosjoukko(tulosjoukko);
                YhteydenHallinta.suljeLause(hakulause);
                YhteydenHallinta.suljeYhteys(yhteys);
            }
        }
        return haeVeneHasMateriaali;
    }

    /**
     * Lisää uuden yhteyden.
     *
     * @param object objekti, joka lisätään.
     */
    @Override
    public void lisaaTieto(Object object) {
        VeneHasMateriaali uusiVeneHasMateriaali = (VeneHasMateriaali) object;
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return;
        }
        PreparedStatement lisayslause = null;
        try {
            String lisaaSql = "insert into vene_has_materiaali "
                    + "(vene_id, materiaali_id) values (?,?)";
            lisayslause = yhteys.prepareStatement(lisaaSql);

            lisayslause.setInt(1, uusiVeneHasMateriaali.getVeneID());
            lisayslause.setInt(2, uusiVeneHasMateriaali.getMateriaaliID());
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
     * @param object muuttuva yhteys VeneHasMateriaaliMuutos objektilla
     * @return palauttaa true, jos muuttaminen on onnistunut.
     */
    @Override
    public boolean muutaTietoja(Object object) {
        if (object instanceof VeneHasMateriaaliMuutos) {
            VeneHasMateriaaliMuutos apu = (VeneHasMateriaaliMuutos) object;
            return muutaTietoja(apu.getUusi(), apu.getVanha());
        }
        return false;
    }

    /**
     *
     *
     * @param uusiVeneHasMateriaali uusi yhetys. Esim "new
     * uusiVeneHasMateriaali(1,1)"
     * @param vanhaVeneHasMateriaali vanha yhetys. Esim. "new
     * uusiVeneHasMateriaali(1,2)"
     * @return palautta true, jos muuttaminen on onnistuttu.
     */
    private boolean muutaTietoja(VeneHasMateriaali uusiVeneHasMateriaali, VeneHasMateriaali vanhaVeneHasMateriaali) {
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return false;
        }
        PreparedStatement muutoslause = null;
        try {
            String muutaSql = "update vene_has_materiaali "
                    + " set materiaali_id=? where vene_id=? and materiaali_id=?";
            muutoslause = yhteys.prepareStatement(muutaSql);

            muutoslause.setInt(1, uusiVeneHasMateriaali.getMateriaaliID());
            muutoslause.setInt(2, vanhaVeneHasMateriaali.getVeneID());
            muutoslause.setInt(3, vanhaVeneHasMateriaali.getMateriaaliID());
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
     * @param materiaaliID poistettavien yhteyksien materiaaliid
     */
    @Override
    public void poistaTieto(int materiaaliID) {
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return;
        }
        PreparedStatement poistolause = null;
        try {
            String poistoSql = "delete from vene_has_materiaali where materiaali_id=?";
            poistolause = yhteys.prepareStatement(poistoSql);
            poistolause.setInt(1, materiaaliID);

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
     * @param materiaaliID poistettavan yhteyden henkilön id
     * @param veneID poistettavan yhteyden tehtävän id
     */
    public void poistaTieto(int materiaaliID, int veneID) {
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return;
        }
        PreparedStatement poistolause = null;
        try {
            String poistoSql = "delete from vene_has_materiaali where materiaali_id=? AND vene_id=?  ";
            poistolause = yhteys.prepareStatement(poistoSql);
            poistolause.setInt(1, materiaaliID);
            poistolause.setInt(2, veneID);

            poistolause.executeUpdate();
        } catch (Exception e) {

        } finally {
            YhteydenHallinta.suljeLause(poistolause);
            YhteydenHallinta.suljeYhteys(yhteys);
        }
    }
}
