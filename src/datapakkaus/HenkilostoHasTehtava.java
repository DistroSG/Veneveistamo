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
    private final int tehtavaID;

    /**
     *
     * Luoda uutta yhdistys henkilostoID:n ja toimistoID:n avulla.
     *
     * @param henkilostoID henkilön id. Esim. "1"
     * @param tehtavaID tehtävän id. Esim. "1"
     */
    public HenkilostoHasTehtava(int henkilostoID, int tehtavaID) {
        this.henkilostoID = henkilostoID;
        this.tehtavaID = tehtavaID;
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
     * Palauttaa henkilostoHasTehtava kaikki tiedot.
     *
     * @return kaikki tiedot
     */
    @Override
    public String toString() {
        return "HenkilostoHasTehtava{" + "henkilostoID=" + henkilostoID + ", tehtavaID=" + tehtavaID + '}';
    }

}
