package datapakkaus;

/**
 * Puhelinnumero luokka. Jolla pidetään puhelinnumeron tietoja.
 *
 * @author s1300778
 * @version 1.0
 */
public class Puhelinnumero {

    private final int id;
    private final int puhelinnumero;
    private final int toimistoID;

    /**
     *
     * Luoda uutta puhelinnumeroa id:n, puhelinnumerin ja toimistoID:n avulla.
     *
     * @param id puhelinnumeron id. Esim. "1"
     * @param puhelinnumero puhelinnumero. Eism. "0417268917"
     * @param toimistoID tomiston id. Esim. "1"
     */
    public Puhelinnumero(int id, int puhelinnumero, int toimistoID) {
        this.id = id;
        this.puhelinnumero = puhelinnumero;
        this.toimistoID = toimistoID;
    }

    /**
     * Palauttaa puhelinnumeron ID.
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Palauttaa puhelinnumero.
     *
     * @return puhelinnumero
     */
    public int getPuhelinnumero() {
        return puhelinnumero;
    }

    /**
     * Palauttaa toimiston ID.
     *
     * @return toimistoID
     */
    public int getToimistoID() {
        return toimistoID;
    }

    /**
     * Palauttaa puhelinnumeron kaikki tiedot.
     *
     * @return kaikki tiedot
     */
    @Override
    public String toString() {
        return "Puhelinnumero{" + "id=" + id + ", puhelinnumero=" + puhelinnumero + ", toimistoID=" + toimistoID + '}';
    }

}
