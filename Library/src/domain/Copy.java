package domain;

import java.util.Observable;

import localization.Messages;

import controll.LibraryEvent;

public class Copy extends Observable {

	public enum Condition {
		NEW(Messages.getString("Condition.NEW.title")), 
		GOOD(Messages.getString("Condition.GOOD.title")), 
		DAMAGED(Messages.getString("Condition.DAMAGED.title")), 
		WASTE(Messages.getString("Condition.WASTE.title")), 
		LOST(Messages.getString("Condition.LOST.title"));
		private String conditionString;

		public String toString() {
			return conditionString;
		}

		private Condition(String titel) {
			this.conditionString = titel;
		}
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

	public boolean isInLendable() {
		return (condition != Condition.WASTE && condition != Condition.LOST);
	}
}
