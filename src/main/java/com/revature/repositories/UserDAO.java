package com.revature.repositories;

import com.revature.models.User;
import com.revature.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

public class UserDAO {

    /**
     * Should retrieve a User from the DB with the corresponding username or an empty optional if there is no match.
     */
    public Optional<User> getByUsername(String username) {
        return Optional.empty();
    }

    /**
     * <ul>
     *     <li>Should Insert a new User record into the DB with the provided information.</li>
     *     <li>Should throw an exception if the creation is unsuccessful.</li>
     *     <li>Should return a User object with an updated ID.</li>
     * </ul>
     *
     * Note: The userToBeRegistered will have an id=0, and username and password will not be null.
     * Additional fields may be null.
     */
    public User create(User userToBeRegistered) {
    	
    	try(Connection conn = ConnectionFactory.getConnection()){
    		//Here -- create SQL String statement to insert new User parameters
    		String sqlCreate = "INSERT INTO ers_users (ers_username, ers_password,"
    				+ "user_first_name, user_last_name, user_email, user_role_id)"
    				+ "VALUES (?, ?, ?, ?, ?, ?)";
    		
    		//Use for SQL commands
    		PreparedStatement p = conn.prepareStatement(sqlCreate); 
    		
    		//User sends in object -> use PreparedStatement object to insert values into CREATE statement
    		//values will come from User Object 
    		p.setString(1, userToBeRegistered.getUsername());
    		p.setString(2, userToBeRegistered.getPassword()); 
    		p.setString(3, userToBeRegistered.getF_Name());
    		p.setString(4, userToBeRegistered.getL_Name());
    		p.setString(5, userToBeRegistered.getEmail());
    		p.setInt(6, userToBeRegistered.getUserRole().ordinal()+1); //ordinal returns value of enum (zero-indexed) incremented to match enum vals in database
    		
    		//Command executes SQL CREATE statement
    		p.executeUpdate(); //use for inserts, updates, and deletes
    		
    		//TEST - send to console if successful
    		System.out.println("Registration successful " + userToBeRegistered.getF_Name()); 
    		System.out.println("Welcome to your new ERS system!");
    		
    	} catch(SQLException e){
    		System.out.println("Registration unsuccessful!");
    		e.printStackTrace();
    	}
    	
        return userToBeRegistered;
    }
}
