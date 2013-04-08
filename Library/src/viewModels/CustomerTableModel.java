package viewModels;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import controll.LibraryEvent;

import domain.Customer;

public class CustomerTableModel  extends AbstractTableModel implements Observer{
	private static final String ID = "ID";
	private static final String Name = "Name";
	private static final String Surname = "Surname";
	private static final String Street = "Street";
	private static final String City = "City";
	/**
	 * 
	 */
	private static final long serialVersionUID = -5278540270938445385L;
	List<Customer> customers;
	String headerList[] = new String[] {ID, Name, Surname, Street, City};

	public CustomerTableModel(List<Customer> list) {
		for(Customer c : list){
			c.addObserver(this);
		}
		this.customers = list;
	}

	@Override
	public int getColumnCount() {
		return headerList.length;
	}

	@Override
	public int getRowCount() {
		return customers.size();
	}

	// this method is called to set the value of each cell
	@Override
	public Object getValueAt(int row, int column) {
		Customer entity = null;
		entity = customers.get(row);
		switch (column) {
		case 0:
			return new Integer(row);
		case 1:
			return entity.getName();
		case 2:
			return entity.getSurname();
		case 3:
			return entity.getStreet();
		case 4:
			return entity.getCity();
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
				fireTableRowsInserted(customers.indexOf(o),customers.indexOf(o));
			case deleted:
				fireTableRowsDeleted(customers.indexOf(o), customers.indexOf(o));
			case returned:
				fireTableRowsDeleted(customers.indexOf(o), customers.indexOf(o));
			case updated:
				fireTableRowsUpdated(customers.indexOf(o),customers.indexOf(o));
			}
		} else {fireTableDataChanged();}
	}
}