package tietovarastopakkaus;

import java.util.List;
import javax.swing.JOptionPane;

/**
 * Tietovarasto abstrakti luokka. Jolla pidetään titovaraston metodit, joilla
 * avulla saadaan tietokantayhteys ja tiedot tietokannan taulukosta.
 *
 * @author s1300778
 * @version 1.0
 */
public abstract class Tietovarasto {

    /**
     * Ajurilla pidetään ajurin nimi. Esim. "com.mysql.jdbc.Driver"
     *
     */
    protected String ajuri;

    /**
     * Url:lla pidetään tietokannan linkki. Esim.
     * "jdbc:mysql://eu-cdbr-azure-north-c.cloudapp.net:3306/veneveistamo"
     */
    protected String url;

    /**
     * Kayttajalla pidetään tietokannan käytäjä. Esim."Pekka"
     */
    protected String kayttaja;

    /**
     * Salasanalla pidetään käytäjän salasana. Esim."qwerty12345"
     */
    protected String salasana;

    /**
     * Luoda uutta yhteyttä ajurin, url:n, kayttatajan ja salasanan avulla.
     *
     * @param ajuri tietokanta-ajuri. Esim. "com.mysql.jdbc.Driver"
     * @param url linkki tietokannaan. Esim.
     * "jdbc:mysql://eu-cdbr-azure-north-c.cloudapp.net:3306/veneveistamo"
     * @param kayttaja tietokannan käytäjä. Esim."Pekka"
     * @param salasana käytäjän salasana. Esim."qwerty12345"
     */
    public Tietovarasto(String ajuri, String url, String kayttaja, String salasana) {
        this.ajuri = ajuri;
        this.url = url;
        this.kayttaja = kayttaja;
        this.salasana = salasana;
    }

    /**
     * Luoda uutta yhteyttä veneveistämön tietokannon kanssa.
     */
    public Tietovarasto() {
        this("com.mysql.jdbc.Driver", "jdbc:mysql://eu-cdbr-azure-north-c.cloudapp.net:3306/veneveistamo",
                "bb372d8eaf1594", "c887b8c8");
    }

    /**
     * Palautta kaikki tiedot listalla taulukosta.
     *
     * @return List
     */
    public abstract List<?> haeTiedot();

    /**
     * Lisätä tieto taulukkoon.
     *
     * @param object objekti, joka lisätään taulukkoon.
     */
    public abstract void lisaaTieto(Object object);

    /**
     * Muuta tieto taulukossa.
     *
     * @param object objekti, joka muuttaa taulukossa.
     * @return palautta true, jos muuttaminen on onnistuttu.
     */
    public abstract boolean muutaTietoja(Object object);

    /**
     * Poista tieto taulukosta.
     *
     * @param id tiedon id, joka poistetaan.
     */
    public abstract void poistaTieto(int id);

    /**
     * Virhe ilmoitus annettu tekstin avulla.
     *
     * @param viesti virhen teksti.
     */
    protected void virhe(String viesti) {
        JOptionPane.showMessageDialog(null,
                viesti, "Virhe", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Virhe joka ilmoitta, että "ID on jo olemassa!"
     */
    protected void idVirheIlmoitus() {
        JOptionPane.showMessageDialog(null,
                "ID on jo olemassa!", "Virhe", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Virhe joka ilmoitta, että "Yhdistelmä on jo olemassa!"
     */
    protected void hastaulukkoIdVirheIlmoitus() {
        JOptionPane.showMessageDialog(null,
                "Yhdistelmä on jo olemassa!", "Virhe", JOptionPane.ERROR_MESSAGE);
    }
}
