package bega.DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import bega.DTO.Contacts;
import bega.DTO.Users;

public class ContactsDAOImplementation implements ContactsDAOInterface{

	@Override
	public ArrayList<Contacts> getConctacts(Users user) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contacts getContactByName(String firstname) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contacts getContactByLastname(String lastname) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addContact(Contacts contact) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateContact(Contacts contact) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteContact(Contacts contact) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
	

}
