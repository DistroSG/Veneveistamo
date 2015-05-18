package datapakkaus;

import java.util.Objects;

/**
 *
 * @author Axeld
 * @author Axel
 */
public class VeneTilaus {

    private final int id;
    private final int vene_id;
    private final int henkilosto_id;
    private final double hinta;
    private final int kuljetus_id;
    private final String vari;
    private final String edistyminen;

    /**
     * Luo uuden tiedon ID, vene_ID, HenkilostoID, hinta, kuljetus_ID, vari ja edistyminen VeneTilaus tauluun.
     * @param id VeneTilauksen ID esim.1
     * @param vene_id veneen id esim. 1
     * @param henkilosto_id henkilöstön id esim. 1
     * @param hinta hinta esim. 10
     * @param kuljetus_id kuljetus id esim. 1
     * @param vari väri esim. punainen
     * @param edistyminen edistyminen esim. Vasta aloitettu.
     */
    public VeneTilaus(int id, int vene_id, int henkilosto_id, double hinta, int kuljetus_id, String vari, String edistyminen) {
        this.id = id;
        this.vene_id = vene_id;
        this.henkilosto_id = henkilosto_id;
        this.hinta = hinta;
        this.kuljetus_id = kuljetus_id;
        this.vari = vari;
        this.edistyminen = edistyminen;
    }
/**
 * Palauttaa ID arvon
 * 
 * @return id
 */
    public int getId() {
        return id;
    }
/**
 * Palauttaa Vene ID arvon.
 * 
 * @return vene_id
 */
    public int getVene_id() {
        return vene_id;
    }
/**
 * Palauttaa Henkilöstö id arvon
 * 
 * @return henkilosto_id
 */
    public int getHenkilosto_id() {
        return henkilosto_id;
    }
/**
 * Palauttaa hinta arvon.
 * 
 * @return hinta
 */
    public double getHinta() {
        return hinta;
    }
/**
 * Palauttaa Kuljetus id arvon.
 * 
 * @return kuljetus_id
 */
    public int getKuljetus_id() {
        return kuljetus_id;
    }
/**
 * Palauttaa vari tiedon
 * 
 * @return vari 
 */
    
    public String getVari() {
        return vari;
    }

    /**
     * Palauttaa edistyminen tiedon
     * 
     * @return edistyminen
     */
    public String getEdistyminen() {
        return edistyminen;
    }
/**
 * Palauttaa VeneTilaus taulun kaikki tiedot stringissä.
 * @return 
 */
    @Override
    public String toString() {
        return "venetilaus{" + "id=" + id + ", vene_id=" + vene_id + ", henkilosto_id=" + henkilosto_id + ", hinta=" + hinta + ", kuljetus_id=" + kuljetus_id + ", vari=" + vari + ", edistyminen=" + edistyminen + '}';
    }

   
   

}
