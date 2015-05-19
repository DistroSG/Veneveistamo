
package datapakkaus;

import java.util.Objects;


public class Arvostelu {
    private final int id;
    private final int asiakasid;
    private final String arvostelu;
    private final String pikkuarvostelu;
    private final String etunimi;
    private final String sukunimi;


        /**
     * Luodaan uutta arvostelua, id:n, asiakasid:n, arvostelun, pikkuarvostelun, etunimen ja sukunimen 
     * avulla.
     *
     * @param id arvostelu id. Esim. "1"
     * @param asiakasid asiakkaan ID. Esim. "1"
     * @param arvostelu asiakkaan arvostelu. Esim. "Arvostelu teksti"
     * @param pikkuarvostelu asiakkaan pieniarvostelu. Esim. "Pieniarvostelu 5/5"
     * @param etunimi asiakkaan etunimi. Esim. "Pekka"
     * @param sukunimi asiakkaan sukunimi. Esim. "Salainen"
     */
    public Arvostelu(int id, int asiakasid, String arvostelu, String pikkuarvostelu, String etunimi, String sukunimi) {
        this.id = id;
        this.asiakasid = asiakasid;
        this.arvostelu = arvostelu;
        this.pikkuarvostelu = pikkuarvostelu;
        this.etunimi = etunimi;
        this.sukunimi = sukunimi;
    }

    
    
    /**
     * Palauttaa arvostelun kaikki tiedot.
     *
     * @return kaikki tiedot
     */
    @Override
    public String toString() {
        return "Arvostelu{" + "id=" + id + ", asiakasid=" + asiakasid + ", arvostelu=" + arvostelu + ", pikkuarvostelu=" + pikkuarvostelu + ", etunimi=" + etunimi + ", sukunimi=" + sukunimi + '}';
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
     * @return asiakas ID
     */
    public int getAsiakasid() {
        return asiakasid;
    }
  /**
     * Palauttaa arvostelun.
     *
     * @return arvostelu
     */
    public String getArvostelu() {
        return arvostelu;
    }
  /**
     * Palauttaa pikkuarvostelun.
     *
     * @return pikkuarvostelu
     */
    public String getPikkuarvostelu() {
        return pikkuarvostelu;
    }
      /**
     * Palauttaa etunimen.
     *
     * @return etunimi
     */
    public String getEtunimi() {
        return etunimi;
    }
          /**
     * Palauttaa sukunimen.
     *
     * @return sukunimi
     */
       public String getSukunimi() {
        return sukunimi;
    }
    
}
