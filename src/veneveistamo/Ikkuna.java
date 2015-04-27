/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package veneveistamo;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.regex.PatternSyntaxException;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import static javax.swing.GroupLayout.Alignment.LEADING;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author s1300778
 */
public class Ikkuna extends JFrame {

    private final JPanel oikeanosa = new JPanel();
    private final JPanel vasenosa = new JPanel();
    private final JPanel vasenylaosa = new JPanel();
    private final JPanel vasenalaosa = new JPanel();
    private final JPanel pohjapaneeli = new JPanel();
    private final JPanel menupaneeli = new JPanel(new FlowLayout(FlowLayout.LEADING));

    private final JLabel hakuSelite = new JLabel("Haku");
    private final JLabel elokuvaNroSelite = new JLabel("ElokuvaNro: ");
    private final JLabel nimiSelite = new JLabel("Nimi: ");
    private final JLabel ohjaajaSelite = new JLabel("Ohjaaja: ");
    private final JLabel vuosiSelite = new JLabel("Vuosi: ");

    private final JTextField hakuKentta = new JTextField(10);
    private final JTextField elokuvaNroKentta = new JTextField(10);
    private final JTextField nimiKentta = new JTextField(10);
    private final JTextField ohjaajaKentta = new JTextField(10);
    private final JTextField vuosiKentta = new JTextField(10);

    private final JButton tyhjennaValintaNappi = new JButton("Tyhjennä valinta");
    private final JButton lisaaNappi = new JButton("Lisää");
    private final JButton muutuNappi = new JButton("Muuta");
    private final JButton poistaNappi = new JButton("Poista");

    private final Tietovarasto rekisteri;

    private final JTable taulukko;
    private final TableRowSorter<TableModel> sorter;
    private final JScrollPane vieritettavaRuutu;
    private final Taulukkomalli malli;
    
    private final JSplitPane splitPane;

    private final JMenuBar menu = new JMenuBar();
    private final JMenu valinta = new JMenu("Valinnat");
    private final JMenuItem testi = new JMenuItem("Testi nappula :-)");

    public Ikkuna(Tietovarasto rekisteri, Taulukkomalli malli, String otsikko) {

        this.rekisteri = rekisteri;
        this.malli = malli;

        menupaneeli.add(menu);
        menu.add(valinta);
        valinta.add(testi);

        muutuNappi.setEnabled(false);
        poistaNappi.setEnabled(false);

        taulukko = new JTable(malli);
        taulukko.setFillsViewportHeight(true);
        sorter = new TableRowSorter<>(malli);
        taulukko.setRowSorter(sorter);
        taulukko.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        asennaSorting();
        vieritettavaRuutu = new JScrollPane(taulukko);
        asennaTaulukkoTyylit(malli.getColumnNames());

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                vasenosa, oikeanosa);

//Provide minimum sizes for the two components in the split pane
        Dimension minimumSize = new Dimension();
        vasenosa.setMinimumSize(minimumSize);
        oikeanosa.setMinimumSize(minimumSize);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerSize(15);

        asetteleKomponentit();

        this.add(pohjapaneeli);
        this.setTitle(otsikko);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();

        keyEvents();

        taulukko.getSelectionModel().addListSelectionListener(
                new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent event) {
                        int viewRow = taulukko.getSelectedRow();
                        if (viewRow < 0) {
                            //Selection got filtered away.
                            tyhjennaKentat();
                            elokuvaNroKentta.setEditable(true);
                            lisaaNappi.setEnabled(true);
                            muutuNappi.setEnabled(false);
                            poistaNappi.setEnabled(false);
                        } else {
                            elokuvaNroKentta.setText("" + malli.getValueAt(taulukko.getSelectedRow(), 0));
                            nimiKentta.setText("" + malli.getValueAt(taulukko.getSelectedRow(), 1));
                            ohjaajaKentta.setText("" + malli.getValueAt(taulukko.getSelectedRow(), 2));
                            vuosiKentta.setText("" + malli.getValueAt(taulukko.getSelectedRow(), 3));
                            elokuvaNroKentta.setEditable(false);
                            lisaaNappi.setEnabled(false);
                            muutuNappi.setEnabled(true);
                            poistaNappi.setEnabled(true);
                        }
                    }
                }
        );

        testi.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        lisaaNappi.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                suoritaLisays();
            }

        });

        muutuNappi.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                suoritaMuutos();
            }

        });

        poistaNappi.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                suoritaPoisto();
            }

        });

        tyhjennaValintaNappi.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                taulukko.clearSelection();
            }

        });
    }

    private void suoritaMuutos() {
        int hid = Integer.parseInt(elokuvaNroKentta.getText());
        int vuosi = Integer.parseInt(vuosiKentta.getText());
        rekisteri.paivitaElokuva(new Elokuva(hid, nimiKentta.getText(),
                ohjaajaKentta.getText(), vuosi));
        paivitaValintaLista();

    }

    private void suoritaPoisto() {
        int i = (int) malli.getValueAt(taulukko.getSelectedRow(), 0);
        rekisteri.poistaElokuva(i);
        paivitaValintaLista();

    }

    private void paivitaValintaLista() {
        int rowCount = malli.getRowCount();

        for (int i = rowCount - 1; i >= 0; i--) {
            malli.removeRow(i);

        }
        for (Elokuva elokuva : rekisteri.haeKaikkElokuvat()) {
            malli.addRow(Arrays.asList(elokuva.getElokuvaNro(), elokuva.getNimi(), elokuva.getOhjaaja(), elokuva.getVuosi()));
        }

    }

    private void suoritaLisays() {

        try {
            int eid = Integer.parseInt(elokuvaNroKentta.getText());
            int vuosi = Integer.parseInt(vuosiKentta.getText());
            rekisteri.lisaaElokuva(new Elokuva(eid, nimiKentta.getText(),
                    ohjaajaKentta.getText(), vuosi));
            tyhjennaKentat();
            paivitaValintaLista();

        } catch (NumberFormatException e) {
            virhe("ElokuvaNro:n ja vuoden pitää olla kokonaislukuja");
        }
    }

    private void virhe(String viesti) {
        JOptionPane.showMessageDialog(this,
                viesti, "Virhe", JOptionPane.ERROR_MESSAGE);
    }

    private void tyhjennaKentat() {
        elokuvaNroKentta.setText("");
        nimiKentta.setText("");
        ohjaajaKentta.setText("");
        vuosiKentta.setText("");
    }

    private void asetteleKomponentit() {

        //oikeanosa 
        GroupLayout oikeanosanAsettelu = new GroupLayout(oikeanosa);
        oikeanosa.setLayout(oikeanosanAsettelu);

        oikeanosanAsettelu.setAutoCreateContainerGaps(true);
        oikeanosanAsettelu.setAutoCreateGaps(true);

        //asetellaan X-suuntaan
        GroupLayout.SequentialGroup ylaosaRyhmaXO = oikeanosanAsettelu.createSequentialGroup();
        ylaosaRyhmaXO.addComponent(hakuSelite);
        ylaosaRyhmaXO.addComponent(hakuKentta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);
        ylaosaRyhmaXO.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 293, Short.MAX_VALUE);
        ylaosaRyhmaXO.addComponent(tyhjennaValintaNappi);

        GroupLayout.ParallelGroup pohjaXO = oikeanosanAsettelu.createParallelGroup();
        pohjaXO.addGroup(ylaosaRyhmaXO);
        pohjaXO.addComponent(vieritettavaRuutu);

        oikeanosanAsettelu.setHorizontalGroup(pohjaXO);

        //asetellaan Y-suuntaan
        GroupLayout.ParallelGroup ylaosaRyhmaYO = oikeanosanAsettelu.createParallelGroup();
        ylaosaRyhmaYO.addComponent(hakuSelite);
        ylaosaRyhmaYO.addComponent(hakuKentta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);
        ylaosaRyhmaYO.addComponent(tyhjennaValintaNappi);

        GroupLayout.SequentialGroup pohjaYO = oikeanosanAsettelu.createSequentialGroup();
        pohjaYO.addGroup(ylaosaRyhmaYO);
        pohjaYO.addComponent(vieritettavaRuutu);

        oikeanosanAsettelu.setVerticalGroup(pohjaYO);

        //vasenylaosa
        GroupLayout vasenylaosanAsettelu = new GroupLayout(vasenylaosa);
        vasenylaosa.setLayout(vasenylaosanAsettelu);

        vasenylaosanAsettelu.setAutoCreateContainerGaps(true);
        vasenylaosanAsettelu.setAutoCreateGaps(true);

        //asetellaan X-suuntaan
        GroupLayout.ParallelGroup seliteRyhmaXVY = vasenylaosanAsettelu.createParallelGroup();
        seliteRyhmaXVY.addComponent(elokuvaNroSelite);
        seliteRyhmaXVY.addComponent(nimiSelite);
        seliteRyhmaXVY.addComponent(ohjaajaSelite);
        seliteRyhmaXVY.addComponent(vuosiSelite);

        GroupLayout.ParallelGroup kenttaRyhmaXVY = vasenylaosanAsettelu.createParallelGroup();
        kenttaRyhmaXVY.addComponent(elokuvaNroKentta, GroupLayout.PREFERRED_SIZE,
                GroupLayout.DEFAULT_SIZE,
                GroupLayout.PREFERRED_SIZE);
        kenttaRyhmaXVY.addComponent(nimiKentta, GroupLayout.PREFERRED_SIZE,
                GroupLayout.DEFAULT_SIZE,
                GroupLayout.PREFERRED_SIZE);
        kenttaRyhmaXVY.addComponent(ohjaajaKentta, GroupLayout.PREFERRED_SIZE,
                GroupLayout.DEFAULT_SIZE,
                GroupLayout.PREFERRED_SIZE);
        kenttaRyhmaXVY.addComponent(vuosiKentta, GroupLayout.PREFERRED_SIZE,
                GroupLayout.DEFAULT_SIZE,
                GroupLayout.PREFERRED_SIZE);

        GroupLayout.SequentialGroup pohjaXVY = vasenylaosanAsettelu.createSequentialGroup();
        pohjaXVY.addGroup(seliteRyhmaXVY);
        pohjaXVY.addGroup(kenttaRyhmaXVY);

        vasenylaosanAsettelu.setHorizontalGroup(pohjaXVY);

        //asetellaan Y-suuntaan
        GroupLayout.ParallelGroup elokuvaNroRyhmaYVY = vasenylaosanAsettelu.createParallelGroup();
        elokuvaNroRyhmaYVY.addComponent(elokuvaNroSelite);
        elokuvaNroRyhmaYVY.addComponent(elokuvaNroKentta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);

        GroupLayout.ParallelGroup nimiRyhmaYVY = vasenylaosanAsettelu.createParallelGroup();
        nimiRyhmaYVY.addComponent(nimiSelite);
        nimiRyhmaYVY.addComponent(nimiKentta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);

        GroupLayout.ParallelGroup ohjaajaRyhmaYVY = vasenylaosanAsettelu.createParallelGroup();
        ohjaajaRyhmaYVY.addComponent(ohjaajaSelite);
        ohjaajaRyhmaYVY.addComponent(ohjaajaKentta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);

        GroupLayout.ParallelGroup vuosiRyhmaYVY = vasenylaosanAsettelu.createParallelGroup();
        vuosiRyhmaYVY.addComponent(vuosiSelite);
        vuosiRyhmaYVY.addComponent(vuosiKentta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);

        GroupLayout.SequentialGroup pohjaYVY = vasenylaosanAsettelu.createSequentialGroup();
        pohjaYVY.addGroup(elokuvaNroRyhmaYVY);
        pohjaYVY.addGroup(nimiRyhmaYVY);
        pohjaYVY.addGroup(ohjaajaRyhmaYVY);
        pohjaYVY.addGroup(vuosiRyhmaYVY);

        vasenylaosanAsettelu.setVerticalGroup(pohjaYVY);

        //vasenalaosa 
        GroupLayout vasenalaosanAsettelu = new GroupLayout(vasenalaosa);
        vasenalaosa.setLayout(vasenalaosanAsettelu);

        vasenalaosanAsettelu.setAutoCreateContainerGaps(true);
        vasenalaosanAsettelu.setAutoCreateGaps(true);

        //asetellaan X-suuntaan
        GroupLayout.SequentialGroup pohjaXVA = vasenalaosanAsettelu.createSequentialGroup();
        pohjaXVA.addComponent(lisaaNappi);
        pohjaXVA.addComponent(muutuNappi);
        pohjaXVA.addComponent(poistaNappi);

        vasenalaosanAsettelu.setHorizontalGroup(pohjaXVA);

        //asetellaan Y-suuntaan
        GroupLayout.ParallelGroup pohjaYVA = vasenalaosanAsettelu.createParallelGroup();
        pohjaYVA.addComponent(lisaaNappi);
        pohjaYVA.addComponent(muutuNappi);
        pohjaYVA.addComponent(poistaNappi);

        vasenalaosanAsettelu.setVerticalGroup(pohjaYVA);

        //vasenosa
        GroupLayout asetteluVasenosa = new GroupLayout(vasenosa);
        vasenosa.setLayout(asetteluVasenosa);

        //asetellaan X-suuntaan
        GroupLayout.ParallelGroup pohjaXV = asetteluVasenosa.createParallelGroup();
        pohjaXV.addComponent(vasenylaosa);
        pohjaXV.addComponent(vasenalaosa);

        asetteluVasenosa.setHorizontalGroup(pohjaXV);

        //asetellaan Y-suuntaan
        GroupLayout.SequentialGroup pohjaYV = asetteluVasenosa.createSequentialGroup();
        pohjaYV.addComponent(vasenylaosa);
        pohjaYV.addComponent(vasenalaosa);

        asetteluVasenosa.setVerticalGroup(pohjaYV);

        //pohja
        GroupLayout asetteluPohja = new GroupLayout(pohjapaneeli);
        pohjapaneeli.setLayout(asetteluPohja);

        //asetellaan X-suuntaan
        GroupLayout.ParallelGroup pohjaXP = asetteluPohja.createParallelGroup();
        pohjaXP.addComponent(menupaneeli, LEADING);
        pohjaXP.addComponent(splitPane);

        asetteluPohja.setHorizontalGroup(pohjaXP);

        //asetellaan Y-suuntaan
        GroupLayout.SequentialGroup pohjaYP = asetteluPohja.createSequentialGroup();
        pohjaYP.addComponent(menupaneeli);
        pohjaYP.addComponent(splitPane);

        asetteluPohja.setVerticalGroup(pohjaYP);

    }

    private void asennaSorting() {
        hakuKentta.getDocument().addDocumentListener(new DocumentListener() {

            private void searchFieldChangedUpdate(DocumentEvent evt) {
                String text = hakuKentta.getText();
                if (text.length() == 0) {
                    sorter.setRowFilter(null);
                    taulukko.clearSelection();
                } else {
                    try {
                        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                    } catch (PatternSyntaxException pse) {
                        JOptionPane.showMessageDialog(null, "Bad regex pattern", "Bad regex pattern", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

            @Override
            public void insertUpdate(DocumentEvent evt) {
                searchFieldChangedUpdate(evt);
            }

            @Override
            public void removeUpdate(DocumentEvent evt) {
                searchFieldChangedUpdate(evt);
            }

            @Override
            public void changedUpdate(DocumentEvent evt) {
                searchFieldChangedUpdate(evt);
            }
        });
    }

    private void asennaTaulukkoTyylit(String[] sarakeNimet) {
        taulukko.setShowVerticalLines(false);
        for (int i = 0; i < sarakeNimet.length; i++) {
            TableColumn column = taulukko.getColumnModel().getColumn(i);
            column.setHeaderRenderer(new HeaderRenderer());
        }

        vieritettavaRuutu.setBorder(BorderFactory.createEmptyBorder());
    }

    private void keyEvents() {
        taulukko.getInputMap().put(KeyStroke.getKeyStroke("ESCAPE "), "clear");
        taulukko.getActionMap().put("clear", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taulukko.clearSelection();
            }
        });
        taulukko.getInputMap().put(KeyStroke.getKeyStroke("DELETE "), "poisto");
        taulukko.getActionMap().put("poisto", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                suoritaPoisto();
            }
        });
    }

}
