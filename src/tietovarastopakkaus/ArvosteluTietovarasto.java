package tietovarastopakkaus;

import datapakkaus.Arvostelu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ArvosteluTietovarasto {

    private String ajuri;
    private String url;
    private String kayttaja;
    private String salasana;

    public ArvosteluTietovarasto(String ajuri, String url, String kayttaja, String salasana) {
        this.ajuri = ajuri;
        this.url = url;
        this.kayttaja = kayttaja;
        this.salasana = salasana;
    }

    public ArvosteluTietovarasto() {
        this("com.mysql.jdbc.Driver", "jdbc:mysql://84.248.87.133:3306/veneveistamo",
                "admin", "kek12345");
    }
/**
 * haeKaikkiArvostelut Hakee kaikki tiedot arvostelu taulusta.
 * @return kaikki arvostelujen tiedot.
 */
    public List<Arvostelu> haeTiedot() {
        List<Arvostelu> arvostelut = new ArrayList<Arvostelu>();
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys != null) {
            PreparedStatement hakulause = null;
            ResultSet tulosjoukko = null;
            try {

                String hakuSql = "select arvostelu.id,asiakas_id,arvostelu,pikkuarvostelu,etunimi,sukunimi from arvostelu inner join asiakas ON arvostelu.asiakas_id=asiakas.id";

                hakulause = yhteys.prepareStatement(hakuSql);
                tulosjoukko = hakulause.executeQuery();

                while (tulosjoukko.next()) {
                    arvostelut.add(new Arvostelu(tulosjoukko.getInt(1),
                            tulosjoukko.getInt(2),
                            tulosjoukko.getString(3),
                            tulosjoukko.getString(4),
                            tulosjoukko.getString(5),
                            tulosjoukko.getString(6)));
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                YhteydenHallinta.suljeTulosjoukko(tulosjoukko);
                YhteydenHallinta.suljeLause(hakulause);
                YhteydenHallinta.suljeYhteys(yhteys);
            }
        }
        return arvostelut;
    }

/**
 * lisaaArvostelu lisää arvostelun arvostelu kantaan.
 * @param uusiarvostelu, lisättävä arvostelu
 */
    public void lisaaTieto(Arvostelu uusiarvostelu) {
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return;
        }
        PreparedStatement lisayslause = null;
        try {

            String lisaaSql = "insert into arvostelu "
                    + "(id,asiakas_id,arvostelu,pikkuarvostelu) "
                    + "values (?,?,?,?)";
            lisayslause = yhteys.prepareStatement(lisaaSql);

            lisayslause.setInt(1, uusiarvostelu.getId());
            lisayslause.setInt(2, uusiarvostelu.getAsiakasid());
            lisayslause.setString(3, uusiarvostelu.getArvostelu());
            lisayslause.setString(4, uusiarvostelu.getPikkuarvostelu());

            lisayslause.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            YhteydenHallinta.suljeLause(lisayslause);
            YhteydenHallinta.suljeYhteys(yhteys);
        }
    }
/**
 * poistaArvostelu poistaa arvostelun kannasta.
 * @param id arvostelun id joka poistetaan.
 */
    public boolean poistaTieto(int id) {
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return false;
        }
        PreparedStatement poistaminenlause = null;
        try {
            String poistaaSql = "delete from arvostelu WHERE id = ?";

            poistaminenlause = yhteys.prepareStatement(poistaaSql);

            poistaminenlause.setInt(1, id);
            if (poistaminenlause.executeUpdate() > 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            YhteydenHallinta.suljeLause(poistaminenlause);
            YhteydenHallinta.suljeYhteys(yhteys);
        }
    }
/**
 * muutaTietoja muuttaa arvostelu tietoja
 * @param object
 * @return palautta true, jos muuttaminen on onnistuttu.
 */
        public boolean muutaTietoja(Object object) {
        Arvostelu arvostelu = (Arvostelu) object;
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return false;
        }
        PreparedStatement paivitaminenlause = null;
        try {

            String paivitaSql = "UPDATE arvostelu "
                    + "SET arvostelu = ?, pikkuarvostelu = ? "
                    + "WHERE id = ? and asiakas_id = ?";
            paivitaminenlause = yhteys.prepareStatement(paivitaSql);

            paivitaminenlause.setInt(3, arvostelu.getId());
            paivitaminenlause.setInt(4, arvostelu.getAsiakasid());
            paivitaminenlause.setString(1, arvostelu.getArvostelu());
            paivitaminenlause.setString(2, arvostelu.getPikkuarvostelu());
            if (paivitaminenlause.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            YhteydenHallinta.suljeLause(paivitaminenlause);
            YhteydenHallinta.suljeYhteys(yhteys);
        }    }

}

