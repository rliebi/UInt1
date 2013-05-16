package components;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import settings.Icons;

public class IconCellRenderer extends DefaultTableCellRenderer {
        private static final long serialVersionUID = -2442980521730520783L;

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) { 
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                this.setIcon(null);
                if (value instanceof String) {
                        this.setIcon(Icons.getIcon(value.toString().trim()));
                }
                return this;
        }
}