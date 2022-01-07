package com.revature.services;

import com.revature.exceptions.UserLoginFailedException;
import com.revature.models.AbstractUser;
import com.revature.models.User;
import com.revature.repositories.UserDAO;

import java.util.Optional;

import javax.security.auth.login.LoginException;

/**
 * The AuthService should handle login and registration for the ERS application.
 *
 * {@code login} and {@code register} are the minimum methods required; however, additional methods can be added.
 *
 * Examples:
 * <ul>
 *     <li>Retrieve Currently Logged-in User</li>
 *     <li>Change Password</li>
 *     <li>Logout</li>
 * </ul>
 */
public class AuthService {
	UserDAO uDAO = new UserDAO(); 
	User user = new User(); 
	UserService us = new UserService();

    /**
     * <ul>
     *     <li>Needs to check for existing users with username/email provided.</li>
     *     <li>Must throw exception if user does not exist.</li>
     *     <li>Must compare password provided and stored password for that user.</li>
     *     <li>Should throw exception if the passwords do not match.</li>
     *     <li>Must return user object if the user logs in successfully.</li>
     *     ----------------------------------------------------------------------
     *     <li>Can add additional functionality for username or email</li>
     * </ul>
     * @throws UserLoginFailedException 
     */
    public User login(String username, String password) {
    	
    	if(uDAO.getByUsername(username).isPresent()) {
    		String userName = username;
    		String passWord = uDAO.getByUsername(userName).get().getPassword(); 
    		
    		if(passWord.equals(password)) {
    			return uDAO.logInUser(userName, passWord);
    			
    		} else {
    			System.out.println("Incorrect Password! Try again");
    		}
    	}
    		 

    	
    	//compare with UserService getByUsername method if they match pass the username into the database and return user if one exists
//    	if(uDAO.getByUsername(username).isPresent()) {
//    	
//    		
////        	Create flag for user login
////        	boolean login = false; 
////    		boolean login = ;
//    		if(password.equals(uDAO.getByUsername(username).get().getPassword())) {
//    			System.out.println("Log in successful!");
//    			//Create a user object that I return
//    			
//    			
//    		} else {
//    			System.out.println("Log in failed!");
////    			throw new UserLoginFailedException(); 
//    		}
//    	};
    	//if the passwords don't match throw exception
    	
    	//return a user object if user logs in successfully
    	
    	//return user object if user logs in successfully
		return null;
    }

    /**
     * <ul>
     *     <li>Should ensure that the username/email provided is unique.</li>
     *     <li>Must throw exception if the username/email is not unique.</li>
     *     <li>Should persist the user object upon successful registration.</li>
     *     <li>Must throw exception if registration is unsuccessful.</li>
     *     <li>Must return user object if the user registers successfully.</li>
     *     <li>Must throw exception if provided user has a non-zero ID</li>
     * </ul>
     *
     * Note: userToBeRegistered will have an id=0, additional fields may be null.
     * After registration, the id will be a positive integer.
     */
    public User register(User userToBeRegistered){
    	
    	
    	
    	return uDAO.create(userToBeRegistered); 
    }

    /**
     * This is an example method signature for retrieving the currently logged-in user.
     * It leverages the Optional type which is a useful interface to handle the
     * possibility of a user being unavailable.
     */
    public Optional<User> exampleRetrieveCurrentUser() {
		
    	return Optional.empty();
    	
//        return uDAO.getByUsername(username);
    }
    
//    public User returnAll() {
//    	uDAO.getAllUsers();
//		return user;
//    }
}
