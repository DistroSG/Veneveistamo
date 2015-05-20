package datapakkaus;

/**
 * AsiakasHasVeneTilaus luokka. Jolla pidetään uusi ja vanha asiakkaan
 * tilaus muutosta varten.
 *
 * @author s1300776
 * @version 1.0
 */
public class AsiakasHasVeneTilausMuutos {

    private final AsiakasHasVeneTilaus vanha;
    private final AsiakasHasVeneTilaus uusi;

    /**
     * Luodaan luokka, jossa on uusi ja vanha asiakkaan tilaukset kahden
     * AsiakasHasVeneTilaus luokan avulla.
     *
     * @param uusi luokka, jossa on uudet tiedot.
     * @param vanha luokka, jossa on vanha tiedot.
     */
    public AsiakasHasVeneTilausMuutos(AsiakasHasVeneTilaus uusi, AsiakasHasVeneTilaus vanha) {
        this.vanha = vanha;
        this.uusi = uusi;
    }

    /**
     * Palauttaa AsiakasHasVeneTilaus luokka, jossa on vanhat tiedot.
     *
     * @return AsiakasHasVeneTilaus luokka, jossa on vanhat tiedot
     */
    public AsiakasHasVeneTilaus getVanha() {
        return vanha;
    }

    /**
     * Palauttaa AsiakasHasVeneTilaus luokka, jossa on uudet tiedot.
     *
     * @returnAsiakasHasVeneTilaus luokka, jossa on uudet tiedot.
     */
    public AsiakasHasVeneTilaus getUusi() {
        return uusi;
    }

}

