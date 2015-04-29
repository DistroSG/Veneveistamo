/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittymapakkaus;

import taulukkopakkaus.Taulukkomalli;
import taulukkopakkaus.HeaderRenderer;
import tietovarastopakkaus.Tietovarasto;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.regex.PatternSyntaxException;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
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
    private final JPanel pohjapaneeli = new JPanel();
    private final Syottopaneeli syottopaneeli;

    private final JLabel hakuSelite = new JLabel("Haku");

    private final JTextField hakuKentta = new JTextField(10);

    private final JButton tyhjennaValintaNappi = new JButton("Tyhjennä valinta");
    private final JButton lisaaNappi = new JButton("Lisää");
    private final JButton muutuNappi = new JButton("Muuta");
    private final JButton poistaNappi = new JButton("Poista");

    private final Tietovarasto rekisteri = new Tietovarasto();

    private JTable taulukko;
    private TableRowSorter<TableModel> sorter;
    private JScrollPane vieritettavaRuutu;
    private final Taulukkomalli malli;

    private final JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
            vasenosa, oikeanosa);

    private String[] values;
    private String[] columnNames;

    private final JComboBox combo = new JComboBox(new String[]{"Elokuva", "Kuva", "Maksu", "Asiakas"});

    public Ikkuna(String otsikko) {
        columnNames = new String[]{};
        malli = new Taulukkomalli(columnNames);
        haeElovat();
        values = new String[malli.getColumnCount()];
        syottopaneeli = new Syottopaneeli(malli.getColumnNames());

        muutuNappi.setEnabled(false);
        poistaNappi.setEnabled(false);

        taulukonasetus();
        splitPaneasetus();
        asetteleKomponentit();
        ikkunaasetus(otsikko);
        keyEvents();
        comboasetus(0);
        combo.setSelectedItem(null); //tyhjennetaan valinta
        nappiasetus();
    }

    public Ikkuna(String otsikko, String[] columnNames, int comboIndex) {
        this.columnNames = columnNames;
        malli = new Taulukkomalli(columnNames);
        haeElovat();
        values = new String[malli.getColumnCount()];
        syottopaneeli = new Syottopaneeli(malli.getColumnNames());

        muutuNappi.setEnabled(false);
        poistaNappi.setEnabled(false);

        taulukonasetus();
        splitPaneasetus();
        asetteleKomponentit();
        ikkunaasetus(otsikko);
        keyEvents();
        comboasetus(comboIndex);

        nappiasetus();
    }

    Ikkuna(Tietovarasto rekisteri, Taulukkomalli malli, String test) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void nappiasetus() {
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

    private void comboasetus(int index) {
        combo.setMaximumSize(combo.getPreferredSize());
        combo.setSelectedIndex(index);
        combo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                kasitteleValinta();

            }

        });
    }

    private void ikkunaasetus(String otsikko) {
        this.add(pohjapaneeli);
        this.setTitle(otsikko);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.pack();

        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = ((int) tk.getScreenSize().getWidth());
        int ySize = ((int) tk.getScreenSize().getHeight());
        this.setSize(xSize, ySize);

    }

    private void splitPaneasetus() {
        //Provide minimum sizes for the two components in the split pane
        Dimension minimumSize = new Dimension();
        vasenosa.setMinimumSize(minimumSize);
        oikeanosa.setMinimumSize(minimumSize);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerSize(15);
    }

    private void taulukonasetus() {
        taulukko = new JTable(malli);
        taulukko.setFillsViewportHeight(true);
        sorter = new TableRowSorter<>(malli);
        taulukko.setRowSorter(sorter);
        taulukko.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        asennaSorting();
        vieritettavaRuutu = new JScrollPane(taulukko);
        asennaTaulukkoTyylit(malli.getColumnNames());

        taulukko.getSelectionModel().addListSelectionListener(
                new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent event) {
                        rivinValinta();
                    }

                }
        );
    }

    private void kasitteleValinta() {

        if (combo.getSelectedItem() == "Elokuva") {

            columnNames = new String[]{"№", "Nimi", "Ohjaja", "Vuosi"};
            new Ikkuna("Elokuva", columnNames, 0).setVisible(true);

            this.dispose();
        } else if (combo.getSelectedItem() == "Kuva") {
            columnNames = new String[]{"№Kuva", "Nimi", "Ohjaja", "Vuosi"};

            new Ikkuna("Elokuva", columnNames, 1).setVisible(true);
            this.dispose();

        }
        
        else if(combo.getSelectedItem()=="Maksu"){
            
            columnNames = new String[]{"Eränumero", "VeneTilausID", "Hinta", "Maksettupäivä"};
            
            new Ikkuna("Maksu", columnNames, 2).setVisible(true);
            this.dispose();
        }
        else if (combo.getSelectedItem() == "Asiakas") {
            columnNames = new String[]{"ID", "Henkilötunnus", "Salasana", "Etunimi", "Sähköposti", "Sukupuoli", "Puhelinnumero", "Asiakastyyppi"};

            new Ikkuna("Asiakas", columnNames, 3).setVisible(true);
            this.dispose();

        }

    }

    private void rivinValinta() {
        int viewRow = taulukko.getSelectedRow();
        if (viewRow < 0) {
            //Selection got filtered away.
            syottopaneeli.tyhjennaKentat();
            syottopaneeli.setEditoitavissa(0, true);
            lisaaNappi.setEnabled(true);
            muutuNappi.setEnabled(false);
            poistaNappi.setEnabled(false);
        } else {

            for (int i = 0; i < malli.getColumnCount(); i++) {
                try {
                    values[i] = taulukko.getValueAt(taulukko.getSelectedRow(), i).toString();
                } catch (NullPointerException e) {
                    System.out.println(e);
                }
            }
            syottopaneeli.setArvot(values);
            syottopaneeli.setEditoitavissa(0, false);
            lisaaNappi.setEnabled(false);
            muutuNappi.setEnabled(true);
            poistaNappi.setEnabled(true);
        }
    }

    private void suoritaMuutos() {
        values = syottopaneeli.getArvot();

        int hid = Integer.parseInt(values[0]);
        int vuosi = Integer.parseInt(values[3]);
//        rekisteri.paivitaElokuva(new Elokuva(hid, values[1], values[2], vuosi));
        paivitaValintaLista();

    }

    private void suoritaPoisto() {
        int i = (int) malli.getValueAt(taulukko.getSelectedRow(), 0);
//        rekisteri.poistaElokuva(i);
        paivitaValintaLista();

    }

    private void suoritaLisays() {
        values = syottopaneeli.getArvot();
        try {
            int eid = Integer.parseInt(values[0]);
            int vuosi = Integer.parseInt(values[3]);
//            rekisteri.lisaaElokuva(new Elokuva(eid, values[1],
//                    values[2], vuosi));
            syottopaneeli.tyhjennaKentat();
            paivitaValintaLista();

        } catch (NumberFormatException e) {
            virhe("ElokuvaNro:n ja vuoden pitää olla kokonaislukuja");
        }
    }

    private void paivitaValintaLista() {

        int rowCount = malli.getRowCount();

        for (int i = rowCount - 1; i >= 0; i--) {
            malli.removeRow(i);

        }
        haeElovat();

    }

    private void haeElovat() {
//        for (Elokuva elokuva : rekisteri.haeKaikkElokuvat()) {
//            malli.addRow(Arrays.asList(elokuva.getElokuvaNro(), elokuva.getNimi(), elokuva.getOhjaaja(), elokuva.getVuosi()));
//        }
    }

    private void virhe(String viesti) {
        JOptionPane.showMessageDialog(this,
                viesti, "Virhe", JOptionPane.ERROR_MESSAGE);
    }

    private void asetteleKomponentit() {
        asetteleOikeanosanKomponentit();
        asetteleVasenosanKomponentit();
        asettelePohjanKomponentit();

    }

    private void asetteleOikeanosanKomponentit() {
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
    }

    private void asetteleVasenosanKomponentit() {
        GroupLayout asetteluVasenosa = new GroupLayout(vasenosa);
        vasenosa.setLayout(asetteluVasenosa);
        asetteluVasenosa.setAutoCreateContainerGaps(true);
        asetteluVasenosa.setAutoCreateGaps(true);

        //asetellaan X-suuntaan
        GroupLayout.SequentialGroup pohjaXVA = asetteluVasenosa.createSequentialGroup();
        pohjaXVA.addComponent(lisaaNappi);
        pohjaXVA.addComponent(muutuNappi);
        pohjaXVA.addComponent(poistaNappi);

        GroupLayout.ParallelGroup pohjaXV = asetteluVasenosa.createParallelGroup();
        pohjaXV.addComponent(syottopaneeli);
        pohjaXV.addGroup(pohjaXVA);

        asetteluVasenosa.setHorizontalGroup(pohjaXV);

        //asetellaan Y-suuntaan
        GroupLayout.ParallelGroup pohjaYVA = asetteluVasenosa.createParallelGroup();
        pohjaYVA.addComponent(lisaaNappi);
        pohjaYVA.addComponent(muutuNappi);
        pohjaYVA.addComponent(poistaNappi);

        GroupLayout.SequentialGroup pohjaYV = asetteluVasenosa.createSequentialGroup();
        pohjaYV.addComponent(syottopaneeli);
        pohjaYV.addGroup(pohjaYVA);

        asetteluVasenosa.setVerticalGroup(pohjaYV);
    }

    private void asettelePohjanKomponentit() {
        //pohja
        GroupLayout asetteluPohja = new GroupLayout(pohjapaneeli);
        pohjapaneeli.setLayout(asetteluPohja);

        //asetellaan X-suuntaan
        GroupLayout.ParallelGroup pohjaXP = asetteluPohja.createParallelGroup();
        pohjaXP.addComponent(combo);
        pohjaXP.addComponent(splitPane);

        asetteluPohja.setHorizontalGroup(pohjaXP);

        //asetellaan Y-suuntaan
        GroupLayout.SequentialGroup pohjaYP = asetteluPohja.createSequentialGroup();
        pohjaYP.addComponent(combo);
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
