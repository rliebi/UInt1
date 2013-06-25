package viewModels;

import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import localization.Messages;
import domain.Copy.Condition;
import domain.Library;
import domain.Loan;

public class CopiesTableModel extends AbstractTableModel implements
		Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5278540270938445385L;
	private Library lib;

	public CopiesTableModel(Library lib) {

		this.lib = lib;
		lib.addObserver(this);
	}

	public Class<?> getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public int getRowCount() {
		if (lib == null)
			return 0;
		return lib.getCopies().size();
	}

	public boolean isCellEditable(int row, int col) {
		if (col == 3) {
			return true;
		} else {
			return false;
		}
	}

	// this method is called to set the value of each cell
	@Override
	public Object getValueAt(int row, int column) {
		if (row == -1) {
			return "";
		}
		switch (column) {

		case 0:
			return lib.getCopies().get(row).getInventoryNumber();
		case 1:
			if (lib.isCopyLent(lib.getCopies().get(row))) {
				return Messages.getString("Domain.Book.Unavailable") + " / "
						+ Messages.getString("Domain.Book.Lent");
			} else if (!lib.getCopies().get(row).isInLendable()) {
				return Messages.getString("Domain.Book.Unavailable") + " / "
						+ Messages.getString("Domain.Book.BadCondition");
			} else {
				return Messages.getString("Domain.Book.Available");
			}

		case 3:
			return lib.getCopies().get(row).getCondition();
		case 2:
			
			return lib.getCopies().get(row).getTitle().getName();
		default:
			return "";
		}

	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (columnIndex == 3) {
			lib.getCopies().get(rowIndex)
					.setCondition((Condition) aValue);
		}
	}

	// This method will be used to display the name of columns
	public String getColumnName(int col) {
		switch (col) {
		case 0:
			return Messages.getString("Domain.Copy.inventoryNumber");
		case 1:
			return Messages.getString("Domain.Copy.Status");
		case 2:
			return Messages.getString("Domain.Book.title");
		case 3:
			return Messages.getString("Domain.Copy.condition");
		default:
			return null;
		}
	}

	@Override
	public void update(Observable o, Object modelRowEvent) {

		fireTableDataChanged();
	}

}
