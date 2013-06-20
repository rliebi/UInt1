package viewModels;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import localization.Messages;

import controller.LibraryEvent;

import domain.Customer;
import domain.Library;
import domain.Loan;

public class CustomerTableModel extends AbstractTableModel implements Observer {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5278540270938445385L;
	List<Customer> customers;
	private Library lib;

	public CustomerTableModel(Library l) {
		l.addObserver(this);
		lib = l;
		customers = l.getCustomers();
	}

	@Override
	public int getColumnCount() {
		return 6;
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
			int numberOfLoans = lib.getCustomerOngoingLoans(entity).size();
			int overdueLoansCounter = 0;
			for (Loan l : lib.getCustomerOngoingLoans(entity)) {
				if (l.isOverdue())
					overdueLoansCounter++;
			}
			String overdueText = "";
			String overdueTextWithBrakets = "";
			if (overdueLoansCounter > 0) {
				overdueText = overdueLoansCounter + " "
						+ Messages.getString("CustomersInventoryView.Overdue");
				overdueTextWithBrakets = "(" + overdueText + ")";
			}
			String loanText = (numberOfLoans == 1) ? Messages
					.getString("Global.Loan") : Messages
					.getString("Global.Loans");
			return (numberOfLoans == 0 || overdueLoansCounter != numberOfLoans) ? numberOfLoans
					+ " " + loanText + " " + overdueTextWithBrakets
					: overdueText;

		case 1:
			return entity.getName();
		case 2:
			return entity.getSurname();
		case 3:
			return entity.getStreet();
		case 5:
			return entity.getCity();
		case 4:
			return entity.getZip();
		default:
			return "";
		}
	}

	// This method will be used to display the name of columns
	public String getColumnName(int col) {
		switch (col) {
		case 0:
			return Messages.getString("Domain.Customer.status");
		case 1:
			return Messages.getString("Domain.Customer.name");
		case 2:
			return Messages.getString("Domain.Customer.surname");
		case 3:
			return Messages.getString("Domain.Customer.street");
		case 4:
			return Messages.getString("Domain.Customer.zip");
		case 5:
			return Messages.getString("Domain.Customer.city");
		}
		return null;
	}

	@Override
	public void update(Observable o, Object modelRowEvent) {
		if (modelRowEvent instanceof LibraryEvent) {
			switch ((LibraryEvent) modelRowEvent) {
			case added:
				fireTableRowsInserted(customers.indexOf(o),
						customers.indexOf(o));
				break;
			case deleted:
				fireTableRowsDeleted(customers.indexOf(o), customers.indexOf(o));
				break;
			case returned:
				fireTableRowsDeleted(customers.indexOf(o), customers.indexOf(o));
				break;
			case updated:
				fireTableRowsUpdated(customers.indexOf(o), customers.indexOf(o));
				break;
			default:
				break;
			}
		} else {
			fireTableDataChanged();
		}
	}
}