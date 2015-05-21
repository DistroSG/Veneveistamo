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
        Connection yhteys = yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys != null) {
            PreparedStatement hakulause = null;
            ResultSet tulosjoukko = null;
            try {
                    String hakuSql = "select Id, Varusteet, Kuvaus, Kuva, Takuu_id, Hinta, Alv from Varusteet";
                hakulause = yhteys.prepareStatement(hakuSql);
                tulosjoukko = hakulause.executeQuery();

                while (tulosjoukko.next()) {
                    Varusteet.add(new Varusteet(tulosjoukko.getInt(1), tulosjoukko.getString(2),
                       tulosjoukko.getString(3),tulosjoukko.getString(4),
                       tulosjoukko.getInt(5),tulosjoukko.getDouble(6),tulosjoukko.getDouble(7)));
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

    public void lisaaVarusteet(Varusteet uusiVarusteet) {
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return;
        }
        PreparedStatement lisayslause = null;
        try {
            String lisaaSql = "insert into Varusteet "
                    + "(Id,Varusteet,Kuvaus,Kuva,Takuu_id,Hinta,Alv) "
                    + "values (?,?,?,?,?,?,?)";
            lisayslause = yhteys.prepareStatement(lisaaSql);

            lisayslause.setInt(1, uusiVarusteet.getId());
            lisayslause.setString(2, uusiVarusteet.getVarusteet());
            lisayslause.setString(3, uusiVarusteet.getKuvaus());
            lisayslause.setString(4, uusiVarusteet.getKuva());
            lisayslause.setInt(5, uusiVarusteet.getTakuu_id());
            lisayslause.setDouble(6, uusiVarusteet.getHinta());
            lisayslause.setDouble(7, uusiVarusteet.getAlv());
            lisayslause.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            YhteydenHallinta.suljeLause(lisayslause);
            YhteydenHallinta.suljeYhteys(yhteys);
        }
    }

    public boolean Muuta(Varusteet uusiVarusteet) {
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return false;
        }
        PreparedStatement muuttamislause = null;
        try {
            String muutaSql = "update Varusteet "
                    + " set Varusteet=?,Kuvaus=?,Kuva=?,Takuu_id=?,Hinta=?,Alv=? "
                    + "where Id=?";
            muuttamislause = yhteys.prepareStatement(muutaSql);

            muuttamislause.setInt(7, uusiVarusteet.getId());
            muuttamislause.setString(1, uusiVarusteet.getVarusteet());
            muuttamislause.setString(2, uusiVarusteet.getKuvaus());
            muuttamislause.setString(3, uusiVarusteet.getKuva());
            muuttamislause.setInt(4, uusiVarusteet.getTakuu_id());
            muuttamislause.setDouble(5, uusiVarusteet.getHinta());
            muuttamislause.setDouble(6, uusiVarusteet.getAlv());
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
    public void poistaVarusteet(int id) {
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return;
        }
        PreparedStatement poistolause = null;
        try {
            String poistoSql = "delete from Varusteet where Id=?";               
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
    public void lisaaTieto(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean muutaTietoja(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void poistaTieto(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
