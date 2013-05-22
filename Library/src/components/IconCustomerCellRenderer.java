package components;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import settings.Icons;

public class IconCustomerCellRenderer extends DefaultTableCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5061635527116720885L;

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) { 
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		this.setIcon(null);
		if (value instanceof String && !value.toString().isEmpty()) {
			this.setIcon(Icons.IconEnum.CUSTOMER.getIcon());
		}
		return this;
	}
}
