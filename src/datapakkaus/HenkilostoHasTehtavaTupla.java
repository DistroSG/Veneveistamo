/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datapakkaus;

/**
 *
 * @author s1300778
 */
public class HenkilostoHasTehtavaTupla {

    private final HenkilostoHasTehtava vanha;
    private final HenkilostoHasTehtava uusi;

    public HenkilostoHasTehtavaTupla(HenkilostoHasTehtava uusi, HenkilostoHasTehtava vanha) {
        this.vanha = vanha;
        this.uusi = uusi;
    }

    public HenkilostoHasTehtava getVanha() {
        return vanha;
    }

    public HenkilostoHasTehtava getUusi() {
        return uusi;
    }

}
