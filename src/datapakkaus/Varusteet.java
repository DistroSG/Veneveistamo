package datapakkaus;

import java.util.Objects;

//@author s1300748
 
public class Varusteet {
    private final int id;
    private final String varusteet;
    private final String kuvaus;
    private final int takuu_id;
    private final double hinta;
    private final int alv;
  

        public Varusteet(int id, String varusteet, String kuvaus, int takuu_id, double hinta, int alv) {
        this.id = id;
        this.varusteet = varusteet;
        this.kuvaus = kuvaus;
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

    public int getTakuu_id() {
        return takuu_id;
    }

    public double getHinta() {
        return hinta;
    }

    public int getAlv() {
        return alv;
    }
    
  @Override
  public String toString() {
        return "varusteet{" + "id=" + id + ", varusteet=" + varusteet + ", kuvaus=" + kuvaus + ", takuu_id=" + takuu_id + ", hinta=" + hinta + ", alv=" + alv + '}';
    }
}