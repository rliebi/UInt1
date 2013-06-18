package domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Observable;

import components.DateChooserComponent;

import controller.LibraryEvent;

public class Loan extends Observable{

	private Copy copy;
	private Customer customer;
	private GregorianCalendar pickupDate, returnDate;
	private final static int DAYS_TO_RETURN_BOOK = 30;
	private static final SimpleDateFormat date_formatter = new SimpleDateFormat("dd.MM.yyyy");

	public Loan(Customer customer, Copy copy) {
		this.copy = copy;
		this.customer = customer;
		pickupDate = new GregorianCalendar();
	}

	public boolean isLent() {
		return returnDate == null;
	}
	
	public void unreturnCopy(){
		returnDate=null;
		fireChanged(LibraryEvent.added);
	}
	public int getDaysOverdue(DateChooserComponent returnDate) {
		if ( !isOverdue() ) return 0;
		
		Date choosenReturnDate = returnDate.getDate();
		Calendar c = Calendar.getInstance();
		c.setTime(choosenReturnDate);
		
		
		GregorianCalendar dueDate = (GregorianCalendar) pickupDate.clone();
		dueDate.add(GregorianCalendar.DAY_OF_YEAR, DAYS_TO_RETURN_BOOK);
		
		return (int) (c.getTimeInMillis() - dueDate.getTimeInMillis())/ 1000 /60 /60 /24 +1;
	}
	public boolean returnCopy() {
		try {
			returnCopy(new GregorianCalendar());
		} catch (IllegalLoanOperationException e) {
			return false;
		}
		return true;
	}

	public void returnCopy(GregorianCalendar returnDate)
			throws IllegalLoanOperationException {
		if (returnDate.before(pickupDate)) {
			throw new IllegalLoanOperationException(
					"Return Date is before pickupDate");
		}
		this.returnDate = returnDate;
		fireChanged(LibraryEvent.returned);
	}

	public void setPickupDate(GregorianCalendar pickupDate)
			throws IllegalLoanOperationException {
		if (!isLent()) {
			throw new IllegalLoanOperationException("Loan is already retuned");
		}
		this.pickupDate = pickupDate;
		fireChanged(LibraryEvent.returned);
	}

	public GregorianCalendar getPickupDate() {
		return pickupDate;
	}
	public String getPickupDatetoString(){
		return getMyFormattedDate(pickupDate);
	}
	
	public GregorianCalendar getReturnDate() {
		return returnDate;
	}
	public String getReturnDatetoString(){
		return getMyFormattedDate(returnDate);
	}

	public Copy getCopy() {
		return copy;
	}

	public Customer getCustomer() {
		return customer;
	}

	@Override
	public String toString() {
		return "Loan of: " + copy.getTitle().getName() + "\tFrom: "
				+ customer.getName() + " " + customer.getSurname()
				+ "\tPick up: " + getFormattedDate(pickupDate) + "\tReturn: "
				+ getFormattedDate(returnDate) + "\tDays: "
				+ getDaysOfLoanDuration();
	}

	private String getFormattedDate(GregorianCalendar date) {
		if (date != null) {
			DateFormat f = SimpleDateFormat.getDateInstance();
			return f.format(date.getTime());
		}
		return "00.00.00";
	}
	private String getMyFormattedDate(GregorianCalendar date){
		return date_formatter.format(date.getTime());
	}
	public int getDaysOfLoanDuration() {
		if (returnDate != null)
			return (int) (returnDate.getTimeInMillis() - pickupDate
					.getTimeInMillis())
					/ 1000 / 60 / 60 / 24;
		return -1;
	}
	
	public int getDaysOverdue() {
		if ( !isOverdue() )
			return 0;
		
		GregorianCalendar dueDate = (GregorianCalendar) pickupDate.clone();
		dueDate.add(GregorianCalendar.DAY_OF_YEAR, DAYS_TO_RETURN_BOOK);
		
		return (int) (new GregorianCalendar().getTimeInMillis() - 
				dueDate.getTimeInMillis())/ 1000 /60 /60 /24;
	}
	
	public int getDaysLeft(){
		if(returnDate!=null||isOverdue()){
			return 0;
		}
		return (int) (new GregorianCalendar().getTimeInMillis() - getdueDate().getTimeInMillis())
				/ 1000 /60 /60 /24;
		
	}
	
	public boolean isOverdue() {
		if ( !isLent() )
			return false;
		GregorianCalendar dueDate = getdueDate();
		return ( new GregorianCalendar().after(dueDate) );
	}

	public GregorianCalendar getdueDate() {
		GregorianCalendar dueDate = (GregorianCalendar) pickupDate.clone();
		dueDate.add(GregorianCalendar.DAY_OF_YEAR, DAYS_TO_RETURN_BOOK);
		dueDate.add(GregorianCalendar.HOUR_OF_DAY, 23);
		dueDate.add(GregorianCalendar.MINUTE, 59);
		dueDate.add(GregorianCalendar.SECOND, 59);
		return dueDate;
	}
	public String getdueDatetoString(){
		return getMyFormattedDate(getdueDate());
	}

	public void fireChanged(LibraryEvent rowevent) {
		setChanged();
		notifyObservers(rowevent);
	}
}
