
package datapakkaus;

import java.util.Objects;


public class Arvostelu {
    private final int id;
    private final int asiakasid;
    private final String arvostelu;
    private final String pikkuarvostelu;

        /**
     * Luodaan uutta arvostelua, id:n, asiakasid:n, arvostelun ja pikkuarvostelun 
     * avulla.
     *
     * @param id arvostelu id. Esim. "1"
     * @param asiakasid asiakkaan ID. Esim. "1"
     * @param arvostelu asiakkaan arvostelu. Esim. "Arvostelu teksti"
     * @param pikkuarvostelu asiakkaan pieniarvostelu. Esim. "Pieniarvostelu 5/5"
     */
    public Arvostelu(int id, int asiakasid, String arvostelu, String pikkuarvostelu) {
        this.id = id;
        this.asiakasid = asiakasid;
        this.arvostelu = arvostelu;
        this.pikkuarvostelu = pikkuarvostelu;
    }

    
    
    /**
     * Palauttaa arvostelun kaikki tiedot.
     *
     * @return kaikki tiedot
     */
    @Override
    public String toString() {
        return "Arvostelu{" + "id=" + id + ", asiakasid=" + asiakasid + ", arvostelu=" + arvostelu + ", pikkuarvostelu=" + pikkuarvostelu + '}';
    }
    
  /**
     * Palauttaa henkilön ID.
     *
     * @return ID
     */
    public int getId() {
        return id;
    }
  /**
     * Palauttaa asiakas ID.
     *
     * @return ID
     */
    public int getAsiakasid() {
        return asiakasid;
    }
  /**
     * Palauttaa arvostelun.
     *
     * @return ID
     */
    public String getArvostelu() {
        return arvostelu;
    }
  /**
     * Palauttaa pikkuarvostelun.
     *
     * @return ID
     */
    public String getPikkuarvostelu() {
        return pikkuarvostelu;
    }

   
    
}
