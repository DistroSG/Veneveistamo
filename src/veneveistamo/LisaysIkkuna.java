package veneveistamo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class LisaysIkkuna extends AbstraktiLisaysJaHakuIkkuna {

    protected JPanel paneeli;
    private final JButton talletaNappi = new JButton("Talleta");
    private final JButton peruutaNappi = new JButton("Peruuta");

    public LisaysIkkuna(Tietovarasto rekisteri) {
        super(rekisteri);
        paneeli = vasenosa;
        asetteleKomponentit();
        this.pack();

        talletaNappi.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                suoritaTallennus();
            }

        });

        peruutaNappi.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                suoritaSuljetus();
            }

        });
    }

    private void asetteleKomponentit() {
        GroupLayout asettelu = new GroupLayout(paneeli);
        paneeli.setLayout(asettelu);

        asettelu.setAutoCreateContainerGaps(true);
        asettelu.setAutoCreateGaps(true);
        asettelu.linkSize(talletaNappi, peruutaNappi);

        //asetellaan X-suuntaan
        GroupLayout.ParallelGroup nappiRyhmaX = asettelu.createParallelGroup();
        nappiRyhmaX.addComponent(talletaNappi);
        nappiRyhmaX.addComponent(peruutaNappi);

        GroupLayout.ParallelGroup pohjaX = asettelu.createParallelGroup();
        pohjaX.addGroup(nappiRyhmaX);

        asettelu.setHorizontalGroup(pohjaX);

        //asetellaan Y-suuntaan
        GroupLayout.SequentialGroup nappiRyhmaY = asettelu.createSequentialGroup();
        nappiRyhmaY.addComponent(talletaNappi);
        nappiRyhmaY.addComponent(peruutaNappi);

        GroupLayout.SequentialGroup pohjaY = asettelu.createSequentialGroup();
        pohjaY.addGroup(nappiRyhmaY);

        asettelu.setVerticalGroup(pohjaY);

    }

    private void suoritaTallennus() {
        try {
            int eid = Integer.parseInt(elokuvaNro.getText());
            int vuosi = Integer.parseInt(this.vuosi.getText());
            rekisteri.lisaaElokuva(new Elokuva(eid, nimi.getText(),
                    ohjaaja.getText(), vuosi));
            Onnistuminen("Lisäys onnistui");
            tyhjennaKentat();
        } catch (NumberFormatException e) {
            virhe("ElokuvaNro:n ja vuoden pitää olla kokonaislukuja");
        }
    }

    private void suoritaSuljetus() {
        this.dispose();
    }
}
