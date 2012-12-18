package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Library extends Observable implements Observer{

	private List<Copy> copies;
	private List<Customer> customers;
	private List<Loan> loans;
	private List<Book> books;
	private List<Loan> onGoingLoans;

	public Library() {
		copies = new ArrayList<Copy>();
		customers = new ArrayList<Customer>();
		loans = new ArrayList<Loan>();
		books = new ArrayList<Book>();
		updateOngoingLoans();
	}

	public Loan createAndAddLoan(Customer customer, Copy copy) {
		if (!isCopyLent(copy)) {
			Loan l = new Loan(customer, copy);
			l.addObserver(this);
			loans.add(l);
			fireChanged();
			return l;
		} else {
			fireChanged();
			return null;
		}
	}

	public Customer createAndAddCustomer(String name, String surname) {
		Customer c = new Customer(name, surname);
		c.addObserver(this);
		customers.add(c);
		fireChanged();
		return c;
	}

	public void createAndAddCustomer(Customer newCustomer) {
		customers.add(newCustomer);
		fireChanged();
	}

	private void fireChanged() {
		setChanged();
		notifyObservers();
	}

	public Book createAndAddBook(String name) {
		Book b = new Book(name);
		b.addObserver(this);
		books.add(b);
		fireChanged();
		return b;
	}

	public void addBook(Book realBook) {
		books.add(realBook);
		fireChanged();
	}

	public Copy createAndAddCopy(Book title) {
		Copy c = new Copy(title);
		c.addObserver(this);
		copies.add(c);
		fireChanged();
		return c;
	}
	public void removeCopy(Copy c){
		copies.remove(c);
		c.deleteObservers();
		fireChanged();
	}

	public Book findByBookTitle(String title) {
		for (Book b : books) {
			if (b.getName().equals(title)) {
				return b;
			}
		}
		return null;
	}

	public boolean isCopyLent(Copy copy) {
		for (Loan l : loans) {
			if (l.getCopy().equals(copy) && l.isLent()) {
				return true;
			}
		}
		return false;
	}

	public List<Copy> getCopiesOfBook(Book book) {
		List<Copy> res = new ArrayList<Copy>();
		for (Copy c : copies) {
			if (c.getTitle().equals(book)) {
				res.add(c);
			}
		}

		return res;
	}

	public List<Loan> getLentCopiesOfBook(Book book) {
		List<Loan> lentCopies = new ArrayList<Loan>();
		for (Loan l : loans) {
			if (l.getCopy().getTitle().equals(book) && l.isLent()) {
				lentCopies.add(l);
			}
		}
		return lentCopies;
	}

	public List<Loan> getCustomerLoans(Customer customer) {
		List<Loan> lentCopies = new ArrayList<Loan>();
		for (Loan l : loans) {
			if (l.getCustomer().equals(customer)) {
				lentCopies.add(l);
			}
		}
		return lentCopies;
	}
	public List<Loan> getCustomerOngoingLoans(Customer customer){
		List<Loan> lentCopies = new ArrayList<Loan>();
		for (Loan l : loans) {
			if (l.getCustomer().equals(customer)&&l.isLent()) {
				lentCopies.add(l);
			}
		}
		return lentCopies;
	}
	
	public List<Loan> getOngoingLoans() {
		return onGoingLoans;
	}

	public List<Loan> getOverdueLoans() {
		List<Loan> overdueLoans = new ArrayList<Loan>();
		for ( Loan l : getLoans() ) {
			if (l.isOverdue())
				overdueLoans.add(l);
		}
		return overdueLoans;
	}
	
	public List<Copy> getAvailableCopies(){
		return getCopies(false);
	}
	
	public List<Copy> getLentOutCopies(){
		return getCopies(true);
	}

	private List<Copy> getCopies(boolean isLent) {
		List<Copy> retCopies = new ArrayList<Copy>();
		for (Copy c : copies) {
			if (isLent == isCopyLent(c)) {
				retCopies.add(c);
			}
		}
		return retCopies;
	}

	public List<Copy> getCopies() {
		return copies;
	}

	public List<Loan> getLoans() {
		return loans;
	}

	public List<Book> getBooks() {
		return books;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof Loan&& !((Loan) o).isLent()){
			updateOngoingLoans();
		}
		fireChanged();
	}

	private void updateOngoingLoans() {
		List<Loan> answer=new ArrayList<Loan>();
		for(Loan l : loans){
			if(l.isLent()){answer.add(l);}
		}
		onGoingLoans=answer;
	}

}
