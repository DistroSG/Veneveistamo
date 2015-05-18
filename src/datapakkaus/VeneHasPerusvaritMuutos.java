package datapakkaus;

/**
 * VeneHasPerusvaritMuutos luokka. Jolla pidetään uusi ja vanha henkilön
 * tehtävät muutoksen varten.
 *
 * @author s1300723
 * @version 1.0
 */
public class VeneHasPerusvaritMuutos {

    private final VeneHasPerusvarit vanha;
    private final VeneHasPerusvarit uusi;

    /**
     * Luoda luokka, jossa on uusi ja vanha henkilön tehtävät kahden
     * VeneHasPerusvarit luokan avulla.
     *
     * @param uusi luokka, jossa on uudet tiedot.
     * @param vanha luokka, jossa on vanha tiedot.
     */
    public VeneHasPerusvaritMuutos(VeneHasPerusvarit uusi, VeneHasPerusvarit vanha) {
        this.vanha = vanha;
        this.uusi = uusi;
    }

    /**
     * Palauttaa VeneHasPerusvarit luokka, jossa on vanhat tiedot.
     *
     * @return VeneHasPerusvarit luokka, jossa on vanhat tiedot
     */
    public VeneHasPerusvarit getVanha() {
        return vanha;
    }

    /**
     * Palauttaa VeneHasPerusvarit luokka, jossa on uudet tiedot.
     *
     * @return VeneHasPerusvarit luokka, jossa on uudet tiedot.
     */
    public VeneHasPerusvarit getUusi() {
        return uusi;
    }

}
