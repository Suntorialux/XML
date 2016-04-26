package by.epam.model;

public class Passenger {
	private int id;
	private String firstName;
	private String lastName;
	private String street;
	private String town;
	private int homeNumber;
	private String passportNumber;
	private String originTown;
	private String originDate;
	private String originTime;
	private String destinationTown;
	private String destinationDate;
	private String destinationTime;
	
	
	public Passenger() {
		super();
		this.id = 0;
		this.firstName = null;
		this.lastName = null;
		this.street = null;
		this.town = null;
		this.homeNumber = 0;
		this.passportNumber = null;
		this.originTown = null;
		this.originDate = null;
		this.originTime = null;
		this.destinationTown = null;
		this.destinationDate = null;
		this.destinationTime = null;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public int getHomeNumber() {
		return homeNumber;
	}
	public void setHomeNumber(int homeNumber) {
		this.homeNumber = homeNumber;
	}
	public String getPassportNumber() {
		return passportNumber;
	}
	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}
	public String getOriginDate() {
		return originDate;
	}
	public void setOriginDate(String originDate) {
		this.originDate = originDate;
	}
	public String getOriginTime() {
		return originTime;
	}
	public void setOriginTime(String originTime) {
		this.originTime = originTime;
	}
	public String getDestinationDate() {
		return destinationDate;
	}
	public void setDestinationDate(String destinationDate) {
		this.destinationDate = destinationDate;
	}
	public String getDestinationTime() {
		return destinationTime;
	}
	public void setDestinationTime(String destinationTime) {
		this.destinationTime = destinationTime;
	}
	public String getOriginTown() {
		return originTown;
	}
	public void setOriginTown(String originTown) {
		this.originTown = originTown;
	}
	public String getDestinationTown() {
		return destinationTown;
	}
	public void setDestinationTown(String destinationTown) {
		this.destinationTown = destinationTown;
	}
	@Override
	public String toString() {
		return "Pasanger [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", street=" + street
				+ ", town=" + town + ", homeNumber=" + homeNumber + ", passportNumber=" + passportNumber
				+ ", originTown=" + originTown + ", originDate=" + originDate + ", originTime=" + originTime
				+ ", destinationTown=" + destinationTown + ", destinationDate=" + destinationDate + ", destinationTime="
				+ destinationTime + "]";
	}
	
	
	
	
}
