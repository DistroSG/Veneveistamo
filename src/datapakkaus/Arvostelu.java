
package datapakkaus;

import java.util.Objects;


public class Arvostelu {
    private final int id;
    private final int asiakasid;
    private final String arvostelu;
    private final String pikkuarvostelu;

    public Arvostelu(int id, int asiakasid, String arvostelu, String pikkuarvostelu) {
        this.id = id;
        this.asiakasid = asiakasid;
        this.arvostelu = arvostelu;
        this.pikkuarvostelu = pikkuarvostelu;
    }

    @Override
    public String toString() {
        return "Arvostelu{" + "id=" + id + ", asiakasid=" + asiakasid + ", arvostelu=" + arvostelu + ", pikkuarvostelu=" + pikkuarvostelu + '}';
    }
    

    public int getId() {
        return id;
    }

    public int getAsiakasid() {
        return asiakasid;
    }

    public String getArvostelu() {
        return arvostelu;
    }

    public String getPikkuarvostelu() {
        return pikkuarvostelu;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.id;
        hash = 89 * hash + this.asiakasid;
        hash = 89 * hash + Objects.hashCode(this.arvostelu);
        hash = 89 * hash + Objects.hashCode(this.pikkuarvostelu);
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
        final Arvostelu other = (Arvostelu) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.asiakasid != other.asiakasid) {
            return false;
        }
        if (!Objects.equals(this.arvostelu, other.arvostelu)) {
            return false;
        }
        if (!Objects.equals(this.pikkuarvostelu, other.pikkuarvostelu)) {
            return false;
        }
        return true;
    }
    
    
    
}
