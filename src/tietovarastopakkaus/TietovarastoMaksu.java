package tietovarastopakkaus;

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

    public List<Maksu> haeMaksut() {
        List<Maksu> maksut = new ArrayList<>();
        Connection yhteys = yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys != null) {
            PreparedStatement hakulause = null;
            ResultSet tulosjoukko = null;
            try{
                String hakuSql = "Select eranumero, veneTilaus_id, hinta, maksettupaiva, erapaiva from maksu";
                hakulause =yhteys.prepareStatement(hakuSql);
                tulosjoukko=hakulause.executeQuery();
                
                while(tulosjoukko.next()){
                    maksut.add(new Maksu(tulosjoukko.getInt(1),tulosjoukko.getInt(2),
                    tulosjoukko.getDouble(3),tulosjoukko.getString(4),
                    tulosjoukko.getString(5)));
                    
                }
                
            }
            catch(Exception e){
                e.printStackTrace();
            }
            finally{
                YhteydenHallinta.suljeTulosjoukko(tulosjoukko);
                YhteydenHallinta.suljeLause(hakulause);
                YhteydenHallinta.suljeYhteys(yhteys);
                
            }
        }
        return maksut;
    }
    
    

}
