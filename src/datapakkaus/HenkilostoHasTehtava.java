package datapakkaus;

/**
 * HenkilostoHasTehtava luokka. Jolla pidetään tietoja millä henkilöllä on mikä
 * tehtävä.
 *
 * @author s1300778
 * @version 1.0
 */
public class HenkilostoHasTehtava {

    private final int henkilostoID;
    private final String henkilonNimi;
    private final int tehtavaID;
    private final String tehtava;

    /**
     *
     * Luoda uutta yhdistys henkilostoID:n ja toimistoID:n avulla.
     *
     * @param henkilostoID henkilön id. Esim. "1"
     * @param henkilonNimi henkilön nimi Esim. "Virtanen Pekka"
     * @param tehtavaID tehtävän id. Esim. "1"
     * @param Tehtava tehtävä. Esim "Pääinsinööri"
     */
    public HenkilostoHasTehtava(int henkilostoID, String henkilonNimi, int tehtavaID, String Tehtava) {
        this.henkilostoID = henkilostoID;
        this.henkilonNimi = henkilonNimi;
        this.tehtavaID = tehtavaID;
        this.tehtava = Tehtava;
    }

    /**
     * Palauttaa henkilönID.
     *
     * @return henkilostoID
     */
    public int getHenkilostoID() {
        return henkilostoID;
    }

    /**
     * Palauttaa TehtavaID.
     *
     * @return tehtavaID
     */
    public int getTehtavaID() {
        return tehtavaID;
    }

    /**
     * Palauttaa Henkilön nimi.
     *
     * @return henkilonNimi
     */
    public String getHenkilonNimi() {
        return henkilonNimi;
    }

    /**
     * Palauttaa tehtävä.
     *
     * @return Tehtava
     */
    public String getTehtava() {
        return tehtava;
    }

    /**
     * Palauttaa henkilostoHasTehtava kaikki tiedot.
     *
     * @return kaikki tiedot
     */
    @Override
    public String toString() {
        return "HenkilostoHasTehtava{" + "henkilostoID=" + henkilostoID + ", henkilonNimi=" + henkilonNimi + ", tehtavaID=" + tehtavaID + ", tehtava=" + tehtava + '}';
    }

}
