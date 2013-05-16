package viewModels;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import domain.Book;
import domain.Copy;
import domain.Library;

public class CopiesTableModel extends AbstractTableModel implements Observer {

	String[] columnNames = {  "Available","Titel", "Condition" };
	/**
	 * 
	 */
	private static final long serialVersionUID = -5278540270938445385L;
	private Library lib;
	private List<Copy> copies;
	private Book book;

	public CopiesTableModel(Library lib, Book theBook) {
		lib.addObserver(this);
		copies = lib.getCopiesOfBook(theBook);
		this.lib = lib;
		this.book = theBook;
	}

	public Class<?> getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return copies.size();
	}
	public boolean isCellEditable(int row, int col) {
		return true;
	}
	// this method is called to set the value of each cell
	@Override
	public Object getValueAt(int row, int column) {
		Copy entity = null;
		entity = copies.get(row);
		switch (column) {

		case 1:
			// return entity.getInventoryNumber();
			return entity.getTitle();

		case 2:
			return entity.getCondition();

		case 0:
			return (lib.isCopyLent(entity)) ? "" : "available";

		default:
			return "";
		}
	}

	public void setValueAt(Object value, int row, int col) {

	}

	// This method will be used to display the name of columns
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public void update(Observable o, Object modelRowEvent) {
		if (!(o instanceof Library)) {
			throw new IllegalStateException();
		}
		Library lib = (Library) o;
		copies = lib.getCopiesOfBook(book);
		fireTableDataChanged();
	}

}
