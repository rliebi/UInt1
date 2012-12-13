package viewModels;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import domain.Customer;

public class CustomerTableModel  extends AbstractTableModel{
	private static final String Name = "Name";
	private static final String Surname = "Surname";
	private static final String Street = "Street";
	private static final String City = "City";
	/**
	 * 
	 */
	private static final long serialVersionUID = -5278540270938445385L;
	List<Customer> wordsList;
	String headerList[] = new String[] { Name, Surname, Street, City};

	public CustomerTableModel(List<Customer> list) {
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
		Customer entity = null;
		entity = wordsList.get(row);
		switch (column) {

		case 0:
			return entity.getName();
		case 1:
			return entity.getSurname();
		case 2:
			return entity.getStreet();
		case 3:
			return entity.getCity();
		default:
			return "";
		}
	}

	// This method will be used to display the name of columns
	public String getColumnName(int col) {
		return headerList[col];
	}
}