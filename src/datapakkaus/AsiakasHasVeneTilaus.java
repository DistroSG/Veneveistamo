
package datapakkaus;


public class AsiakasHasVeneTilaus {
    
    private final int asiakasID;
    private final int veneTilausID;

    public AsiakasHasVeneTilaus(int asiakasID, int veneTilausID) {
        this.asiakasID = asiakasID;
        this.veneTilausID = veneTilausID;
    }

    public int getAsiakasID() {
        return asiakasID;
    }

    public int getVeneTilausID() {
        return veneTilausID;
    }

    @Override
    public String toString() {
        return "AsiakasHasVeneTilaus{" + "asiakasID=" + asiakasID + ", veneTilausID=" + veneTilausID + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.asiakasID;
        hash = 97 * hash + this.veneTilausID;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AsiakasHasVeneTilaus other = (AsiakasHasVeneTilaus) obj;
        if (this.asiakasID != other.asiakasID) {
            return false;
        }
        if (this.veneTilausID != other.veneTilausID) {
            return false;
        }
        return true;
    }
    
    
}
