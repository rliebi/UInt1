package viewModels;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import localization.Messages;
import domain.Library;
import domain.Loan;

public class LendingTableModel extends AbstractTableModel implements Observer{
	private static final long serialVersionUID = -5278540270938445385L;
	private static final SimpleDateFormat date = new SimpleDateFormat("dd.MM.yyyy");
	private static final DecimalFormat myFormat = new DecimalFormat("000");
	
	private Library library;

	public LendingTableModel(Library l) {
		l.addObserver(this);
		this.library = l;
//		this.loans = l.getOngoingLoans();
	}

	@Override
	public int getColumnCount() {
		return 5;
	}

	@Override
	public int getRowCount() {
		return library.getOngoingLoans().size();
	}

	// this method is called to set the value of each cell
	@Override
	public Object getValueAt(int row, int column) {
		Loan entity = library.getOngoingLoans().get(row);
		switch (column) {
		case 1:
			if(!entity.isOverdue()){return Messages.getString("Domain.Loan.OK");}
			else{
				String daysOverdue = "(" + library.getOngoingLoans().get(row).getDaysOverdue() + " ";
				daysOverdue += (library.getOngoingLoans().get(row).getDaysOverdue() == 1) ? Messages.getString("Global.Day") : Messages.getString("Global.Days");
				daysOverdue += ")";
				return Messages.getString("Domain.Loan.Overdue") + " " + daysOverdue;}
		case 0:
			return myFormat.format(entity.getCopy().getInventoryNumber());
		case 3:
			return entity.getCopy().getTitle();
		case 2:
			return date.format(entity.getPickupDate().getTime()); 
//			return date.format(entity.getdueDate().getTime()) + "("+ entity.getDaysLeft() + " days left)";
		case 4:
			return entity.getCustomer().getName() + " " + entity.getCustomer().getSurname();
		default:
			return "";
		}
	}

	// This method will be used to display the name of columns
	public String getColumnName(int col) {
		switch(col){
		case 1:
			return Messages.getString("LoansInventoryView.tblBooksInventoryStatus.text");
		case 0: 
			return Messages.getString("Domain.Copy.inventoryNumber");
		case 2: 
			return Messages.getString("Domain.Loan.pickupDate");
		case 3: 
			return Messages.getString("Domain.Book.title");
		case 4: 
			return Messages.getString("Domain.Customer.name");
		default:
			return null;
		}
	}
	

	@Override
	public void update(Observable o, Object modelRowEvent) {
		if (!(o instanceof Library)) {
			throw new IllegalStateException();
		}
		library= (Library) o;
		fireTableDataChanged();
	}
}
