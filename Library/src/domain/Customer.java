package domain;

import java.util.Observable;

import controll.ModelRowEvent;

public class Customer extends Observable{
	
	private String name, surname, street, city;
	private int zip;

	public Customer(String name, String surname) {
		this.name = name;
		this.surname = surname;
	}
		
	public void setAdress(String street, int zip, String city) {
		this.street = street;
		this.zip = zip;
		this.city = city;
		fireChange(ModelRowEvent.updated);
	}
	public void fireChange(ModelRowEvent e) {
		setChanged();
		notifyObservers(e);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		fireChange(ModelRowEvent.updated);
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
		fireChange(ModelRowEvent.updated);
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
		fireChange(ModelRowEvent.updated);
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
		fireChange(ModelRowEvent.updated);
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
		fireChange(ModelRowEvent.updated);
	}
	
	@Override
	public String toString() {
		return name + " " + surname + " , " + street + " , " + zip + " " + city;
	}

}
