package application;

import java.util.List;

import javax.swing.table.AbstractTableModel;


import domain.Book;

class TableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5278540270938445385L;
	List<Book> wordsList;
	String headerList[] = new String[] { "Name", "Author", "Publisher", "Regal"};

	public TableModel(List<Book> list) {
		wordsList = list;
	}

	@Override
	public int getColumnCount() {
		return 4;
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
			return entity.getName();
		case 2:
			return entity.getPublisher();
		case 3:
			return entity.getShelf().name();
		default:
			return "";
		}
	}

	// This method will be used to display the name of columns
	public String getColumnName(int col) {
		return headerList[col];
	}
}