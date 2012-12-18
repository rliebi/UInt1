package viewModels;


import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import controll.ModelRowEvent;
import domain.Book;



public class BookTableModel extends AbstractTableModel implements Observer{

	private static final String SHELF = "Shelf";
	private static final String TITEL = "Titel";
	/**
	 * 
	 */
	private static final long serialVersionUID = -5278540270938445385L;
	List<Book> books;
	String headerList[] = new String[] { TITEL, SHELF};

	public BookTableModel(List<Book> list) {
		books = list;
	}

	@Override
	public int getColumnCount() {
		return headerList.length;
	}

	@Override
	public int getRowCount() {
		return books.size();
	}

	// this method is called to set the value of each cell
	@Override
	public Object getValueAt(int row, int column) {
		Book entity = null;
		entity = books.get(row);
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
	@Override
	public void update(Observable o, Object modelRowEvent) {
		if(modelRowEvent instanceof ModelRowEvent){
			switch((ModelRowEvent)modelRowEvent){
			case added:
				fireTableRowsInserted(books.indexOf(o),books.indexOf(o));
			case deleted:
				fireTableRowsDeleted(books.indexOf(o), books.indexOf(o));
			case returned:
				fireTableRowsDeleted(books.indexOf(o), books.indexOf(o));
			case updated:
				fireTableRowsUpdated(books.indexOf(o),books.indexOf(o));
			}
		} else {fireTableDataChanged();}
	}
	
}