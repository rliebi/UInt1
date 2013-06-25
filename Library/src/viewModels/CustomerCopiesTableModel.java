package viewModels;

import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import localization.Messages;

import domain.Copy.Condition;
import domain.Customer;
import domain.Library;

public class CustomerCopiesTableModel extends AbstractTableModel implements Observer {

	String[] columnNames = { "Available", "Titel", "Condition" };
	/**
	 * 
	 */
	private static final long serialVersionUID = -5278540270938445385L;
	private Library lib;
	private Customer customer;

	public CustomerCopiesTableModel(Library lib, Customer theCustomer) {
		lib.addObserver(this);
		this.lib = lib;
		this.customer = theCustomer;
	}

	public Class<?> getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	@Override
	public int getColumnCount() {
		return 5;
	}

	@Override
	public int getRowCount() {
		if (lib == null) return 0; 
		return lib.getCustomerOngoingLoans(customer).size();
	}

	public boolean isCellEditable(int row, int col) {
		return false;
	}

	// this method is called to set the value of each cell
	@Override
	public Object getValueAt(int row, int column) {
		
		if(row == -1){
			return "";
		}
		switch (column) {
		case 1:
			return lib.getCustomerOngoingLoans(customer).get(row).getCopy().getInventoryNumber();
		case 0:
			if (lib.getCustomerOngoingLoans(customer).get(row).isOverdue() == false) {
				return Messages.getString("Domain.Loan.OK");
			} else {
				String daysOverdue = "(" + lib.getCustomerOngoingLoans(customer).get(row).getDaysOverdue() + " ";
				daysOverdue += (lib.getCustomerOngoingLoans(customer).get(row).getDaysOverdue() == 1) ? Messages.getString("Global.Day") : Messages.getString("Global.Days");
				daysOverdue += ")";
				return Messages.getString("Domain.Loan.Overdue") + " " + daysOverdue;
			}
		case 2:
			return lib.getCustomerOngoingLoans(customer).get(row).getPickupDate().getTime();
		case 3:
			return lib.getCustomerOngoingLoans(customer).get(row).getdueDatetoString();
		case 4:
			return lib.getCustomerOngoingLoans(customer).get(row).getCopy().getTitle().getName();


		default:
			return "";
		}
		
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (columnIndex == 3) {
			lib.getCustomerLoans(customer).get(rowIndex).getCopy()
					.setCondition((Condition) aValue);
		}
	}

	// This method will be used to display the name of columns
	public String getColumnName(int col) {
		switch (col) {
		case 0:
			return Messages.getString("LoansInventoryView.tblBooksInventoryStatus.text");
		case 1:
			return Messages.getString("Domain.Copy.inventoryNumber");
		case 2:
			return Messages.getString("Domain.Loan.pickupDate");
		case 3:
			return "Due Date";
		case 4:
			return Messages.getString("Domain.Book.title");
		default:
			return null;
		}
	}

	@Override
	public void update(Observable o, Object modelRowEvent) {

		fireTableDataChanged();
	}

}
