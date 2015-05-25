package datapakkaus;

//@author s1300748

public class VeneHasVarusteet {
    private int vene_id;
    private int erikoisvarusteet_id;
    private boolean vakiovarusteet;
  

        public VeneHasVarusteet(int vene_id, int erikoisvarusteet_id, boolean vakiovarusteet) {
        this.vene_id = vene_id;
        this.erikoisvarusteet_id = erikoisvarusteet_id;
        this.vakiovarusteet = vakiovarusteet;
        
    }
    
    public int getVene_id() {
        return vene_id;
    }

    public int getErikoisvarusteet_id() {
        return erikoisvarusteet_id;
    }

    public boolean getVakiovarusteet() {
        return vakiovarusteet;
    }

  @Override
  public String toString() {
        return "varusteet{" + "vene_id=" + erikoisvarusteet_id + ", erikoisvarusteet_id=" + ", vakiovarusteet=" + vakiovarusteet + '}';
    }
}
