package domain;

import java.util.Observable;

import controll.LibraryEvent;

public class Copy extends Observable {

	public enum Condition {
		NEW, GOOD, DAMAGED, WASTE, LOST
	}

	public static long nextInventoryNumber = 1;

	private final long inventoryNumber;
	private final Book book;
	private Condition condition;

	public Copy(Book title) {
		this.book = title;
		inventoryNumber = nextInventoryNumber++;
		condition = Condition.NEW;
	}

	public Book getTitle() {
		return book;
	}

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
		fireChange();
	}

	private void fireChange() {
		setChanged();
		notifyObservers();
	}

	public void fireChange(LibraryEvent libraryEvent) {
		setChanged();
		notifyObservers(libraryEvent);
	}

	public long getInventoryNumber() {
		return inventoryNumber;
	}
}
