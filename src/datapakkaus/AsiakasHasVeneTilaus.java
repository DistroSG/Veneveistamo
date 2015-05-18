
package datapakkaus;


public class AsiakasHasVeneTilaus {
    
    private final int asiakasID;
    private final int veneTilausID;
    /**
     * Luodaan uutta asiakas has vene tilausta asiakas id:n ja venetilaus id:n avulla.
     * @param asiakasID
     * @param veneTilausID 
     */

    public AsiakasHasVeneTilaus(int asiakasID, int veneTilausID) {
        this.asiakasID = asiakasID;
        this.veneTilausID = veneTilausID;
    }
   /**
     * Palauttaa asiakkaan ID:n.
     *
     * @return asiakas id
     */
    public int getAsiakasID() {
        return asiakasID;
    }
   /**
     * Palauttaa venetilaus id:n.
     *
     * @return venetilaus id
     */
    public int getVeneTilausID() {
        return veneTilausID;
    }
    /**
     * Palauttaa asiakas has vene tilauksen kaikki tiedot.
     *
     * @return kaikki tiedot
     */
    @Override
    public String toString() {
        return "AsiakasHasVeneTilaus{" + "asiakasID=" + asiakasID + ", veneTilausID=" + veneTilausID + '}';
    }

    
    
}
