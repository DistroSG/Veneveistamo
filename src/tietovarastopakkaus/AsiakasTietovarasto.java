
package tietovarastopakkaus;

import datapakkaus.Asiakas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AsiakasTietovarasto extends Tietovarasto {

    private String ajuri;
    private String url;
    private String kayttaja;
    private String salasana;

    public AsiakasTietovarasto(String ajuri, String url, String kayttaja, String salasana) {
        this.ajuri = ajuri;
        this.url = url;
        this.kayttaja = kayttaja;
        this.salasana = salasana;
    }

    public AsiakasTietovarasto() {
        this("com.mysql.jdbc.Driver", "jdbc:mysql://eu-cdbr-azure-north-c.cloudapp.net:3306/veneveistamo",
                "bb372d8eaf1594", "c887b8c8");
    }

    public List<Asiakas> haeAsiakas() {
        List<Asiakas> asiakkaat = new ArrayList<Asiakas>();
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys != null) {
            PreparedStatement hakulause = null;
            ResultSet tulosjoukko = null;
            try {

                String hakuSql = "select id,henkilotunnus,salsana,sukunimi,etunimi,sahkoposti,sukupuoli,puhelinnumero,asiakastyyppi from asiakas";

                hakulause = yhteys.prepareStatement(hakuSql);
                tulosjoukko = hakulause.executeQuery();

                while (tulosjoukko.next()) {
                    asiakkaat.add(new Asiakas(tulosjoukko.getInt(1),
                            tulosjoukko.getString(2),
                            tulosjoukko.getString(3),
                            tulosjoukko.getString(4),
                            tulosjoukko.getString(5),
                            tulosjoukko.getString(6),
                            tulosjoukko.getString(7),
                            tulosjoukko.getString(8),
                            tulosjoukko.getString(9)));
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


    public void lisaaAsiakas(Asiakas uusiasiakas) {
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return;
        }
        PreparedStatement lisayslause = null;
        try {

            String lisaaSql = "insert into asiakas"
                    + "(id, henkilotunnus, salsana, sukunimi, etunimi, sahkoposti, sukupuoli, puhelinnumero, asiakastyyppi) "
                    + "values (?,?,?,?,?,?,?,?,?)";
            lisayslause = yhteys.prepareStatement(lisaaSql);

            lisayslause.setInt(1, uusiasiakas.getId());
            lisayslause.setString(2, uusiasiakas.getHenkilotunnus());
            lisayslause.setString(3, uusiasiakas.getSalasana());
            lisayslause.setString(4, uusiasiakas.getSukunimi());
            lisayslause.setString(5, uusiasiakas.getEtunimi());
            lisayslause.setString(6, uusiasiakas.getSahkoposti());
            lisayslause.setString(7, uusiasiakas.getSukupuoli());
            lisayslause.setString(8, uusiasiakas.getPuhelinnumero());
            lisayslause.setString(9, uusiasiakas.getAsiakastyyppi());

            lisayslause.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            YhteydenHallinta.suljeLause(lisayslause);
            YhteydenHallinta.suljeYhteys(yhteys);
        }
    }

    public void poistaAsiakas(int id) {
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return;
        }
        PreparedStatement poistolause = null;
        try {
            String poistoSql = "delete from asiakas where id=?";
            poistolause = yhteys.prepareStatement(poistoSql);
            poistolause.setInt(1, id);

            poistolause.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            YhteydenHallinta.suljeLause(poistolause);
            YhteydenHallinta.suljeYhteys(yhteys);
        }
    }

    @Override
        public boolean muutaTietoja(Object object) {
        Asiakas Asiakas = (Asiakas) object;
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return false;
        }
        PreparedStatement paivitaminenlause = null;
        try {

            String paivitaSql = "UPDATE Asiakas "
                    + "SET henkilotunnus = ?, salsana = ?, sukunimi = ?, etunimi = ?, sahkoposti = ?, sukupuoli = ?, puhelinnumero = ?, asiakastyyppi = ? "
                    + "WHERE id = ?";
            paivitaminenlause = yhteys.prepareStatement(paivitaSql);

            paivitaminenlause.setInt(9, Asiakas.getId());
            paivitaminenlause.setString(1, Asiakas.getHenkilotunnus());
            paivitaminenlause.setString(2, Asiakas.getSalasana());
            paivitaminenlause.setString(3, Asiakas.getSukunimi());
            paivitaminenlause.setString(4, Asiakas.getEtunimi());
            paivitaminenlause.setString(5, Asiakas.getSahkoposti());
            paivitaminenlause.setString(6, Asiakas.getSukupuoli());
            paivitaminenlause.setString(7, Asiakas.getPuhelinnumero());
            paivitaminenlause.setString(8, Asiakas.getAsiakastyyppi());
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

    @Override
    public List<?> haeTiedot() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void lisaaTieto(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public void poistaTieto(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
