package datapakkaus;

import java.util.Objects;

//@author s1300748
 
public class Varusteet {
    private final int id;
    private final String varusteet;
    private final String kuvaus;
    private final String kuva;
    private final int takuu_id;
    private final double hinta;
    private final double alv;
  

        public Varusteet(int id, String varusteet, String kuvaus, String kuva, int takuu_id, double hinta, double alv) {
        this.id = id;
        this.varusteet = varusteet;
        this.kuvaus = kuvaus;
        this.kuva = kuva;
        this.takuu_id = takuu_id;
        this.hinta = hinta;
        this.alv = alv;
        
    }
    
    public int getId() {
        return id;
    }

    public String getVarusteet() {
        return varusteet;
    }
    
    public String getKuvaus() {
        return kuvaus;
    }

    public String getKuva() {
        return kuva;
    }

    public int getTakuu_id() {
        return takuu_id;
    }

    public double getHinta() {
        return hinta;
    }

    public double getAlv() {
        return alv;
    }
    
    
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.id;
        hash = 97 * hash + Objects.hashCode(this.varusteet);
        hash = 97 * hash + Objects.hashCode(this.kuvaus);
        hash = 97 * hash + Objects.hashCode(this.kuva);
        hash = 97 * hash + Objects.hashCode(this.takuu_id);
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.hinta) ^ (Double.doubleToLongBits(this.hinta) >>> 64));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.alv) ^ (Double.doubleToLongBits(this.alv) >>> 64));
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
        final Varusteet other = (Varusteet) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.varusteet, other.varusteet)) {
            return false;
        }
        if (!Objects.equals(this.kuvaus, other.kuvaus)) {
            return false;
        }
        if (!Objects.equals(this.kuva, other.kuva)) {
            return false;
        }
        if (this.takuu_id != other.takuu_id) {
            return false;
        }
        if (Double.doubleToLongBits(this.hinta) != Double.doubleToLongBits(other.hinta)) {
            return false;
        }
        if (Double.doubleToLongBits(this.alv) != Double.doubleToLongBits(other.alv)) {
            return false;
        }
        return true;
     }
  @Override
  public String toString() {
        return "varusteet{" + "id=" + id + ", varusteet=" + varusteet + ", kuvaus=" + kuvaus + ", kuva=" + kuva + ", takuu_id=" + takuu_id + ", hinta=" + hinta + ", alv=" + alv + '}';
    }
}