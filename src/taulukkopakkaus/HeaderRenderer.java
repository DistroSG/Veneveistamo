package taulukkopakkaus;

import java.awt.Color;
import java.awt.Component;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.RowSorter.SortKey;
import javax.swing.SortOrder;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;

/**
 * HeaderRenderer luokka. Jolla asennetaan taulukon Header.
 *
 * @author s1300778
 * @version 1.0
 */
public class HeaderRenderer extends JLabel implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table,
            Object value,
            boolean hasFocus,
            boolean isSelected,
            int row,
            int col) {
        setText(value.toString());
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.gray));
        if (this instanceof JLabel) {
            JLabel label = (JLabel) this;
            label.setIcon(getSortIcon(table, col));
            label.setIcon(getSortIcon(table, col));
        }

        table.getTableHeader().setDefaultRenderer(this);

        return this;

    }

    /**
     * Implements the logic to choose the appropriate icon.
     */
    private Icon getSortIcon(JTable table, int column) {
        SortOrder sortOrder = getColumnSortOrder(table, column);
        if (SortOrder.UNSORTED == sortOrder) {
            return null;
        }
        return SortOrder.ASCENDING
                == sortOrder ? UIManager.getIcon("Table.ascendingSortIcon")
                        : UIManager.getIcon("Table.descendingSortIcon");

    }

    private SortOrder getColumnSortOrder(JTable table, int column) {
        if (table == null || table.getRowSorter() == null) {
            return SortOrder.UNSORTED;
        }
        List<? extends SortKey> keys = table.getRowSorter().getSortKeys();
        if (keys.size() > 0) {
            SortKey key = keys.get(0);
            if (key.getColumn() == table.convertColumnIndexToModel(column)) {
                return key.getSortOrder();
            }
        }
        return SortOrder.UNSORTED;
    }
}
