package tietovarastopakkaus;

//@author s1300748

import datapakkaus.Varusteet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VarusteetTietovarasto extends Tietovarasto {

    private String ajuri;
    private String url;
    private String kayttaja;
    private String salasana;

    public VarusteetTietovarasto(String ajuri, String url, String kayttaja, String salasana) {
        this.ajuri = ajuri;
        this.url = url;
        this.kayttaja = kayttaja;
        this.salasana = salasana;
    }

    public VarusteetTietovarasto() {
        this("com.mysql.jdbc.Driver", "jdbc:mysql://eu-cdbr-azure-north-c.cloudapp.net:3306/veneveistamo",
                "bb372d8eaf1594", "c887b8c8");
    }

    public List<Varusteet> haeTiedot() {
        List<Varusteet> Varusteet = new ArrayList<Varusteet>();
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys != null) {
            PreparedStatement hakulause = null;
            ResultSet tulosjoukko = null;
            try {
                    String hakuSql = "select id, varusteet, kuvaus, takuu_id, hinta, alv from varusteet";
                hakulause = yhteys.prepareStatement(hakuSql);
                tulosjoukko = hakulause.executeQuery();

                while (tulosjoukko.next()) {
                    Varusteet.add(new Varusteet(tulosjoukko.getInt(1), tulosjoukko.getString(2),tulosjoukko.getString(3),
                       tulosjoukko.getInt(4),tulosjoukko.getDouble(5),tulosjoukko.getInt(6)));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                YhteydenHallinta.suljeTulosjoukko(tulosjoukko);
                YhteydenHallinta.suljeLause(hakulause);
                YhteydenHallinta.suljeYhteys(yhteys);
            }
        }
        return Varusteet;
    }

    @Override
    public void lisaaTieto(Object object) {
        Varusteet uusiVarusteet = (Varusteet) object;
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return;
        }
        PreparedStatement lisayslause = null;
        try {
            String lisaaSql = "insert into varusteet"
                    + "(id,varusteet,kuvaus,takuu_id,hinta,alv) values (?,?,?,?,?,?)";
            lisayslause = yhteys.prepareStatement(lisaaSql);

            lisayslause.setInt(1, uusiVarusteet.getId());
            lisayslause.setString(2, uusiVarusteet.getVarusteet());
            lisayslause.setString(3, uusiVarusteet.getKuvaus());
            lisayslause.setInt(4, uusiVarusteet.getTakuu_id());
            lisayslause.setDouble(5, uusiVarusteet.getHinta());
            lisayslause.setInt(6, uusiVarusteet.getAlv());
            lisayslause.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            YhteydenHallinta.suljeLause(lisayslause);
            YhteydenHallinta.suljeYhteys(yhteys);
        }
    }

    public boolean muutaTietoja(Varusteet uusiVarusteet) {
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return false;
        }
        PreparedStatement muuttamislause = null;
        try {
            String muutaSql = "update varusteet "
                    + " set varusteet=?,kuvaus=?,takuu_id=?,hinta=?,alv=? "
                    + "where id=?";
            muuttamislause = yhteys.prepareStatement(muutaSql);

            muuttamislause.setInt(6, uusiVarusteet.getId());
            muuttamislause.setString(1, uusiVarusteet.getVarusteet());
            muuttamislause.setString(2, uusiVarusteet.getKuvaus());
            muuttamislause.setInt(3, uusiVarusteet.getTakuu_id());
            muuttamislause.setDouble(4, uusiVarusteet.getHinta());
            muuttamislause.setInt(5, uusiVarusteet.getAlv());
            if(muuttamislause.executeUpdate()>0) {
                return true;
            }
            else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            YhteydenHallinta.suljeLause(muuttamislause);
            YhteydenHallinta.suljeYhteys(yhteys);
        }     
    }
    public void poistaTieto(int id) {
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return;
        }
        PreparedStatement poistolause = null;
        try {
            String poistoSql = "delete from varusteet where id=?";               
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}