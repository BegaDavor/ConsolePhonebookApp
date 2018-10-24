package bega.DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import bega.DTO.Contacts;
import bega.DTO.Users;

public interface ContactsDAOInterface {
	
	public ArrayList<Contacts> getConctacts(Users user) throws SQLException;
	public ArrayList<Contacts> getContactByName(String firstname) throws SQLException;
	public ArrayList<Contacts> getContactByLastname(String lastname) throws SQLException;
	public void addContact(Contacts contact, Users user) throws SQLException;
	public void updateContact(Contacts contact, Users user) throws SQLException;
	public void deleteContact(Contacts contact, Users user) throws SQLException;
	
	

}
