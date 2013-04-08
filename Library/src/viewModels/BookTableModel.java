package viewModels;


import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import controll.LibraryEvent;
import domain.Book;
import domain.Library;



public class BookTableModel extends AbstractTableModel implements Observer{
	private static final String AVAILABLE = "Available";

	private static final String SHELF = "Shelf";
	private static final String TITEL = "Titel";
	/**
	 * 
	 */
	private static final long serialVersionUID = -5278540270938445385L;
	List<Book> books;
	Library lib;
	String headerList[] = new String[] {AVAILABLE, TITEL, SHELF};

	public BookTableModel(List<Book> list) {
		books = list;
	}
	public BookTableModel(Library lib){
		books = lib.getBooks();
		this.lib = lib;
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
//			return (lib.getLentCopiesOfBook(entity).size() < lib.getCopiesOfBook(entity).size())?"Ist da":"weg";
//			return (>0)?"Ist da":"weg";
			return "TODO";
//			return null;
		case 1:
			
			return entity.getName();
		case 2:
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
		if(modelRowEvent instanceof LibraryEvent){
			switch((LibraryEvent)modelRowEvent){
			case added:
				fireTableRowsInserted(books.indexOf(o),books.indexOf(o));
				break;
			case deleted:
				fireTableRowsDeleted(books.indexOf(o), books.indexOf(o));
				break;
			case returned:
				fireTableRowsDeleted(books.indexOf(o), books.indexOf(o));
				break;
			case updated:
				fireTableRowsUpdated(books.indexOf(o),books.indexOf(o));
				break;
			default:
				break;
			}
		} else {fireTableDataChanged();}
	}
	
}