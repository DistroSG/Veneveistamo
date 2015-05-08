package taulukkopakkaus;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Taulukkomalli luokka. Jolla pidetään taulukkon sisältö.
 *
 * @author s1300778
 * @version 1.0
 */
public class Taulukkomalli extends AbstractTableModel {

    private final String[] sarakkeenOtsikot;
    private final List<List> data = new ArrayList();

    /**
     * Luoda uutta mallia sarakkeenOtsikot:n avulla.
     *
     * @param sarakkeenOtsikot otsikot, jotka tulevat taulokkoon.
     */
    public Taulukkomalli(String[] sarakkeenOtsikot) {
        this.sarakkeenOtsikot = sarakkeenOtsikot;

    }

    /**
     * Palauttaa sarakkeen lukumäärä.
     *
     * @return sarakkeen lukumäärä
     */
    @Override
    public int getColumnCount() {
        return sarakkeenOtsikot.length;
    }

    /**
     * Palauttaa rivin lukumäärä.
     *
     * @return rivin lukumäärä
     */
    @Override
    public int getRowCount() {
        return data.size();
    }

    /**
     * Palauttaa arvo valitusta solusta objektilla.
     *
     * @param row rivi, jossa on haluttu arvo.
     * @param col sarake, jossa on haluttu arvo.
     * @return arvo valitusta solusta objektilla.
     */
    @Override
    public Object getValueAt(int row, int col) {
        return data.get(row).get(col);
    }

    /**
     * Lisää rivi, jossa on tiedot, joka annetaan listalla.
     *
     * @param rowData lista, jossa on tiedot, jotka tarvi lisätä taulukkoon
     */
    public void addRow(List rowData) {
        data.add(rowData);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    /**
     * Poista rivi taulukosta
     *
     * @param row poistetava rivi
     */
    public void removeRow(int row) {
        data.remove(row);
        fireTableRowsDeleted(row, row);
    }

    /**
     * Palauta sarakkeen nimet taulukolla
     *
     * @return sarakkeen nimet taulukolla
     */
    public String[] getColumnNames() {

        String[] columnNames = new String[this.getColumnCount()];

        for (int i = 0, columnCount = this.getColumnCount(); i < columnCount; i++) {
            columnNames[i] = this.getColumnName(i);
        }

        return columnNames;

    }
}
