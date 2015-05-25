package tietovarastopakkaus;

//@author s1300748

import datapakkaus.VeneHasVarusteet;
import datapakkaus.VeneHasVarusteetMuutos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VeneHasVarusteetTietovarasto extends Tietovarasto {

    private String ajuri;
    private String url;
    private String kayttaja;
    private String salasana;

    public VeneHasVarusteetTietovarasto(String ajuri, String url, String kayttaja, String salasana) {
        this.ajuri = ajuri;
        this.url = url;
        this.kayttaja = kayttaja;
        this.salasana = salasana;
    }

    public VeneHasVarusteetTietovarasto() {
        this("com.mysql.jdbc.Driver", "jdbc:mysql://eu-cdbr-azure-north-c.cloudapp.net:3306/veneveistamo",
                "bb372d8eaf1594", "c887b8c8");
    }

    public List<VeneHasVarusteet> haeTiedot() {
        List<VeneHasVarusteet> VeneHasVarusteet = new ArrayList<VeneHasVarusteet>();
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys != null) {
            PreparedStatement hakulause = null;
            ResultSet tulosjoukko = null;
            try {
                    String hakuSql = "SELECT vene_has_varusteet.vene_id, vene_has_varusteet.erikoisvarusteet_id, vakiovarusteet FROM vene_has_varusteet JOIN vene ON vene_has_varusteet.vene_id=vene.id;";
                hakulause = yhteys.prepareStatement(hakuSql);
                tulosjoukko = hakulause.executeQuery();

                while (tulosjoukko.next()) {
                    VeneHasVarusteet.add(new VeneHasVarusteet(tulosjoukko.getInt(1), tulosjoukko.getInt(2),tulosjoukko.getBoolean(3)));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                YhteydenHallinta.suljeTulosjoukko(tulosjoukko);
                YhteydenHallinta.suljeLause(hakulause);
                YhteydenHallinta.suljeYhteys(yhteys);
            }
        }
        return VeneHasVarusteet;
    }

    @Override
    public void lisaaTieto(Object object) {
        VeneHasVarusteet uusiVeneHasVarusteet = (VeneHasVarusteet) object;
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return;
        }
        PreparedStatement lisayslause = null;
        try {
            String lisaaSql = "insert into vene_has_varusteet"
                    + "(vene_id,erikoisvarusteet_id,vakiovarusteet) values (?,?,?)";
            lisayslause = yhteys.prepareStatement(lisaaSql);

            lisayslause.setInt(1, uusiVeneHasVarusteet.getVene_id());
            lisayslause.setInt(2, uusiVeneHasVarusteet.getErikoisvarusteet_id());
            lisayslause.setBoolean(3, uusiVeneHasVarusteet.getVakiovarusteet());
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
        if (object instanceof VeneHasVarusteetMuutos) {
            VeneHasVarusteetMuutos venehasvarusteet = (VeneHasVarusteetMuutos) object;
            return muutaTietoja(venehasvarusteet.getUusi(), venehasvarusteet.getVanha());
        }
        return false;
    }
    
    public boolean muutaTietoja(VeneHasVarusteet uusiVeneHasVarusteet, VeneHasVarusteet vanhaVeneHasVarusteet) {
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return false;
        }
        PreparedStatement muuttamislause = null;
        try {
            String muutaSql = "update vene_has_varusteet "
                    + " set vakiovarusteet=? "
                    + "where vene_id=? and erikoisvarusteet_id=?";
            muuttamislause = yhteys.prepareStatement(muutaSql);

            muuttamislause.setInt(2, vanhaVeneHasVarusteet.getErikoisvarusteet_id());
            muuttamislause.setInt(3, uusiVeneHasVarusteet.getVene_id());
//            muuttamislause.setInt(4, uusiVeneHasVarusteet.getErikoisvarusteet_id());
            muuttamislause.setBoolean(1, uusiVeneHasVarusteet.getVakiovarusteet());
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
    
    public void poistaTieto(int vene_id) { System.out.println("poisto1");
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            System.out.println("null1");
            return;
        }
        PreparedStatement poistolause = null;
        try {
            String poistoSql = "delete from vene_has_varusteet where vene_id=?";               
            poistolause = yhteys.prepareStatement(poistoSql);
            poistolause.setInt(1, vene_id);
            
            poistolause.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            YhteydenHallinta.suljeLause(poistolause);
            YhteydenHallinta.suljeYhteys(yhteys);
        }
    }
    
    public void poistaTieto(int vene_id, int erikoisvarusteet_id) { System.out.println("poisto2");
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            System.out.println("null");
            return;
        }
        PreparedStatement poistolause = null;
        try {
            String poistoSql = "delete from vene_has_varusteet where vene_id=? AND erikoisvarusteet_id=?";
            poistolause = yhteys.prepareStatement(poistoSql);
            poistolause.setInt(1, vene_id);
            poistolause.setInt(2, erikoisvarusteet_id);

            poistolause.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            YhteydenHallinta.suljeLause(poistolause);
            YhteydenHallinta.suljeYhteys(yhteys);
        }
    }
//    @Override
//    public boolean muutaTietoja(Object object) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

}