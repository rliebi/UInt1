package viewModels;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import domain.Book;



public class BookTableModel extends AbstractTableModel {

	private static final String SHELF = "Shelf";
	private static final String TITEL = "Titel";
	/**
	 * 
	 */
	private static final long serialVersionUID = -5278540270938445385L;
	List<Book> wordsList;
	String headerList[] = new String[] { TITEL, SHELF};

	public BookTableModel(List<Book> list) {
		wordsList = list;
	}

	@Override
	public int getColumnCount() {
		return headerList.length;
	}

	@Override
	public int getRowCount() {
		return wordsList.size();
	}

	// this method is called to set the value of each cell
	@Override
	public Object getValueAt(int row, int column) {
		Book entity = null;
		entity = wordsList.get(row);
		switch (column) {

		case 0:
			return entity.getName();
		case 1:
			return entity.getShelf();
		default:
			return "";
		}
	}

	// This method will be used to display the name of columns
	public String getColumnName(int col) {
		return headerList[col];
	}
}