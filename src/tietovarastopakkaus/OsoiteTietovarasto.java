package tietovarastopakkaus;

import datapakkaus.Osoite;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OsoiteTietovarasto extends Tietovarasto {


    @Override
    public List<Osoite> haeTiedot() {
        List<Osoite> asiakkaat = new ArrayList<Osoite>();
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys != null) {
            PreparedStatement hakulause = null;
            ResultSet tulosjoukko = null;
            try {

                String hakuSql = "select id,katuosoite,postinumero,toimipaikka,yrityksennimi,venetilaus_id from osoite";

                hakulause = yhteys.prepareStatement(hakuSql);
                tulosjoukko = hakulause.executeQuery();

                while (tulosjoukko.next()) {
                    asiakkaat.add(new Osoite(tulosjoukko.getInt(1),
                            tulosjoukko.getString(2),
                            tulosjoukko.getString(3),
                            tulosjoukko.getString(4),
                            tulosjoukko.getString(5),
                            tulosjoukko.getInt(6)));
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                YhteydenHallinta.suljeTulosjoukko(tulosjoukko);
                YhteydenHallinta.suljeLause(hakulause);
                YhteydenHallinta.suljeYhteys(yhteys);
            }
        }
        return asiakkaat;
    }

    @Override
    public void lisaaTieto(Object object) {
        Osoite uusiosoite = (Osoite) object;
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return;
        }
        PreparedStatement lisayslause = null;
        try {

            String lisaaSql = "insert into osoite "
                    + "(id,katuosoite,postinumero,toimipaikka,yrityksennimi,venetilaus_id) "
                    + "values (?,?,?,?,?,?)";
            lisayslause = yhteys.prepareStatement(lisaaSql);

            lisayslause.setInt(1, uusiosoite.getId());
            lisayslause.setString(2, uusiosoite.getKatuosoite());
            lisayslause.setString(3, uusiosoite.getPostinumero());
            lisayslause.setString(4, uusiosoite.getToimipaikka());
            lisayslause.setString(5, uusiosoite.getYrityksennimi());
            lisayslause.setInt(6, uusiosoite.getVenetilausid());

            lisayslause.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            YhteydenHallinta.suljeLause(lisayslause);
            YhteydenHallinta.suljeYhteys(yhteys);
        }
    }

    @Override
    public boolean muutaTietoja(Object object) {
        Osoite osoite = (Osoite) object;
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return false;
        }
        PreparedStatement paivitaminenlause = null;
        try {

            String paivitaSql = "UPDATE Osoite "
                    + "SET katuosoite = ?, postinumero = ?, toimipaikka = ?, yrityksennimi = ?, venetilaus_id = ? "
                    + "WHERE id = ?";
            paivitaminenlause = yhteys.prepareStatement(paivitaSql);

            paivitaminenlause.setInt(6, osoite.getId());
            paivitaminenlause.setString(1, osoite.getKatuosoite());
            paivitaminenlause.setString(2, osoite.getPostinumero());
            paivitaminenlause.setString(3, osoite.getToimipaikka());
            paivitaminenlause.setString(4, osoite.getYrityksennimi());
            paivitaminenlause.setInt(5, osoite.getVenetilausid());

            paivitaminenlause.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            YhteydenHallinta.suljeLause(paivitaminenlause);
            YhteydenHallinta.suljeYhteys(yhteys);
        }
        return false;
    }

    @Override
    public void poistaTieto(int id) {
        
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        
        PreparedStatement poistaminenlause = null;
        try {
            String poistaaSql = "delete from osoite WHERE id = ?";
            poistaminenlause = yhteys.prepareStatement(poistaaSql);
            poistaminenlause.setInt(1, id);
        } catch (Exception e) {
            e.printStackTrace();
            
        } finally {
            YhteydenHallinta.suljeLause(poistaminenlause);
            YhteydenHallinta.suljeYhteys(yhteys);
        }
    }

}
