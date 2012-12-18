package domain;

import java.util.Observable;

import controll.ModelRowEvent;

public class Book extends Observable{
	
	private String title, author, publisher;
	private Shelf shelf;
	
	public Book(String name) {
		this.title = name;
	}

	public String getName() {
		return title;
	}

	public void setName(String name) {
		this.title = name;
		fireChange(ModelRowEvent.updated);
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String autor) {
		this.author = autor;
		fireChange(ModelRowEvent.updated);
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
		fireChange(ModelRowEvent.updated);
	}
	
	public Shelf getShelf() {
		return shelf;
	}
	
	public void setShelf(Shelf shelf) {
		this.shelf = shelf;
		fireChange(ModelRowEvent.updated);
	}
	
	@Override
	public String toString() {
		return title + ", " + author + ", " + publisher;
	}

	public void fireChange(ModelRowEvent modelRowEvent) {
		setChanged();
		notifyObservers(modelRowEvent);	
	}
}
