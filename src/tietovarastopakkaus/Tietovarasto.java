package tietovarastopakkaus;

import datapakkaus.Puhelinumero;
import datapakkaus.Toimisto;
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
        this("com.mysql.jdbc.Driver", "jdbc:mysql://eu-cdbr-azure-north-c.cloudapp.net:3306/veneveistamo",
                "bb372d8eaf1594", "c887b8c8");
    }

    //puhelinnimero
    public List<Puhelinumero> haePuhelinumerot() {
        List<Puhelinumero> puhelinumerot = new ArrayList<>();
        Connection yhteys = yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys != null) {
            PreparedStatement hakulause = null;
            ResultSet tulosjoukko = null;
            try {
                String hakuSql = "SELECT id, puhelinumero, toimisto_id FROM puhelinumero;";
                hakulause = yhteys.prepareStatement(hakuSql);
                tulosjoukko = hakulause.executeQuery();

                while (tulosjoukko.next()) {
                    puhelinumerot.add(new Puhelinumero(tulosjoukko.getInt(1),
                            tulosjoukko.getInt(2), tulosjoukko.getInt(3)));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                YhteydenHallinta.suljeTulosjoukko(tulosjoukko);
                YhteydenHallinta.suljeLause(hakulause);
                YhteydenHallinta.suljeYhteys(yhteys);
            }
        }
        return puhelinumerot;
    }

    public void lisaaPuhelinumero(Puhelinumero uusiPuhelinumero) {
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return;
        }
        PreparedStatement lisayslause = null;
        try {
            String lisaaSql = "insert into puhelinumero "
                    + "(id,puhelinumero,toimisto_id) values (?,?,?)";
            lisayslause = yhteys.prepareStatement(lisaaSql);

            lisayslause.setInt(1, uusiPuhelinumero.getId());
            lisayslause.setInt(2, uusiPuhelinumero.getPuhelinnumero());
            lisayslause.setInt(3, uusiPuhelinumero.getToimistoID());
            lisayslause.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            YhteydenHallinta.suljeLause(lisayslause);
            YhteydenHallinta.suljeYhteys(yhteys);
        }
    }

    public boolean muutaPuhelinumeroTietoja(Puhelinumero uusiPuhelinumero) {
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return false;
        }
        PreparedStatement muutoslause = null;
        try {
            String muutaSql = "update puhelinnumero "
                    + " set id=?,puhelinumero=?,toimisto_id=? "
                    + "where id=?";
            muutoslause = yhteys.prepareStatement(muutaSql);

            muutoslause.setInt(1, uusiPuhelinumero.getId());
            muutoslause.setInt(2, uusiPuhelinumero.getPuhelinnumero());
            muutoslause.setInt(3, uusiPuhelinumero.getToimistoID());
            if (muutoslause.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            YhteydenHallinta.suljeLause(muutoslause);
            YhteydenHallinta.suljeYhteys(yhteys);
        }
    }

    public void poistaPuhelinumero(int puhelinID) {
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return;
        }
        PreparedStatement poistolause = null;
        try {
            String poistoSql = "delete from puhelinumero where id=?";
            poistolause = yhteys.prepareStatement(poistoSql);
            poistolause.setInt(1, puhelinID);

            poistolause.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            YhteydenHallinta.suljeLause(poistolause);
            YhteydenHallinta.suljeYhteys(yhteys);
        }
    }

    //toimisto
    public List<Toimisto> haeToimistot() {
        List<Toimisto> toimistot = new ArrayList<>();
        Connection yhteys = yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys != null) {
            PreparedStatement hakulause = null;
            ResultSet tulosjoukko = null;
            try {
                String hakuSql = "SELECT id, aukiolajit, katuosoite, postinumero, toimipaikka FROM toimisto;";
                hakulause = yhteys.prepareStatement(hakuSql);
                tulosjoukko = hakulause.executeQuery();

                while (tulosjoukko.next()) {
                    toimistot.add(new Toimisto(tulosjoukko.getInt(1),
                            tulosjoukko.getString(2), tulosjoukko.getString(3), tulosjoukko.getInt(4), tulosjoukko.getString(5)));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                YhteydenHallinta.suljeTulosjoukko(tulosjoukko);
                YhteydenHallinta.suljeLause(hakulause);
                YhteydenHallinta.suljeYhteys(yhteys);
            }
        }
        return toimistot;
    }

    public void lisaaToimisto(Toimisto uusiToimisto) {
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return;
        }
        PreparedStatement lisayslause = null;
        try {
            String lisaaSql = "insert into toimisto"
                    + "(id,aukiolajit, katuosoite, postinumero, toimipaikka) values (?,?,?,?,?)";
            lisayslause = yhteys.prepareStatement(lisaaSql);

            lisayslause.setInt(1, uusiToimisto.getId());
            lisayslause.setString(2, uusiToimisto.getAukiolajit());
            lisayslause.setString(3, uusiToimisto.getKatuosoite());
            lisayslause.setInt(4, uusiToimisto.getPostinumero());
            lisayslause.setString(5, uusiToimisto.getToimipaikka());
            lisayslause.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            YhteydenHallinta.suljeLause(lisayslause);
            YhteydenHallinta.suljeYhteys(yhteys);
        }
    }

    public boolean muutaToimistonTietoja(Toimisto uusiToimisto) {
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return false;
        }
        PreparedStatement muutoslause = null;
        try {

            String muutaSql = "update toimisto "
                    + " set id=?,aukiolajit=?,katuosoite=?,postinumero=?,toimipaikka=? "
                    + "where id=?";
            muutoslause = yhteys.prepareStatement(muutaSql);

            muutoslause.setInt(1, uusiToimisto.getId());
            muutoslause.setString(2, uusiToimisto.getAukiolajit());
            muutoslause.setString(3, uusiToimisto.getKatuosoite());
            muutoslause.setInt(4, uusiToimisto.getPostinumero());
            muutoslause.setString(5, uusiToimisto.getToimipaikka());
            if (muutoslause.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            YhteydenHallinta.suljeLause(muutoslause);
            YhteydenHallinta.suljeYhteys(yhteys);
        }
    }

    public void poistaToimisto(int toimistoID) {
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return;
        }
        PreparedStatement poistolause = null;
        try {
            String poistoSql = "delete from toimisto where id=?";
            poistolause = yhteys.prepareStatement(poistoSql);
            poistolause.setInt(1, toimistoID);

            poistolause.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            YhteydenHallinta.suljeLause(poistolause);
            YhteydenHallinta.suljeYhteys(yhteys);
        }
    }

}
