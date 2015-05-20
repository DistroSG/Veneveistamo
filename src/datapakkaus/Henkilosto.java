package datapakkaus;

/**
 * Henkilosto luokka. Jolla pidetään tietoja henkilöstä.
 *
 * @author s1300778
 * @version 1.0
 */
public class Henkilosto {

    private final int id;
    private final String sukunimi;
    private final String etunimi;
    private final String osasto;
    private final int toimistoID;
    private final String toimistoKatuosoite;

    /**
     * Luoda uutta henkilöä id:n, sukunimen, etunimen, osaston ja toimistoID:n
     * avulla.
     *
     * @param id henkilön id. Esim. "1"
     * @param sukunimi henkilön sukunimi. Esim. "Virtanen"
     * @param etunimi henkilön etunimi. Esim. "Pekka"
     * @param osasto henkilön osasto. Esim. "Myynti"
     * @param toimistoID henkilön toimistoID. Esim. "1"
     * @param toimistoKatuosoite toimiston katuosoite Esim. "Veneveistämökuja 1"
     */
    public Henkilosto(int id, String sukunimi, String etunimi, String osasto, int toimistoID, String toimistoKatuosoite) {
        this.id = id;
        this.sukunimi = sukunimi;
        this.etunimi = etunimi;
        this.osasto = osasto;
        this.toimistoID = toimistoID;
        this.toimistoKatuosoite = toimistoKatuosoite;
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
     * Palauttaa henkilön sukunimi.
     *
     * @return sukunimi
     */
    public String getSukunimi() {
        return sukunimi;
    }

    /**
     * Palauttaa henkilön etunimi.
     *
     * @return etunimi
     */
    public String getEtunimi() {
        return etunimi;
    }

    /**
     * Palauttaa henkilön osasto.
     *
     * @return osasto
     */
    public String getOsasto() {
        return osasto;
    }

    /**
     * Palauttaa henkilön toimistoID.
     *
     * @return toimistoID
     */
    public int getToimistoID() {
        return toimistoID;
    }

    /**
     * Palauttaa toimiston katuosoite.
     *
     * @return toimistoKatuosoite
     */
    public String getToimistoKatuosoite() {
        return toimistoKatuosoite;
    }

    /**
     * Palauttaa henkilön kaikki tiedot.
     *
     * @return kaikki tiedot
     */
    @Override
    public String toString() {
        return "Henkilosto{" + "id=" + id + ", sukunimi=" + sukunimi + ", etunimi=" + etunimi + ", osasto=" + osasto + ", toimistoID=" + toimistoID + ", toimistoKatuosoite=" + toimistoKatuosoite + '}';
    }

}
