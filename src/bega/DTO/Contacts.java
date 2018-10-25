package bega.DTO;

public class Contacts {
	
	//atributi
	
	private int contactID;
	private String firstname;
	private String lastname;
	private String city;
	private String email;
	private String phonenumber;
	private int userID;
	
	//konstruktor
	
	public Contacts(String firstname, String lastname, String city, String email, String phonenumber, int userID) {
		
		this.firstname = firstname;
		this.lastname = lastname;
		this.city = city;
		this.email = email;
		this.phonenumber = phonenumber;
		this.userID = userID;
		
	}
	
public Contacts(int contactID, String firstname, String lastname, String city, String email, String phonenumber, int userID) {
		
		this.contactID = contactID;
		this.firstname = firstname;
		this.lastname = lastname;
		this.city = city;
		this.email = email;
		this.phonenumber = phonenumber;
		this.userID = userID;
		
	}
	
	//getters and setters

	public int getContactID() {
		return contactID;
	}

	public void setContactID(int contactID) {
		this.contactID = contactID;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	
	// toString metoda
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "ID: " + getContactID() + 
				" | First name: " + getFirstname() +
				" | Last name: " + getLastname() + 
				" | City: " + getCity() + 
				" | E-mail: " + getEmail() + 
				" | Phone number: " + getPhonenumber();
	}
	

}
