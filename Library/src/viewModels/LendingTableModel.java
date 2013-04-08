package viewModels;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import controll.LibraryEvent;
import domain.Library;
import domain.Loan;

public class LendingTableModel extends AbstractTableModel implements Observer{
	private static final String Status = "Status";
	private static final String ID = "ID";
	private static final String Titel = "Titel";
	private static final String Until = "Until";
	private static final String Customer = "Customer";
	private static final long serialVersionUID = -5278540270938445385L;
	private static final SimpleDateFormat date = new SimpleDateFormat("dd.MM.yyyy");
	private List<Loan> loans;
	
	String headerList[] = new String[] { Status, ID, Titel, Until, Customer};

	public LendingTableModel(Library l) {
		l.addObserver(this);
		this.loans = l.getLoans();
	}

	@Override
	public int getColumnCount() {
		return headerList.length;
	}

	@Override
	public int getRowCount() {
		return loans.size();
	}

	// this method is called to set the value of each cell
	@Override
	public Object getValueAt(int row, int column) {
		Loan entity = loans.get(row);
		switch (column) {
		case 0:
			if(!entity.isLent()){return "Ended";}
			if(!entity.isOverdue()){return "Ok";}
			else{return "Due!";}
		case 1:
			return entity.getCopy().getInventoryNumber();
		case 2:
			return entity.getCopy().getTitle();
		case 3:
			return date.format(entity.getdueDate().getTime()) + "("+ entity.getDaysLeft() + " days left)";
		case 4:
			return entity.getCustomer().getName() + " " + entity.getCustomer().getSurname();
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
		System.out.println("CHANGED");
		if(modelRowEvent instanceof LibraryEvent){
			switch((LibraryEvent)modelRowEvent){
			case added:
				fireTableRowsInserted(loans.indexOf(o),loans.indexOf(o));
				break;
			case deleted:
				fireTableRowsDeleted(loans.indexOf(o), loans.indexOf(o));
				break;
			case returned:
				fireTableRowsDeleted(loans.indexOf(o), loans.indexOf(o));
				break;
			case updated:
				fireTableRowsUpdated(loans.indexOf(o),loans.indexOf(o));
				break;
			default:
				break;
			}
		} else {fireTableDataChanged();}
	}
}
