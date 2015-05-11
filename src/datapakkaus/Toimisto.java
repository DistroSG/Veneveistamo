package datapakkaus;

/**
 * Toimisto luokka. Jolla pidetään toimiston tietoja.
 *
 * @author s1300778
 * @version 1.0
 */
public class Toimisto {

    private final int id;
    private final String aukioloajat;
    private final String katuosoite;
    private final String postinumero;
    private final String toimipaikka;

    /**
     * Luoda uutta toimistoa id:n, aukioloaikkoin, katuosoiten, postinumeron ja
     * toimipaikkaID:n avulla.
     *
     * @param id toimiston id. Esim. "1"
     * @param aukioloajat aukioloajat. Esim. "arikisin 9-15 lauantaisin ja
     * sunnuntaisin suljettu"
     * @param katuosoite katuosoite. Esim. "Hattulantie 2"
     * @param postinumero postinumero. Esim. "00820"
     * @param toimipaikka toimipaikka. Esim. "Helsinki"
     */
    public Toimisto(int id, String aukioloajat, String katuosoite, String postinumero, String toimipaikka) {
        this.id = id;
        this.aukioloajat = aukioloajat;
        this.katuosoite = katuosoite;
        this.postinumero = postinumero;
        this.toimipaikka = toimipaikka;
    }

    /**
     * Palauttaa toimiston ID.
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Palauttaa aukioloajat.
     *
     * @return aukioloajat
     */
    public String getAukioloajat() {
        return aukioloajat;
    }

    /**
     * Palauttaa katuosoite.
     *
     * @return katuosoite
     */
    public String getKatuosoite() {
        return katuosoite;
    }

    /**
     * Palauttaa postinumero.
     *
     * @return postinumero
     */
    public String getPostinumero() {
        return postinumero;
    }

    /**
     * Palauttaa toimipaikka.
     *
     * @return toimipaikka
     */
    public String getToimipaikka() {
        return toimipaikka;
    }

    /**
     * Palauttaa toimiston kaikki tiedot.
     *
     * @return kaikki tiedot
     */
    @Override
    public String toString() {
        return "Toimisto{" + "id=" + id + ", aukioloajat=" + aukioloajat + ", katuosoite=" + katuosoite + ", postinumero=" + postinumero + ", toimipaikka=" + toimipaikka + '}';
    }

}
