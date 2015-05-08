package datapakkaus;

/**
 * HenkilostoHasTehtavaMuutos luokka. Jolla pidetään uusi ja vanha henkilön
 * tehtävät muutoksen varten.
 *
 * @author s1300778
 * @version 1.0
 */
public class HenkilostoHasTehtavaMuutos {

    private final HenkilostoHasTehtava vanha;
    private final HenkilostoHasTehtava uusi;

    /**
     * Luoda luokka, jossa on uusi ja vanha henkilön tehtävät kahden
     * HenkilostoHasTehtava luokan avulla.
     *
     * @param uusi luokka, jossa on uudet tiedot.
     * @param vanha luokka, jossa on vanha tiedot.
     */
    public HenkilostoHasTehtavaMuutos(HenkilostoHasTehtava uusi, HenkilostoHasTehtava vanha) {
        this.vanha = vanha;
        this.uusi = uusi;
    }

    /**
     * Palauttaa HenkilostoHasTehtava luokka, jossa on vanhat tiedot.
     *
     * @return HenkilostoHasTehtava luokka, jossa on vanhat tiedot
     */
    public HenkilostoHasTehtava getVanha() {
        return vanha;
    }

    /**
     * Palauttaa HenkilostoHasTehtava luokka, jossa on uudet tiedot.
     *
     * @return HenkilostoHasTehtava luokka, jossa on uudet tiedot.
     */
    public HenkilostoHasTehtava getUusi() {
        return uusi;
    }

}
