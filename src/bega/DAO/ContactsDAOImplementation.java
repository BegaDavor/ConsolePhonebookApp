package bega.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bega.DTO.Contacts;
import bega.DTO.Users;

public class ContactsDAOImplementation implements ContactsDAOInterface{

	Connection connection = ConnectionManager.getInstance().getConnection();
	
	@Override
	public ArrayList<Contacts> getConctacts(Users user) throws SQLException {
		// TODO Auto-generated method stub
		
		ArrayList<Contacts> contacts = new ArrayList<>();
		
		String query = "SELECT * FROM contacts WHERE userID = ?";
		
		ResultSet rs = null;
		
		try(PreparedStatement statement = connection.prepareStatement(query);){
			statement.setInt(1, user.getUserID());
			
			rs = statement.executeQuery();
			
			while(rs.next()) {
				contacts.add(new Contacts(rs.getString("firstname"), rs.getString("lastname"), rs.getString("city"), rs.getString("email"), rs.getString("phonenumber"), rs.getInt("userID")));
			}
		}
		
		return contacts;
	}

	@Override
	public ArrayList<Contacts> getContactByName(String firstname) throws SQLException {
		// TODO Auto-generated method stub
		
		ArrayList<Contacts> contacts = new ArrayList<>();
		
		String query = "SELECT * FROM contacts WHERE firstname = ?";
		
		ResultSet rs = null;
		
		try(PreparedStatement statement = connection.prepareStatement(query);){
			statement.setString(1, firstname);
			
			rs = statement.executeQuery();
			
			while (rs.next()) {
				
				contacts.add(new Contacts(rs.getString("firstname"), rs.getString("lastname"), rs.getString("city"), rs.getString("email"), rs.getString("phonenumber"), rs.getInt("userID")));
				
			}
		}
		
		return contacts;
	}

	@Override
	public ArrayList<Contacts> getContactByLastname(String lastname) throws SQLException {
ArrayList<Contacts> contacts = new ArrayList<>();
		
		String query = "SELECT * FROM contacts WHERE lastname = ?";
		
		ResultSet rs = null;
		
		try(PreparedStatement statement = connection.prepareStatement(query);){
			statement.setString(1, lastname);
			
			rs = statement.executeQuery();
			
			while (rs.next()) {
				
				contacts.add(new Contacts(rs.getString("firstname"), rs.getString("lastname"), rs.getString("city"), rs.getString("email"), rs.getString("phonenumber"), rs.getInt("userID")));
				
			}
		}
		
		return contacts;
	}

	@Override
	public void addContact(Contacts contact, Users user) throws SQLException {
		// TODO Auto-generated method stub
		
		String query = "INSERT INTO contacts(firstname, last name, city, email, phonenumber,  userID) VALUES (?, ?, ?, ?, ?, ?)";
		
		try(PreparedStatement statement = connection.prepareStatement(query);){
			statement.setString(1, contact.getFirstname());
			statement.setString(2, contact.getLastname());
			statement.setString(3, contact.getCity());
			statement.setString(4, contact.getEmail());
			statement.setString(5, contact.getPhonenumber());
			statement.setInt(6, user.getUserID());
			
			statement.executeUpdate();
			
			System.out.println("Conctact is added!");
		}
	}

	@Override
	public void updateContact(Contacts contact, Users user) throws SQLException {
		// TODO Auto-generated method stub
		
		if (contact != null) {
			String query = "UPDATE contacts SET firstname = ?, lastname = ?, city = ?, email = ?, phonenumber = ? WHERE contactID = ? AND userID = ?";
			
			try (PreparedStatement statement = connection.prepareStatement(query);){
				statement.setString(1, contact.getFirstname());
				statement.setString(2, contact.getLastname());
				statement.setString(3, contact.getCity());
				statement.setString(4, contact.getEmail());
				statement.setString(5, contact.getPhonenumber());
				statement.setInt(6, contact.getContactID());
				statement.setInt(7, user.getUserID());
				
				statement.executeUpdate();
				
				System.out.println("Cotact is up to date!");
			}
		}
		
	}

	@Override
	public void deleteContact(Contacts contact, Users user) throws SQLException {
		// TODO Auto-generated method stub
		
		if (contact != null) {
			
			String query = "DELETE FROM contacts WHERE contactID = ? AND userID = ?";
			
			try(PreparedStatement statement = connection.prepareStatement(query);){
				statement.setInt(1, contact.getContactID());
				statement.setInt(2, user.getUserID());
				
				statement.executeUpdate();
				
				System.out.println("Contact is deleted!");
			}
		}
		
	}
	
	

}
