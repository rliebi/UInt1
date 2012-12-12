package viewModels;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import domain.Loan;

public class LendingTableModel extends AbstractTableModel{
	private static final String Status = "Status";
	private static final String ID = "ID";
	private static final String Titel = "Titel";
	private static final String Until = "Until";
	private static final String Customer = "Customer";
	private static final long serialVersionUID = -5278540270938445385L;
	private static final SimpleDateFormat date = new SimpleDateFormat("dd.MM.yyyy");
	List<Loan> wordsList;
	
	String headerList[] = new String[] { Status, ID, Titel, Until, Customer};

	public LendingTableModel(List<Loan> list) {
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
		Loan entity = null;
		entity = wordsList.get(row);
		switch (column) {

		case 0:
			return entity.isOverdue();
		case 1:
			return entity.getCopy().getInventoryNumber();
		case 2:
			return entity.getCopy().getTitle();
		case 3:
			//return date.format(entity.getPickupDate().getTime());
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
}