package datapakkaus;

public class Osoite {

    private final int id;
    private final String katuosoite;
    private final String postinumero;
    private final String toimipaikka;
    private final String yrityksennimi;
    private final int venetilausid;

    /**
     * Luodaan uutta osoitetta  id:n, katuosoitteen, postinumeron, toimipaikan,
     * yrityksennimen ja venetilausid:n avulla.
     *
     * @param id osoite id. Esim. "1"
     * @param katuosoite katuosoite. Esim. "Hattulantie 2"
     * @param postinumero postinumero. Esim. "00400"
     * @param toimipaikka toimipaikka. Esim. "Helsinki"
     * @param yrityksennimi yrityksennimi. Esim. ""
     * @param venetilausid venetilausid. Esim. "1"
     */
    public Osoite(int id, String katuosoite, String postinumero, String toimipaikka, String yrityksennimi, int venetilausid) {
        this.id = id;
        this.katuosoite = katuosoite;
        this.postinumero = postinumero;
        this.toimipaikka = toimipaikka;
        this.yrityksennimi = yrityksennimi;
        this.venetilausid = venetilausid;
    }
   /**
     * Palauttaa osoitteen ID.
     *
     * @return ID
     */
    public int getId() {
        return id;
    }
   /**
     * Palauttaa katuosoitteen.
     *
     * @return katuosoite
     */
    public String getKatuosoite() {
        return katuosoite;
    }
   /**
     * Palauttaa postinumeron.
     *
     * @return postinumero
     */
    public String getPostinumero() {
        return postinumero;
    }
   /**
     * Palauttaa toimipaikan.
     *
     * @return toimipaikka
     */
    public String getToimipaikka() {
        return toimipaikka;
    }
   /**
     * Palauttaa yrityksennimen.
     *
     * @return yrityksennimi
     */
    public String getYrityksennimi() {
        return yrityksennimi;
    }
   /**
     * Palauttaa venetilaus ID:n.
     *
     * @return venetilausid
     */
    public int getVenetilausid() {
        return venetilausid;
    }
    /**
     * Palauttaa osoitteen kaikki tiedot.
     *
     * @return kaikki tiedot
     */
    @Override
    public String toString() {
        return "Osoite{" + "id=" + id + ", katuosoite=" + katuosoite + ", postinumero=" + postinumero + ", toimipaikka=" + toimipaikka + ", yrityksennimi=" + yrityksennimi + ", venetilausid=" + venetilausid + '}';
    }


}
