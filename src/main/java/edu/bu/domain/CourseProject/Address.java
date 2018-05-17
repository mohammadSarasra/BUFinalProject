package edu.bu.domain.CourseProject;


public class Address {
	private String streetName;
	private String houseNumber;
	private String city;
	
	public Address() {
		
	}

	public Address(String streetName, String houseNumber, String city) {
		this.streetName = streetName;
		this.houseNumber = houseNumber;
		this.city = city;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public String toString() {
		return "\nStreet Name: " + this.streetName + "\nHouse Number: " + this.houseNumber
				+ "\nCity: " + this.city;
	}
}
