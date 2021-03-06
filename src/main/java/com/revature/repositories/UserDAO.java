package com.revature.repositories;

import com.revature.exceptions.RegistrationUnsuccessfulException;
import com.revature.models.AbstractUser;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAO {

//	User newU = new User(); 
	Role setRole;
	User u = new User(); 

	/**
	 * Should retrieve a User from the DB with the corresponding username or an
	 * empty optional if there is no match.
	 * 
	 * @throws SQLException
	 */
	public Optional<User> getByUsername(String username) { 
		
		try (Connection conn = ConnectionFactory.getConnection()) {
//			User opt = new User(); 
			/**
			 * Result set object points to row in database that record exists in set to null
			 * for now
			 **/
			ResultSet rs = null;
			// retrieve user from the database with corresponding username
			String sqlSelect = "SELECT * FROM ers_users WHERE ers_username= ?";
			// Put the sql query into our statement object
			PreparedStatement ps = conn.prepareStatement(sqlSelect);
			// Here -- create SQL String statement to retrieve user record that matches
			// passed in username

			ps.setString(1, username);
			rs = ps.executeQuery(); // Used to retrieve values from database
			
//			int enumVal = rs.getInt("user_role_id"); 
			int roleId; 
			
			
			
			while(rs.next()) {
//				Role r = null; 
//				if(rs.getInt("user_role_id") == 1) {
//					r = Role.EMPLOYEE;
//				} else {
//					r = Role.FINANCE_MANAGER;
//				}
//				(int id, String username, String password, String fName, String lName, String email, int roleId)
				User opt = new User(
						rs.getInt("ers_users_id"), 
						rs.getString("ers_username"),
						rs.getString("ers_password"),
						rs.getString("user_first_name"), 
						rs.getString("user_last_name"),
						rs.getString("user_email"), 
						rs.getInt("user_role_id")
//						Role.values()[) - 1]//Working??		
						); 
//						Role.values()[getInt("user_role_id"]
//						Role.values(rs.getInt("user_role_id"))
				// I take an ID -- int value from this column and have to turn it into a Role
				// enum
//				Role.convertInttoRole(rs.getInt("user_role_id"))
//				
//				setRole.valueOf(rs.getInt("user_role_id"))
						
				System.out.println("Username for " + opt.toString() + " exists!");
				return Optional.ofNullable(opt);
			}
			
			 
			

		} catch (SQLException e) {
			System.out.println("Something has gone wrong!");
			e.printStackTrace();
		}

		return Optional.ofNullable(null);

	}

	/**
	 * <ul>
	 * <li>Should Insert a new User record into the DB with the provided
	 * information.</li>
	 * <li>Should throw an exception if the creation is unsuccessful.</li>
	 * <li>Should return a User object with an updated ID.</li>
	 * </ul>
	 *
	 * Note: The userToBeRegistered will have an id=0, and username and password
	 * will not be null. Additional fields may be null.
	 */
	public User create(User userToBeRegistered) {

		try (Connection conn = ConnectionFactory.getConnection()) {
			// Here -- create SQL String statement to insert new User parameters
			String sqlCreate = "INSERT INTO ers_users (ers_username, ers_password,"
					+ "user_first_name, user_last_name, user_email, user_role_id)" + "VALUES (?, ?, ?, ?, ?, ?)";

			// Use for SQL commands that use parameters
			PreparedStatement p = conn.prepareStatement(sqlCreate);

			// User sends in object -> use PreparedStatement object to insert values into
			// CREATE statement
			// values will come from User Object
			p.setString(1, userToBeRegistered.getUsername());
			p.setString(2, userToBeRegistered.getPassword());
			p.setString(3, userToBeRegistered.getF_Name());
			p.setString(4, userToBeRegistered.getL_Name());
			p.setString(5, userToBeRegistered.getEmail());
			p.setInt(6, userToBeRegistered.getUserRole().ordinal() + 1); // ordinal returns value of enum (zero-indexed)
																			// incremented to match enum vals in
																			// database

			// Command executes SQL CREATE statement
			p.executeUpdate(); // use for inserts, updates, and deletes

			// TEST - send to console if successful
			System.out.println("Registration successful " + userToBeRegistered.getF_Name());
			System.out.println("Welcome to your new ERS system!");

			// NonZeroException isn't running why???

//    		if(userToBeRegistered.getId() != 0) {
//    			throw new NewUserHasNonZeroIdException(); 

			// Not sure how to throw exception for if user is not registered successfully

//    		}

		} catch (RegistrationUnsuccessfulException | SQLException e) {
			System.out.println("Registration unsuccessful!");
			e.printStackTrace();
		}

		return userToBeRegistered;
	}
	
	
	// This will handle the user login with the database
	
	
	public User logInUser(String username, String password) {

		try (Connection conn = ConnectionFactory.getConnection()) {

			/**
			 * Result set object points to row in database that record exists in set to null
			 * for now
			 **/
			ResultSet rs = null;
			// retrieve user from the database with corresponding username
			String sqlSelect = "SELECT * FROM ers_users WHERE ers_username= ? AND ers_password=?";
			// Put the sql query into our statement object
			PreparedStatement ps = conn.prepareStatement(sqlSelect);
			// Here -- create SQL String statement to retrieve user record that matches
			// passed in username

			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery(); // Used to retrieve values from database
			
			System.out.println("You've entered your password correctly!");

			// This is what's missing. Where is the object that I create from the matching
			// record in the database
			// I may not need an extra method in this class if I do this correctly
			while(rs.next()) {
//				Role r = null; 
//				if(rs.getInt("user_role_id") == 1) {
//					r = Role.EMPLOYEE;
//				} else {
//					r = Role.FINANCE_MANAGER;
//				}
				User u = new User(
						rs.getInt("ers_users_id"), 
						rs.getString("ers_username"),
						rs.getString("ers_password"),
						rs.getInt("user_role_id")
						);
				System.out.println(u.toString()); 
				return u; 
				
//				if(newU.equals(u)) {//Tests need to change here
//					// Test if successful
//					System.out.println("Password matches!");
//					
//				} else {
//					throw new RegistrationUnsuccessfulException(); 
//				}
				

			}
		} catch (SQLException e) {
			System.out.println("Something has gone wrong!");
			e.printStackTrace();
		}
		
		return null;

	}
	
//get UserById
public User getUserById(int id) {
		
		try(Connection conn = ConnectionFactory.getConnection()) {
			
			ResultSet rs = null;
			
			String sqlSelect = "SELECT * FROM ers_users WHERE ers_users_id= ?";
			
			PreparedStatement ps = conn.prepareStatement(sqlSelect); 
			

			ps.setInt(1, id); 
			
			rs = ps.executeQuery();

			while(rs.next()) {
				User user = new User(
						rs.getInt("ers_users_id"), 
						rs.getString("ers_username"),
						rs.getString("ers_password"),
						rs.getString("user_first_name"),
						rs.getString("user_last_name"),
						rs.getString("user_email"),
						// I take an ID -- int value from this column and have to turn it into a Role
						// enum
						rs.getInt("user_role_id")
						);
			
				return user;
			}
			
			
		} catch (SQLException e) {
			System.out.println("Something went wrong with your database!"); 
			e.printStackTrace();
		}
		return null;
	}
}
