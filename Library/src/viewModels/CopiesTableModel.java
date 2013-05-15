package viewModels;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

//import javax.swing.DefaultListCellRenderer;
//import javax.swing.JComboBox;
import javax.swing.table.AbstractTableModel;

import controll.LibraryEvent;
//import javax.swing.table.DefaultTableModel;

import domain.Book;
import domain.Copy;
import domain.Library;

public class CopiesTableModel extends AbstractTableModel implements Observer {

	String[] columnNames = {"Titel","Condition","Available"};
	/**
	 * 
	 */
	private static final long serialVersionUID = -5278540270938445385L;
	private Library lib;
	private List<Copy> copies;
	public CopiesTableModel(Library lib, Book theBook) {
		lib.addObserver(this);
		copies = lib.getCopiesOfBook(theBook);
		this.lib = lib;
		
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
	// this method is called to set the value of each cell
	@Override
	public Object getValueAt(int row, int column) {
		Copy entity = null;
		entity = copies.get(row);
		switch (column) {

		case 0:
//			return entity.getInventoryNumber();
			return entity.getTitle();

		case 1:
			return entity.getCondition();

		case 2:
			return (lib.isCopyLent(entity))?"": "available";


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
		if(modelRowEvent instanceof LibraryEvent){
			switch((LibraryEvent)modelRowEvent){
			case added:
				fireTableRowsInserted(copies.indexOf(o),copies.indexOf(o));
				break;
			case deleted:
				fireTableRowsDeleted(copies.indexOf(o), copies.indexOf(o));
				break;
			case returned:
				fireTableRowsDeleted(copies.indexOf(o), copies.indexOf(o));
				break;
			case updated:
				fireTableRowsUpdated(copies.indexOf(o),copies.indexOf(o));
				break;
			default:
				break;
			}
		} else {fireTableDataChanged();}
	}


}
