package domain;

import java.util.List;

import javax.swing.table.AbstractTableModel;



public class TableModel extends AbstractTableModel {

	private static final String REGAL = "Regal";
	private static final String PUBLISHER = "Publisher";
	private static final String AUTHOR = "Author";
	private static final String NAME = "Name";
	/**
	 * 
	 */
	private static final long serialVersionUID = -5278540270938445385L;
	List<Book> wordsList;
	String headerList[] = new String[] { NAME, AUTHOR, PUBLISHER, REGAL};

	public TableModel(List<Book> list) {
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