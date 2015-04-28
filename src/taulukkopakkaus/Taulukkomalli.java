package taulukkopakkaus;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author s1300778
 */
public class Taulukkomalli extends AbstractTableModel {

    private final String[] sarakkeenOtsikot;
    private final List<List> data = new ArrayList();

    public Taulukkomalli(String[] sarakkeenOtsikot) {
        this.sarakkeenOtsikot = sarakkeenOtsikot;

    }

    @Override
    public int getColumnCount() {
        return sarakkeenOtsikot.length;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public String getColumnName(int col) {
        return sarakkeenOtsikot[col];
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    @Override
    public Object getValueAt(int row, int col) {
        return data.get(row).get(col);
    }

    public void addRow(List rowData) {
        data.add(rowData);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    public void removeRow(int row) {
        data.remove(row);
        fireTableRowsDeleted(row, row);
    }

    public void updateTabel() {
        fireTableDataChanged();
    }

    public String[] getColumnNames() {

        String[] columnNames = new String[this.getColumnCount()];

        for (int i = 0, columnCount = this.getColumnCount(); i < columnCount; i++) {
            columnNames[i] = this.getColumnName(i);
        }

        return columnNames;

    }
  

}
