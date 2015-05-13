package datapakkaus;

/**
 * VeneHasMateriaaliMuutos luokka. Jolla pidetään uusi ja vanha henkilön
 * tehtävät muutoksen varten.
 *
 * @author s1300723
 * @version 1.0
 */
public class VeneHasMateriaaliMuutos {

    private final VeneHasMateriaali vanha;
    private final VeneHasMateriaali uusi;

    /**
     * Luoda luokka, jossa on uusi ja vanha henkilön tehtävät kahden
     * VeneHasMateriaali luokan avulla.
     *
     * @param uusi luokka, jossa on uudet tiedot.
     * @param vanha luokka, jossa on vanha tiedot.
     */
    public VeneHasMateriaaliMuutos(VeneHasMateriaali uusi, VeneHasMateriaali vanha) {
        this.vanha = vanha;
        this.uusi = uusi;
    }

    /**
     * Palauttaa VeneHasMateriaali luokka, jossa on vanhat tiedot.
     *
     * @return VeneHasMateriaali luokka, jossa on vanhat tiedot
     */
    public VeneHasMateriaali getVanha() {
        return vanha;
    }

    /**
     * Palauttaa VeneHasMateriaali luokka, jossa on uudet tiedot.
     *
     * @return VeneHasMateriaali luokka, jossa on uudet tiedot.
     */
    public VeneHasMateriaali getUusi() {
        return uusi;
    }

}
