package bega.DTO;

public class Users {
	
	//atributi
	
	private int userID;
	private String username;
	private String password;
	
	//konstruktor
	
	public Users(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	//getters & setters
	
	public int getUserID() {
		return userID;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

}
