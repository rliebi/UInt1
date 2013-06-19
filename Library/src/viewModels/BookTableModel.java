package viewModels;


import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import localization.Messages;

import controller.LibraryEvent;
import domain.Book;
import domain.Copy;
import domain.Library;


public class BookTableModel extends AbstractTableModel implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5278540270938445385L;
	List<Book> books;
	Library library;
	public BookTableModel(Library l) {
		l.addObserver(this);
		books = l.getBooks();
		library = l;
	}
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}
	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public int getRowCount() {
		return books.size();
	}

	// this method is called to set the value of each cell
	@Override
	public Object getValueAt(int arg0, int arg1) {
		List<Book> bookList = library.getBooks();
		List<Copy> avCopyList = library.getAvailableCopies();
		Object ret;
		switch(arg1){
		case 0: 
			ret = Messages.getString("Domain.Book.Unavailable");
			int counter = 0;
			for(Copy c: avCopyList) {
				if (c.getTitle().equals(bookList.get(arg0))) {
					counter++;
				}
			}
			if (counter > 0) {
				String numberOfCopies = "(" + counter ;
				numberOfCopies += ")";
				ret = Messages.getString("Domain.Book.Available") +" "+ numberOfCopies;
			}
			break;
		case 1: 
			ret = bookList.get(arg0).getName();
			break;
		case 2: 
			ret =  bookList.get(arg0).getAuthor();
			break;
		case 3: 
			ret = bookList.get(arg0).getPublisher();
			break;
		default:
			ret = 0;
		}
		return ret;
	}

	// This method will be used to display the name of columns
	@Override
	public String getColumnName(int column) {
		String ret;
		switch(column){
		case 0: 
			ret = Messages.getString("BookTab.tableHeaders.status.text");
			break;
		case 1: 
			ret = Messages.getString("Domain.Book.title");
			break;
		case 2: 
			ret = Messages.getString("Domain.Book.author");
			break;
		case 3: 
			ret = Messages.getString("Domain.Book.publisher");
			break;
		default:
			ret = null;
		}
		return ret;
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