package domain;

import java.util.Observable;

import controller.LibraryEvent;

public class Customer extends Observable{
	
	private String name, surname, street, city;
	private int zip;
	private boolean invalid;

	public Customer(String name, String surname) {
		this.name = name;
		this.surname = surname;
	}
		
	public void setAdress(String street, int zip, String city) {
		this.street = street;
		this.zip = zip;
		this.city = city;
		fireChange(LibraryEvent.updated);
	}
	public void fireChange(LibraryEvent e) {
		setChanged();
		notifyObservers(e);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		fireChange(LibraryEvent.updated);
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
		fireChange(LibraryEvent.updated);
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
		fireChange(LibraryEvent.updated);
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
		fireChange(LibraryEvent.updated);
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
		fireChange(LibraryEvent.updated);
	}
	
	@Override
	public String toString() {
		return name + " " + surname + " , " + street + " , " + zip + " " + city;
	}

	public boolean isInvalid() {
		// TODO Auto-generated method stub
		return invalid;
	}

	public void setInvalid() {
		invalid = true;
		
	}

}
