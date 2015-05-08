package kayttoliittymapakkaus;

import javax.swing.*;

import static javax.swing.GroupLayout.Alignment.*;

/**
 * Veneveistamo luokka. Jolla suorita ohjelma.
 *
 * @author HELIL
 * @version 1.0
 */
public class Syottopaneeli extends JPanel {

    private final JTextField[] kentat;
    private final JLabel[] selitteet;

    private GroupLayout asettelu;

    /**
     * Luoda syottopaneeli selitteiden otsikkojen avulla
     *
     * @param otsikot selitteiden otsikot
     * @throws IllegalArgumentException
     */
    public Syottopaneeli(String[] otsikot)
            throws IllegalArgumentException {

        int koko = otsikot.length;
        kentat = new JTextField[koko];
        selitteet = new JLabel[koko];

        int i = 0;
        for (String kentta : otsikot) {
            kentat[i] = new JTextField(10);
            selitteet[i] = new JLabel(kentta);
            i++;
        }
        asetteleKomponentit();
    }

    /**
     * Asetele arvot kenttään
     *
     * @param arvot arvot, jotka asenetään
     * @throws IllegalArgumentException
     */
    public void setArvot(String[] arvot) throws IllegalArgumentException {
        if (kentat.length != arvot.length) {
            throw new IllegalArgumentException("Arvoja väärä määrä");
        }
        for (int i = 0; i < kentat.length; i++) {
            kentat[i].setText((arvot[i] != null ? arvot[i] : ""));
        }
    }

    /**
     *Palauta arvot String taulukolla
     * 
     * @return arvot String taulukolla
     */
    public String[] getArvot() {
        String[] arvot = new String[kentat.length];
        for (int i = 0; i < kentat.length; i++) {
            arvot[i] = kentat[i].getText();
        }
        return arvot;
    }

    /**
     *tyhjenna kaikki kentat
     */
    public void tyhjennaKentat() {
        for (JTextField tyhjentäväKentta : kentat) {
            tyhjentäväKentta.setText("");
        }
    }

    /**
     * asenna valitu kenta editoitavissa
     * @param indeksi valitu kenta
     * @param tila Muokattava(true) tai ei muokattava(false)
     * @throws IllegalArgumentException
     */
    public void setEditoitavissa(int indeksi, boolean tila) throws IllegalArgumentException {
        if (indeksi < 0 || indeksi >= kentat.length) {
            throw new IllegalArgumentException("Tiloja väärä määrä");
        }
        kentat[indeksi].setEditable(tila);
    }

    private void asetteleKomponentit() {
        asettelu = new GroupLayout(this);
        this.setLayout(asettelu);

        asettelu.setAutoCreateContainerGaps(false);
        asettelu.setAutoCreateGaps(true);

        //asetellaan X-suuntaan
        GroupLayout.ParallelGroup seliteryhmaX = asettelu.createParallelGroup();
        for (JLabel selite : selitteet) {
            seliteryhmaX.addComponent(selite);
        }

        GroupLayout.ParallelGroup kenttaryhmaX = asettelu.createParallelGroup();
        for (JTextField kentta : kentat) {
            kenttaryhmaX.addComponent(kentta, GroupLayout.PREFERRED_SIZE,
                    GroupLayout.DEFAULT_SIZE,
                    GroupLayout.PREFERRED_SIZE);
        }

        GroupLayout.Group pohjaX = asettelu.createSequentialGroup();
        pohjaX.addGroup(seliteryhmaX);
        pohjaX.addGroup(kenttaryhmaX);

        asettelu.setHorizontalGroup(pohjaX);

        //asetellaan Y-suuntaan
        GroupLayout.Group pohjaY = asettelu.createSequentialGroup();
        for (int i = 0; i < selitteet.length; i++) {
            pohjaY.addGroup(asettelu.createParallelGroup(BASELINE)
                    .addComponent(selitteet[i])
                    .addComponent(kentat[i]));
        }
        asettelu.setVerticalGroup(pohjaY);

    }
}
