package viewModels;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import controll.LibraryEvent;

import domain.Customer;
import domain.Library;

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
	private static final String Loans = "#";
	List<Customer> customers;
	String headerList[] = new String[] {ID,Loans, Name, Surname, Street, City};
	private Library lib;
//	public CustomerTableModel(List<Customer> list) {
//		for(Customer c : list){
//			c.addObserver(this);
//		}
//		this.customers = list;
//	}
	
	public CustomerTableModel(Library l){
		l.addObserver(this);
		lib = l;
		customers= l.getCustomers();
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
		case 2:
			return entity.getName();
		case 3:
			return entity.getSurname();
		case 4:
			return entity.getStreet();
		case 5:
			return entity.getCity();
		case 1:
			return lib.getCustomerOngoingLoans(entity).size();
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
				break;
			case deleted:
				fireTableRowsDeleted(customers.indexOf(o), customers.indexOf(o));
				break;
			case returned:
				fireTableRowsDeleted(customers.indexOf(o), customers.indexOf(o));
				break;
			case updated:
				fireTableRowsUpdated(customers.indexOf(o),customers.indexOf(o));
				break;
			default:
				break;
			}
		} else {fireTableDataChanged();}
	}
}