package components;

import java.awt.Component;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ComboBoxCellRenderer extends JComboBox<Object> implements TableCellRenderer {
   private static final long serialVersionUID = -8882388101338515739L;

   public ComboBoxCellRenderer(Object[] items) {
       super(items);
   }
   
   public Component getTableCellRendererComponent(JTable table, Object value,
           boolean isSelected, boolean hasFocus, int row, int column) {
       if (isSelected) {
//           setForeground(table.getSelectionForeground());
//           super.setBackground(table.getSelectionBackground());
       } else {
//           setForeground(table.getForeground());
//           setBackground(table.getBackground());
       }

       int modelRow = table.convertRowIndexToModel(row);
       setSelectedItem(table.getModel().getValueAt(modelRow, column));
       return this;
   }
   

}
