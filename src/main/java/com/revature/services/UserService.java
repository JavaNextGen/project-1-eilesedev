package com.revature.services;

import java.util.Optional;
import java.util.function.Consumer;

import com.revature.exceptions.UsernameNotUniqueException;
import com.revature.models.AbstractUser;
import com.revature.models.User;
import com.revature.repositories.UserDAO;

/**
 * The UserService should handle the processing and retrieval of Users for the
 * ERS application.
 *
 * {@code getByUsername} is the only method required; however, additional
 * methods can be added.
 *
 * Examples:
 * <ul>
 * <li>Create User</li>
 * <li>Update User Information</li>
 * <li>Get Users by ID</li>
 * <li>Get Users by Email</li>
 * <li>Get All Users</li>
 * </ul>
 */
public class UserService {
	UserDAO uDAO = new UserDAO();

	/**
	 * Should retrieve a User with the corresponding username or an empty optional
	 * if there is no match.
	 */
	public Optional<User> getByUsername(String username) {

//		If username is present in the database get and return the username
		if (uDAO.getByUsername(username).isPresent()) {

			return Optional.ofNullable(uDAO.getByUsername(username).get());

		} else
			return Optional.ofNullable(null);

		// =========================================ALREADY WORKS TESTING OTHER
		// METHODS==============================================

//		
//		if(uDAO.getByUsername(username).isPresent()) { //This method only pulls the user name and password from the DB
//			System.out.println("User exists in UserService!");
//			User fromDAO = uDAO.getByUsername(username).get();
//			
//			System.out.println("Hey " + fromDAO.getUsername()); // this doesn't access every column in the DAO
//			System.out.println("Your password is " + fromDAO.getPassword());
//			System.out.println("You are a(n) " + fromDAO.getUserRole());
////			
//			return Optional.of(fromDAO); 
//			
//		} else {
//			uDAO.getByUsername(username).orElseThrow(UsernameNotUniqueException :: new); //need to change this exception
//					
//		}
////		return Optional.of(user);//using username return the entire object
//		return Optional.empty(); 
//		
//=========================================ALREADY WORKS TESTING OTHER METHODS==============================================

//		User fromDAO = uDAO.getByUsername(username).get(); //will this create an object
//		System.out.println("Hello from User Service");
//		fromDAO.toString(); 
//////		if(fromDAO != Optional.empty()) { //This method only pulls the user name and password from the DB
////			System.out.println("User exists in UserService!");
////			User fromDAO = uDAO.getByUsername(username).get();
////			
////			System.out.println("Hey " + fromDAO.getUsername()); // this doesn't access every column in the DAO
////			System.out.println("Your password is " + fromDAO.getPassword());
////			System.out.println("You are a(n) " + fromDAO.getUserRole());
//////			
////			return Optional.of(fromDAO); 
////			
////		} else {
////			uDAO.getByUsername(username).orElseThrow(UsernameNotUniqueException :: new); //need to change this exception
//					
////		}
////		return Optional.of(user);//using username return the entire object
//		return Optional.empty(); 

	}
	
	public User getUserById(int userId) {
		
		return uDAO.getUserById(userId);
	}
	
}
