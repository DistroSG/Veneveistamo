package kayttoliittymapakkaus;

import taulukkopakkaus.Taulukkomalli;
import taulukkopakkaus.HeaderRenderer;
import taulukkopakkaus.SortedComboBoxModel;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
 * Ikkuna luokka. Jolla asennetaan ikkunoiden ulkomuoto.
 *
 * @author s1300778
 * @version 1.0
 */
public abstract class Ikkuna extends JFrame {

    private final JPanel oikeanosa = new JPanel();
    private final JPanel vasenosa = new JPanel();
    private final JPanel pohjapaneeli = new JPanel();

    /**
     * Syöttöpaneli, joka generoi kentät ja selittet
     */
    protected final Syottopaneeli syottopaneeli;

    private final JLabel hakuSelite = new JLabel("Haku");

    private final JTextField hakuKentta = new JTextField(10);

    private final JButton tyhjennaValintaNappi = new JButton("Tyhjennä valinta");
    private final JButton lisaaNappi = new JButton("Lisää");
    private final JButton muutuNappi = new JButton("Muuta");
    private final JButton poistaNappi = new JButton("Poista");

    /**
     * Taulukko, jossa on säilytetään tiedot.
     */
    protected JTable taulukko;
    private TableRowSorter<TableModel> lajittelija;
    private JScrollPane vieritettavaRuutu;

    /**
     * Taulukon malli
     */
    protected final Taulukkomalli malli;

    private final JSplitPane jakaaRuutu = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
            vasenosa, oikeanosa);

    /**
     * Taulukon arvot
     */
    protected String[] arvot;
    private String[] sarakenimet;
    private final String[] comboboxItems = {"Puhelinnumero", "Toimisto", "Henkilöstö", "Tehtävä", "Henkilöstö has tehtävä", "Maksu", "Materiaali", "Perusvarit", "Asiakas", "Vene Tilaus", "Kuljetus", "Malli", "Osoite", "Arvostelu"};
    private final SortedComboBoxModel<String> comboboxModel = new SortedComboBoxModel<>(comboboxItems);
    private final JComboBox<String> yhdistelmä = new JComboBox<>(comboboxModel);

    /**
     * Luoda uusi ikkuna otsikon avulla
     *
     * @param ikkunanNimi ikunan nimi
     */
    public Ikkuna(String ikkunanNimi) {
        sarakenimet = new String[]{};
        malli = new Taulukkomalli(sarakenimet);
        arvot = new String[malli.getColumnCount()];
        syottopaneeli = new Syottopaneeli(malli.getColumnNames());

        lisaaNappi.setEnabled(false);
        muutuNappi.setEnabled(false);
        poistaNappi.setEnabled(false);
        tyhjennaValintaNappi.setEnabled(false);
        hakuKentta.setEditable(false);

        taulukonasetus();
        splitPaneasetus();
        asetteleKomponentit();
        ikkunaasetus(ikkunanNimi);
        keyEvents();
        comboasetus(null);
        yhdistelmä.setSelectedItem(null); //tyhjennetaan valinta
        nappiasetus();
    }

    /**
     * Luoda uusi ikkuna otsikon, sarakenimien ja yhdistelmäIndeksen avulla
     *
     * @param ikkunanNimi ikunan otsikko
     * @param sarakenimet taulokon sarakenimet
     */
    public Ikkuna(String ikkunanNimi, String[] sarakenimet) {
        this.sarakenimet = sarakenimet;
        malli = new Taulukkomalli(sarakenimet);
        arvot = new String[malli.getColumnCount()];
        syottopaneeli = new Syottopaneeli(malli.getColumnNames());

        muutuNappi.setEnabled(false);
        poistaNappi.setEnabled(false);

        taulukonasetus();
        splitPaneasetus();
        asetteleKomponentit();
        ikkunaasetus(ikkunanNimi);
        keyEvents();
        comboasetus(ikkunanNimi);

        nappiasetus();
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

    private void comboasetus(String nimi) {
        yhdistelmä.setMaximumSize(yhdistelmä.getPreferredSize());
        yhdistelmä.setSelectedItem(nimi);
        yhdistelmä.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                kasitteleValinta();

            }

        });
    }

    private void kasitteleValinta() {
        if (yhdistelmä.getSelectedItem() == "Puhelinnumero") {

            sarakenimet = new String[]{"ID", "Puhelinnumero", "Toimisto ID"};
            new PuhelinumeroIkkuna("Puhelinnumero", sarakenimet).setVisible(true);

        } else if (yhdistelmä.getSelectedItem() == "Toimisto") {
            sarakenimet = new String[]{"ID", "Aukioloajat", "Katuosoite", "Postinumero", "Toimipaikka"};

            new ToimistoIkkuna("Toimisto", sarakenimet).setVisible(true);

        } else if (yhdistelmä.getSelectedItem() == "Henkilöstö") {
            sarakenimet = new String[]{"ID", "Sukunimi", "Etunimi", "Osasto", "Toimisto ID"};

            new HenkilostoIkkuna("Henkilöstö", sarakenimet).setVisible(true);
        } else if (yhdistelmä.getSelectedItem() == "Tehtävä") {
            sarakenimet = new String[]{"ID", "Tehtävä"};

            new TehtavaIkkuna("Tehtävä", sarakenimet).setVisible(true);
        } else if (yhdistelmä.getSelectedItem() == "Henkilöstö has tehtävä") {
            sarakenimet = new String[]{"Henkilöstö ID", "Tehtävä ID"};

            new HenkilostoHasTehtavaIkkuna("Henkilöstö has tehtävä", sarakenimet).setVisible(true);
        } else if (yhdistelmä.getSelectedItem() == "Maksu") {
            sarakenimet = new String[]{"EraNumero", "VeneTilaus ID", "Hinta", "Maksettupaiva", "Eräpäivä"};

            new MaksuIkkuna("Maksu", sarakenimet).setVisible(true);

        } else if (yhdistelmä.getSelectedItem() == "Materiaali") {
            sarakenimet = new String[]{"ID", "Materiaali"};

            new MateriaaliIkkuna("Materiaali", sarakenimet).setVisible(true);
        } else if (yhdistelmä.getSelectedItem() == "Perusvarit") {
            sarakenimet = new String[]{"ID", "Perusvarit"};

            new PerusvaritIkkuna("Perusvarit", sarakenimet).setVisible(true);
        } else if (yhdistelmä.getSelectedItem() == "Asiakas") {
            sarakenimet = new String[]{"AsiakasID", "Henkilötunnus", "Salasana", "Sukunimi", "Etunimi", "Sähköposti", "Sukupuoli", "Puhelinnumero", "Asiakastyyppi"};

            new AsiakasIkkuna("Asiakas", sarakenimet).setVisible(true);
        } else if (yhdistelmä.getSelectedItem() == "Vene Tilaus") {
            sarakenimet = new String[]{"ID", "Vene ID", "Henkilöstö ID", "Hinta", "Kuljetus ID", "Väri", "Edistyminen"};

            new VeneTilausIkkuna("Vene Tilaus", sarakenimet).setVisible(true);
        } else if (yhdistelmä.getSelectedItem() == "Malli") {
            sarakenimet = new String[]{"ID", "Malli", "Masto"};

            new MalliIkkuna("Malli", sarakenimet).setVisible(true);
        } else if (yhdistelmä.getSelectedItem() == "Kuljetus") {
            sarakenimet = new String[]{"ID", "Vastaanottaja", "Vastaanotto"};

            new KuljetusIkkuna("Kuljetus", sarakenimet).setVisible(true);

        } else if (yhdistelmä.getSelectedItem() == "Osoite") {
            sarakenimet = new String[]{"ID", "Katuosoite", "Postinumero", "Toimipaikka", "Yrityksennimi", "VenetilausID"};

            new OsoiteIkkuna("Osoite", sarakenimet).setVisible(true);
        } else if (yhdistelmä.getSelectedItem() == "Arvostelu") {
            sarakenimet = new String[]{"ID", "AsiakasID", "Arvostelu", "Pikkuarvostelu"};

            new ArvosteluIkkuna("Arvostelu", sarakenimet).setVisible(true);
        }
        this.dispose();
    }
//a

    private void ikkunaasetus(String otsikko) {
        this.add(pohjapaneeli);
        this.setTitle(otsikko);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();

    }

    private void splitPaneasetus() {
        //Provide minimum sizes for the two components in the split pane
        Dimension minimumSize = new Dimension();
        vasenosa.setMinimumSize(minimumSize);
        oikeanosa.setMinimumSize(minimumSize);
        jakaaRuutu.setOneTouchExpandable(true);
        jakaaRuutu.setDividerSize(15);
    }

    private void taulukonasetus() {
        taulukko = new JTable(malli);
        taulukko.setFillsViewportHeight(true);
        taulukko.getTableHeader().setReorderingAllowed(false);
        lajittelija = new TableRowSorter<>(malli);
        taulukko.setRowSorter(lajittelija);
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
                    arvot[i] = taulukko.getValueAt(taulukko.getSelectedRow(), i).toString();
                } catch (NullPointerException e) {
                    System.out.println(e);
                }
            }
            syottopaneeli.setArvot(arvot);
            syottopaneeli.setEditoitavissa(0, false);
            lisaaNappi.setEnabled(false);
            muutuNappi.setEnabled(true);
            poistaNappi.setEnabled(true);
        }
    }

    /**
     * Suorita lisays taulukkoon
     */
    public abstract void suoritaLisays();

    /**
     * Suorita muutos taulukossa
     */
    public abstract void suoritaMuutos();

    /**
     * Suorita poisto taulukosta
     */
    public abstract void suoritaPoisto();

    /**
     * hae kaikki tiedot taulokosta
     */
    public abstract void haeKaikkiTiedot();

    /**
     * päivitä kaikki tiedot ikkunan taulukossa
     */
    public void paivitaValintaLista() {
        int rowCount = malli.getRowCount();

        for (int rowID = rowCount - 1; rowID >= 0; rowID--) {
            malli.removeRow(rowID);

        }
        haeKaikkiTiedot();
    }

    /**
     * Ilmoittaa virhe käytäjälle viestin avulla
     *
     * @param viesti virhen viesti
     */
    protected void virhe(String viesti) {
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
        pohjaXP.addComponent(yhdistelmä);
        pohjaXP.addComponent(jakaaRuutu);

        asetteluPohja.setHorizontalGroup(pohjaXP);

        //asetellaan Y-suuntaan
        GroupLayout.SequentialGroup pohjaYP = asetteluPohja.createSequentialGroup();
        pohjaYP.addComponent(yhdistelmä);
        pohjaYP.addComponent(jakaaRuutu);

        asetteluPohja.setVerticalGroup(pohjaYP);
    }

    private void asennaSorting() {
        hakuKentta.getDocument().addDocumentListener(new DocumentListener() {

            private void searchFieldChangedUpdate(DocumentEvent evt) {
                String text = hakuKentta.getText();
                if (text.length() == 0) {
                    lajittelija.setRowFilter(null);
                    taulukko.clearSelection();
                } else {
                    try {
                        lajittelija.setRowFilter(RowFilter.regexFilter("(?i)" + text));

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
