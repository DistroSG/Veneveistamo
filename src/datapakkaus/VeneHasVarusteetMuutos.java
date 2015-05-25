package datapakkaus;

//@author s1300748

public class VeneHasVarusteetMuutos {

    private final VeneHasVarusteet vanha;
    private final VeneHasVarusteet uusi;


public VeneHasVarusteetMuutos(VeneHasVarusteet uusi, VeneHasVarusteet vanha) {
        this.vanha = vanha;
        this.uusi = uusi;
    }

    public VeneHasVarusteet getVanha() {
        return vanha;
    }

    public VeneHasVarusteet getUusi() {
        return uusi;
    }

}

