package datapakkaus;

/**
 * Tehtava luokka. Jolla pidetään tehtävän tietoja.
 *
 * @author s1300778
 * @version 1.0
 */
public class Tehtava {

    private final int id;
    private final String tehtava;

    /**
     * Luoda uutta tehtävää id:n ja tehtavan avulla.
     *
     * @param id tehtävän id. Esim. 1
     * @param tehtava tehtävä. Esim. Pääinsinööri
     */
    public Tehtava(int id, String tehtava) {
        this.id = id;
        this.tehtava = tehtava;
    }

    /**
     * Palauttaa tehtävän ID.
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Palauttaa tehtävä.
     *
     * @return tehtava
     */
    public String getTehtava() {
        return tehtava;
    }

    /**
     * Palauttaa tehtävän kaikki tiedot.
     *
     * @return kaikki tiedot
     */
    @Override
    public String toString() {
        return "Tehtava{" + "id=" + id + ", tehtava=" + tehtava + '}';
    }

}
