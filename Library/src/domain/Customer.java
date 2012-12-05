package domain;

import java.util.Observable;

public class Customer extends Observable{
	
	private String name, surname, street, city;
	private int zip;

	public Customer(String name, String surname) {
		this.name = name;
		this.surname = surname;
	}
	public Customer(String surname, String name, String street, String city) {
		this.name = name;
		this.surname = surname;
	}
	
	
	public void setAdress(String street, int zip, String city) {
		this.street = street;
		this.zip = zip;
		this.city = city;
		fireChange();
	}
	public void fireChange() {
		setChanged();
		notifyObservers();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		fireChange();
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
		fireChange();
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
		fireChange();
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
		fireChange();
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
		fireChange();
	}
	
	@Override
	public String toString() {
		return name + " " + surname + " , " + street + " , " + zip + " " + city;
	}

}
