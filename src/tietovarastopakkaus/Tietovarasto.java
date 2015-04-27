package tietovarastopakkaus;

import datapakkaus.Elokuva;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Tietovarasto {

    private String ajuri;
    private String url;
    private String kayttaja;
    private String salasana;

    public Tietovarasto(String ajuri, String url, String kayttaja, String salasana) {
        this.ajuri = ajuri;
        this.url = url;
        this.kayttaja = kayttaja;
        this.salasana = salasana;
    }

    public Tietovarasto() {
        this("org.apache.derby.jdbc.EmbeddedDriver", "jdbc:derby:elokuva",
                "saku", "salainen");
    }

    public List<Elokuva> haeKaikkElokuvat() {
        List<Elokuva> elokuvat = new ArrayList<Elokuva>();
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys != null) {
            PreparedStatement hakulause = null;
            ResultSet tulosjoukko = null;
            try {

                String hakuSql = "select elokuvaNro,nimi,ohjaaja,vuosi from elokuva";

                hakulause = yhteys.prepareStatement(hakuSql);
                tulosjoukko = hakulause.executeQuery();

                while (tulosjoukko.next()) {
                    elokuvat.add(new Elokuva(tulosjoukko.getInt(1),
                            tulosjoukko.getString(2),
                            tulosjoukko.getString(3),
                            tulosjoukko.getInt(4)));
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                YhteydenHallinta.suljeTulosjoukko(tulosjoukko);
                YhteydenHallinta.suljeLause(hakulause);
                YhteydenHallinta.suljeYhteys(yhteys);
            }
        }
        return elokuvat;
    }

    public Elokuva haeElokuva(int ElokuvaNro) {
        Elokuva elokuva = null;
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys != null) {
            PreparedStatement hakulause = null;
            ResultSet tulosjoukko = null;
            try {

                String hakuSql = "select elokuvaNro,nimi,ohjaaja,vuosi from elokuva WHERE elokuvaNro = ?";

                hakulause = yhteys.prepareStatement(hakuSql);
                hakulause.setInt(1, ElokuvaNro);
                tulosjoukko = hakulause.executeQuery();
                if (tulosjoukko.next()) {
                    elokuva = new Elokuva(tulosjoukko.getInt(1),
                            tulosjoukko.getString(2),
                            tulosjoukko.getString(3),
                            tulosjoukko.getInt(4));
                }

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            } finally {
                YhteydenHallinta.suljeTulosjoukko(tulosjoukko);
                YhteydenHallinta.suljeLause(hakulause);
                YhteydenHallinta.suljeYhteys(yhteys);
            }
        }
        return elokuva;
    }

    public void lisaaElokuva(Elokuva uusielokuva) {
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return;
        }
        PreparedStatement lisayslause = null;
        try {

            String lisaaSql = "insert into elokuva "
                    + "(elokuvaNro,nimi,ohjaaja,vuosi) "
                    + "values (?,?,?,?)";
            lisayslause = yhteys.prepareStatement(lisaaSql);

            lisayslause.setInt(1, uusielokuva.getElokuvaNro());
            lisayslause.setString(2, uusielokuva.getNimi());
            lisayslause.setString(3, uusielokuva.getOhjaaja());
            lisayslause.setInt(4, uusielokuva.getVuosi());

            lisayslause.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            YhteydenHallinta.suljeLause(lisayslause);
            YhteydenHallinta.suljeYhteys(yhteys);
        }
    }

    public boolean poistaElokuva(int elokuvaNro) {
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return false;
        }
        PreparedStatement poistaminenlause = null;
        try {
            String poistaaSql = "delete from elokuva WHERE elokuvaNro = ?";

            poistaminenlause = yhteys.prepareStatement(poistaaSql);

            poistaminenlause.setInt(1, elokuvaNro);
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

    public boolean paivitaElokuva(Elokuva elokuva) {
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return false;
        }
        PreparedStatement paivitaminenlause = null;
        try {

            String paivitaSql = "UPDATE Elokuva "
                    + "SET nimi = ?, ohjaaja = ?, vuosi = ? "
                    + "WHERE elokuvaNro = ?";
            paivitaminenlause = yhteys.prepareStatement(paivitaSql);

            paivitaminenlause.setString(1, elokuva.getNimi());
            paivitaminenlause.setString(2, elokuva.getOhjaaja());
            paivitaminenlause.setInt(3, elokuva.getVuosi());
            paivitaminenlause.setInt(4, elokuva.getElokuvaNro());
            paivitaminenlause.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            YhteydenHallinta.suljeLause(paivitaminenlause);
            YhteydenHallinta.suljeYhteys(yhteys);
        }
        return false;
    }

}
