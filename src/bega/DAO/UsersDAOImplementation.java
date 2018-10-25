package bega.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bega.DTO.Users;

public class UsersDAOImplementation implements UsersDAOInterface{
	
	//Konekcija na bazu
	Connection connection = ConnectionManager.getInstance().getConnection();
	
	@Override
	public Users getUserByUsername(String username) throws SQLException {
		// TODO Auto-generated method stub
		
		Users user = null;
		
		String query = "SELECT * FROM users WHERE username = ?";
		
		ResultSet rs = null;
		
		try(PreparedStatement statement = connection.prepareStatement(query);){
			statement.setString(1, username);
			
			rs = statement.executeQuery();
			
			if(rs.next()) {
				user = new Users(rs.getInt("userID"), rs.getString("username"), rs.getString("password"));
				rs.close();
			}
		}
		
		return user;
	}

	@Override
	public void addUser(Users user) throws SQLException {
		// TODO Auto-generated method stub
		
		String query = "INSERT INTO users(username, password) VALUES (?, ?)";
		
		try (PreparedStatement statement = connection.prepareStatement(query);){
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			
			statement.executeUpdate();
			
			System.out.println("User is added!");
		}
		
	}

}
