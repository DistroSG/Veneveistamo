/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tietovarastopakkaus;

import datapakkaus.VeneTilaus;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Axel
 */
public class VeneTilausTietovarasto extends Tietovarasto {

    @Override
    public List<VeneTilaus> haeTiedot() {
        List<VeneTilaus> venetilaus = new ArrayList<>();
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys != null) {
            PreparedStatement hakulause = null;
            ResultSet tulosjoukko = null;
            try {
                String hakuSql = "SELECT id, vene_id, henkilosto_id, hinta, kuljetus_id, vari, edistyminen FROM venetilaus;";
                hakulause = yhteys.prepareStatement(hakuSql);
                tulosjoukko = hakulause.executeQuery();

                while (tulosjoukko.next()) {
                    venetilaus.add(new VeneTilaus(tulosjoukko.getInt(1),
                            tulosjoukko.getInt(2), tulosjoukko.getInt(3), tulosjoukko.getDouble(4), tulosjoukko.getInt(5), tulosjoukko.getString(6), tulosjoukko.getString(7)));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                YhteydenHallinta.suljeTulosjoukko(tulosjoukko);
                YhteydenHallinta.suljeLause(hakulause);
                YhteydenHallinta.suljeYhteys(yhteys);
            }
        }
        return venetilaus;
    }

    @Override
    public void lisaaTieto(Object object) {
        VeneTilaus uusiVeneTilaus = (VeneTilaus) object;
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return;
        }
        PreparedStatement lisayslause = null;
        try {
            String lisaaSql = "insert into venetilaus"
                    + "(id, vene_id, henkilosto_id, hinta, kultejus_id, vari, edistyminen) values (?,?,?,?,?,?,?)";
            lisayslause = yhteys.prepareStatement(lisaaSql);

            lisayslause.setInt(1, uusiVeneTilaus.getId());
            lisayslause.setInt(2, uusiVeneTilaus.getVene_id());
            lisayslause.setInt(3, uusiVeneTilaus.getHenkilosto_id());
            lisayslause.setDouble(4, uusiVeneTilaus.getHinta());
            lisayslause.setInt(5, uusiVeneTilaus.getKuljetus_id());
            lisayslause.setString(6, uusiVeneTilaus.getVari());
            lisayslause.setString(7, uusiVeneTilaus.getEdistyminen());
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void poistaTieto(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
