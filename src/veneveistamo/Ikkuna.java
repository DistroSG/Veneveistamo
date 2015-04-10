/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package veneveistamo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.PatternSyntaxException;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author s1300778
 */
public class Ikkuna extends JFrame {

    private final JPanel pohjapaneeli = new JPanel();

    private final JTextField hakuKentta = new JTextField("Haku", 10);

    private final JButton lisaaNappi = new JButton("Lisää");
    private final JButton muutuNappi = new JButton("Muutu");
    private final JButton poistaNappi = new JButton("Poistaa");

    private final Tietovarasto rekisteri;

    private final JTable taulukko;
    private final TableRowSorter<TableModel> sorter;
    private final JScrollPane vieritettavaRuutu;

    public Ikkuna(Tietovarasto rekisteri, Taulukkomalli malli, String otsikko) {

        this.rekisteri = rekisteri;

        taulukko = new JTable(malli);
        taulukko.setFillsViewportHeight(true);
        sorter = new TableRowSorter<>(malli);
        taulukko.setRowSorter(sorter);
        asennaSorting();

        vieritettavaRuutu = new JScrollPane(taulukko);
        asennaTaulukkoTyylit(malli.getColumnNames());
        asetteleKomponentit();

        this.add(pohjapaneeli);
        this.setTitle(otsikko);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();

        lisaaNappi.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                suoritaLisays();
            }

        });
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

    private void asetteleKomponentit() {
        GroupLayout asettelu = new GroupLayout(pohjapaneeli);
        pohjapaneeli.setLayout(asettelu);

        asettelu.setAutoCreateContainerGaps(true);
        asettelu.setAutoCreateGaps(true);

        //asetellaan X-suuntaan
        GroupLayout.SequentialGroup ylaosaRyhmaX = asettelu.createSequentialGroup();
        ylaosaRyhmaX.addComponent(hakuKentta, GroupLayout.PREFERRED_SIZE,
                GroupLayout.DEFAULT_SIZE,
                GroupLayout.PREFERRED_SIZE);
        ylaosaRyhmaX.addComponent(lisaaNappi);
        ylaosaRyhmaX.addComponent(muutuNappi);
        ylaosaRyhmaX.addComponent(poistaNappi);

        GroupLayout.ParallelGroup pohjaX = asettelu.createParallelGroup();
        pohjaX.addGroup(ylaosaRyhmaX);
        pohjaX.addComponent(vieritettavaRuutu);

        asettelu.setHorizontalGroup(pohjaX);

        //asetellaan Y-suuntaan
        GroupLayout.ParallelGroup ylaosaRyhmaY = asettelu.createParallelGroup();
        ylaosaRyhmaY.addComponent(hakuKentta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);
        ylaosaRyhmaY.addComponent(lisaaNappi);
        ylaosaRyhmaY.addComponent(muutuNappi);
        ylaosaRyhmaY.addComponent(poistaNappi);

        GroupLayout.SequentialGroup pohjaY = asettelu.createSequentialGroup();
        pohjaY.addGroup(ylaosaRyhmaY);
        pohjaY.addComponent(vieritettavaRuutu);

        asettelu.setVerticalGroup(pohjaY);

    }

    private void suoritaLisays() {
        new LisaysIkkuna(rekisteri).setVisible(true);
    }

}
