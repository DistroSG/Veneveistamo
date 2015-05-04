package tietovarastopakkaus;

import java.util.List;
import javax.swing.JOptionPane;

public abstract class Tietovarasto {

    protected String ajuri;
    protected String url;
    protected String kayttaja;
    protected String salasana;

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

    public abstract List<?> haeTiedot();

    public abstract void lisaaTieto(Object object);

    public abstract boolean muutaTietoja(Object object);

    public abstract void poistaTieto(int id);

    protected void virhe(String viesti) {
        JOptionPane.showMessageDialog(null,
                viesti, "Virhe", JOptionPane.ERROR_MESSAGE);
    }
     protected void idVirheIlmoitus() {
        JOptionPane.showMessageDialog(null,
                "ID on jo olemassa!", "Virhe", JOptionPane.ERROR_MESSAGE);
    }
     protected void hastaulukkoIdVirheIlmoitus() {
        JOptionPane.showMessageDialog(null,
                "Yhdistelmä on jo olemassa!", "Virhe", JOptionPane.ERROR_MESSAGE);
    }

//    //Hakee tiedot Maksu taulusta
//    public List<Maksu> haeMaksut() {
//        List<Maksu> maksut = new ArrayList<>();
//        Connection yhteys = yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
//        if (yhteys != null) {
//            PreparedStatement hakulause = null;
//            ResultSet tulosjoukko = null;
//            try {
//                String hakuSql = "Select eranumero, veneTilaus_id, hinta, maksettupaiva, erapaiva from maksu";
//                hakulause = yhteys.prepareStatement(hakuSql);
//                tulosjoukko = hakulause.executeQuery();
//
//                while (tulosjoukko.next()) {
//                    maksut.add(new Maksu(tulosjoukko.getInt(1), tulosjoukko.getInt(2),
//                            tulosjoukko.getDouble(3), tulosjoukko.getString(4),
//                            tulosjoukko.getString(5)));
//
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                YhteydenHallinta.suljeTulosjoukko(tulosjoukko);
//                YhteydenHallinta.suljeLause(hakulause);
//                YhteydenHallinta.suljeYhteys(yhteys);
//
//            }
//        }
//        return maksut;
//    }
}
