package viewModels;

import java.util.List;

//import javax.swing.DefaultListCellRenderer;
//import javax.swing.JComboBox;
import javax.swing.table.AbstractTableModel;
//import javax.swing.table.DefaultTableModel;

import domain.Book;
import domain.Copy;
import domain.Library;

public class CopiesTableModel extends AbstractTableModel {

	String[] columnNames = {"Available","Condition","Titel","Nr"};
	/**
	 * 
	 */
	private static final long serialVersionUID = -5278540270938445385L;
	private Library lib;
	private List<Copy> wordList;
	public CopiesTableModel(Library lib, Book theBook) {
		wordList = lib.getCopiesOfBook(theBook);
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
		return wordList.size();
	}
	// this method is called to set the value of each cell
	@Override
	public Object getValueAt(int row, int column) {
		Copy entity = null;
		entity = wordList.get(row);
		switch (column) {

		case 0:
			return entity.getInventoryNumber();

		case 1:
			return entity.getTitle();
		case 2:
			return entity.getCondition();
		case 3:
			return (lib.isCopyLent(entity))?"Ist vergriffen": "Ist verfügbar";

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


}
