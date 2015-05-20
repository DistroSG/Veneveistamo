package datapakkaus;

/**
 *
 * @author s1300776
 */
public class Asiakas {

    private final int id;
    private final String henkilotunnus;
    private final String salasana;
    private final String sukunimi;
    private final String etunimi;
    private final String sahkoposti;
    private final String sukupuoli;
    private final String puhelinnumero;
    private final String asiakastyyppi;

    /**
     * Luodaan uutta asiakasta, id:n, henkilotunnuksen, salasanan, sukunimen,
     * etunimen, sahkopostin, sukupuolen,puhelinnumeron ja asiakastyypin avulla.
     *
     * @param id arvostelu id. Esim. "1"
     * @param henkilotunnus henkilotunnuksen ID. Esim. "890232-123E"
     * @param salasana asiakkaan salasana. Esim. "WowSuchDog123"
     * @param sukunimi asiakkaan sukunimi Esim. "Salainen"
     * @param etunimi asiakkaan etunimi. Esim. "Saku"
     * @param sahkoposti asiakkaan sahkoposti. Esim. "asd.dsa@jotain.com"
     * @param sukupuoli asiakkaan sukupuoli. HUOM. käy vain "M/N"
     * @param puhelinnumero asiakkaan puhelinnumero. Esim. "983249823"
     * @param asiakastyyppi asiakkastyyppi. Esim. "Yksityishenkilö/Organisaatio"
     */
    public Asiakas(int id, String henkilotunnus, String salasana, String sukunimi, String etunimi, String sahkoposti, String sukupuoli, String puhelinnumero, String asiakastyyppi) {
        this.id = id;
        this.henkilotunnus = henkilotunnus;
        this.salasana = salasana;
        this.sukunimi = sukunimi;
        this.etunimi = etunimi;
        this.sahkoposti = sahkoposti;
        this.sukupuoli = sukupuoli;
        this.puhelinnumero = puhelinnumero;
        this.asiakastyyppi = asiakastyyppi;
    }

    public Asiakas(int id, String henkilotunnus, String value, String salasana, String value0, String sukunimi, String value1, String etunimi, String value2, String sahkoposti, String value3, String sukupuoli, String value4, String asiakastyyppi, String value5) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Palauttaa henkilön ID.
     *
     * @return ID
     */

    public int getId() {
        return id;
    }

    /**
     * Palauttaa henkilötunnuksen.
     *
     * @return henkilotunnus
     */
    public String getHenkilotunnus() {
        return henkilotunnus;
    }

    /**
     * Palauttaa salasanan.
     *
     * @return salasana
     */
    public String getSalasana() {
        return salasana;
    }

    /**
     * Palauttaa sukunimen.
     *
     * @return sukunimi
     */
    public String getSukunimi() {
        return sukunimi;
    }

    /**
     * Palauttaa etunimen.
     *
     * @return etunimi
     */
    public String getEtunimi() {
        return etunimi;
    }

    /**
     * Palauttaa sahköpostin.
     *
     * @return sahkoposti
     */
    public String getSahkoposti() {
        return sahkoposti;
    }

    /**
     * Palauttaa sukupuolen.
     *
     * @return sukupuoli
     */
    public String getSukupuoli() {
        return sukupuoli;
    }

    /**
     * Palauttaa puhelinnumeron.
     *
     * @return puhelinnumero
     */
    public String getPuhelinnumero() {
        return puhelinnumero;
    }

    /**
     * Palauttaa asiakastyypin.
     *
     * @return asiakastyyppi
     */
    public String getAsiakastyyppi() {
        return asiakastyyppi;
    }

    /**
     * Palauttaa asiakkaan kaikki tiedot.
     *
     * @return kaikki tiedot
     */
    @Override
    public String toString() {
        return "asiakas{" + "id=" + id + ", henkilotunnus=" + henkilotunnus + ", salasana=" + salasana + ", sukunimi=" + sukunimi + ", etunimi=" + etunimi + ", sahkoposti=" + sahkoposti + ", sukupuoli=" + sukupuoli + ", puhelinnumero=" + puhelinnumero + ", asiakastyyppi=" + asiakastyyppi + '}';
    }

}
