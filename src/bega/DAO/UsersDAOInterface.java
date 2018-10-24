package bega.DAO;

import java.sql.SQLException;

import bega.DTO.Users;

public interface UsersDAOInterface {
	
	public Users getUserByUsername(String username) throws SQLException;
	public void addUser(Users user) throws SQLException;
	

}
