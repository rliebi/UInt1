package viewModels;

import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import localization.Messages;

import domain.Book;
import domain.Loan;

public class LoanCloseTableModel extends AbstractTableModel implements Observer{
	private static final long serialVersionUID = 4290250101362817162L;
	private List<Loan> loanList;
	
	public LoanCloseTableModel (List<Loan> loanList){
		super();
		this.loanList = loanList;
	}
	public LoanCloseTableModel() {
		super();
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public int getRowCount() {
		if (loanList == null) return 0; 
		return loanList.size();
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		Class<?> ret = null;
		switch(columnIndex) {
		case 0:
			ret = Long.class;
			break;
		case 1:
			ret = Date.class;
			break;
		case 2:
			ret = Book.class;
			break;
		case 3:
			ret = String.class;
			break;
		}
		return ret;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		Object ret;
		switch(arg1){
		case 1: 
			ret = loanList.get(arg0).getPickupDate().getTime();
			break;
		case 2: 
			ret = loanList.get(arg0).getCopy().getTitle();
			break;
		case 0: 
			ret = loanList.get(arg0).getCopy().getInventoryNumber();
			break;
		case 3: 
			ret = loanList.get(arg0).getCustomer().getName() + " " + loanList.get(arg0).getCustomer().getSurname();
			break;
		default:
			ret = 0;
		}
		return ret;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		this.fireTableDataChanged();
	}

	@Override
	public String getColumnName(int column) {
		String ret;
		switch(column){
		case 1: 
			ret = Messages.getString("Domain.Loan.pickupDate");
			break;
		case 2: 
			ret = Messages.getString("Domain.Book.title");
			break;
		case 0: 
			ret = Messages.getString("Domain.Copy.inventoryNumber");
			break;
		case 3: 
			ret = Messages.getString("Domain.Customer.name");
			break;
		default:
			ret = null;
		}
		return ret;
	}
}
