package datapakkaus;

/**
 * HenkilostoHasTehtavaMuutos luokka. Jolla pidetään uusi ja vanha henkilön
 * tehtävät muutoksen varten.
 *
 * @author s1300778
 * @version 1.0
 */
public class AsiakasHasVeneTilausMuutos {

    private final AsiakasHasVeneTilaus vanha;
    private final AsiakasHasVeneTilaus uusi;

    /**
     * Luoda luokka, jossa on uusi ja vanha henkilön tehtävät kahden
     * HenkilostoHasTehtava luokan avulla.
     *
     * @param uusi luokka, jossa on uudet tiedot.
     * @param vanha luokka, jossa on vanha tiedot.
     */
    public AsiakasHasVeneTilausMuutos(AsiakasHasVeneTilaus uusi, AsiakasHasVeneTilaus vanha) {
        this.vanha = vanha;
        this.uusi = uusi;
    }

    /**
     * Palauttaa HenkilostoHasTehtava luokka, jossa on vanhat tiedot.
     *
     * @return HenkilostoHasTehtava luokka, jossa on vanhat tiedot
     */
    public AsiakasHasVeneTilaus getVanha() {
        return vanha;
    }

    /**
     * Palauttaa HenkilostoHasTehtava luokka, jossa on uudet tiedot.
     *
     * @return HenkilostoHasTehtava luokka, jossa on uudet tiedot.
     */
    public AsiakasHasVeneTilaus getUusi() {
        return uusi;
    }

}

