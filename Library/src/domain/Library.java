package domain;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import components.LibraryExcption;

import controller.LibraryEvent;

public class Library extends Observable implements Observer {

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
		onGoingLoans = new ArrayList<Loan>();

	}

	public Loan tryCreateAndAddLoan(Customer customer, Copy copy)
			throws LibraryExcption {
		if (isCopyLent(copy)) {
			throw new LibraryExcption("Copy is already Lent");

		}
		if (getCustomerOngoingLoans(customer).size() >= 3) {
			throw new LibraryExcption("Cannot have more than 3 Loans");
		}
		return createAndAddLoan(customer, copy);
	}

	public Loan createAndAddLoan(Customer customer, Copy copy) {
		if (!isCopyLent(copy)) {
			Loan l = new Loan(customer, copy);
			l.addObserver(this);
			loans.add(l);
			fireChanged();
			onGoingLoans.add(l);
			return l;
		} else {
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

	public void addCustomer(Customer newCustomer) {
		newCustomer.addObserver(this);
		customers.add(newCustomer);
		newCustomer.fireChange(LibraryEvent.added);
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
		realBook.addObserver(this);
		books.add(realBook);
		realBook.fireChange(LibraryEvent.added);
		fireChanged();
	}

	public Copy createAndAddCopy(Book title) {
		Copy c = new Copy(title);
		c.addObserver(this);
		copies.add(c);
		// c.fireChange(LibraryEvent.added);
		fireChanged();
		return c;
	}

	public boolean removeCopy(Copy c) {
		boolean returnval = copies.remove(c);
		fireChanged();
		return returnval;
	}

	public void removeCopies(List<Copy> c) {
		for (Copy copy : c) {
			removeCopy(copy);
		}
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

	public List<Copy> getAvailableCopiesOfBook(Book book) {
		List<Copy> res = new ArrayList<Copy>();
		for (Copy c : copies) {
			if (c.getTitle().equals(book) && !isCopyLent(c)) {
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

	public List<Loan> getCustomerOngoingLoans(Customer customer) {
		List<Loan> lentCopies = new ArrayList<Loan>();
		for (Loan l : loans) {
			if (l.getCustomer().equals(customer) && l.isLent()) {
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
		for (Loan l : getLoans()) {
			if (l.isOverdue())
				overdueLoans.add(l);
		}
		return overdueLoans;
	}

	public List<Copy> getAvailableCopies() {
		return getCopies(false);
	}

	public List<Copy> getLentOutCopies() {
		return getCopies(true);
	}

	private List<Copy> getCopies(boolean isLent) {
		List<Copy> retCopies = new ArrayList<Copy>();
		for (Copy c : copies) {
			if (isLent == isCopyLent(c) && c.isInLendable()) {
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
		if (o instanceof Loan && !((Loan) o).isLent()) {
			onGoingLoans.remove(onGoingLoans.indexOf(o));
		}
		fireChanged();
	}

	public List<Loan> getOpenLoans() {
		List<Loan> ret = new ArrayList<Loan>();
		for (Loan l : loans) {
			if (l.isLent())
				ret.add(l);
		}
		return ret;
	}

	public void returnCopy(Loan loan, GregorianCalendar date)
			throws IllegalLoanOperationException {
		loan.returnCopy(date);
		fireChanged();
	}

	public boolean isCopyLendable(Copy c) {
		if (isCopyLent(c)) {
			return false;
		} else if (!c.isInLendable()) {
			return false;
		} else {
			return true;
		}
	}

	public boolean isCustomerTrustworthy(Customer customer) {
		List<Loan> list = getCustomerOngoingLoans(customer);
		for (Loan loan : list) {
			if (loan.isOverdue())
				return false;
		}
		if (list.size() >= Setting.getMaxBorrowsPerCustomer())
			return false;
		return true;
	}
}
