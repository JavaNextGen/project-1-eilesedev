package com.revature.services;

import com.revature.exceptions.NewUserHasNonZeroIdException;
import com.revature.exceptions.RegistrationUnsuccessfulException;
import com.revature.exceptions.UserLoginFailedException;
import com.revature.exceptions.UsernameNotUniqueException;
import com.revature.models.AbstractUser;
import com.revature.models.User;
import com.revature.repositories.UserDAO;

//import static org.junit.Assert.assertEquals;
//import static org.mockito.Matchers.anyString;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;

import java.util.Optional;

import javax.security.auth.login.LoginException;

/**
 * The AuthService should handle login and registration for the ERS application.
 *
 * {@code login} and {@code register} are the minimum methods required; however,
 * additional methods can be added.
 *
 * Examples:
 * <ul>
 * <li>Retrieve Currently Logged-in User</li>
 * <li>Change Password</li>
 * <li>Logout</li>
 * </ul>
 */
public class AuthService {

	User user = new User();
	UserService us = new UserService();

	/**
	 * <ul>
	 * <li>Needs to check for existing users with username/email provided.</li>
	 * <li>Must throw exception if user does not exist.</li>
	 * <li>Must compare password provided and stored password for that user.</li>
	 * <li>Should throw exception if the passwords do not match.</li> *****
	 * <li>Must return user object if the user logs in successfully.</li>
	 * ----------------------------------------------------------------------
	 * <li>Can add additional functionality for username or email</li>
	 * </ul>
	 * 
	 * @throws UserLoginFailedException
	 */
	public User login(String username, String password) throws UserLoginFailedException{

		UserDAO uDAO = new UserDAO();
		
		UserService newServ = new UserService(); 

		if (newServ.getByUsername(username).isPresent()) {
			
			User byUnm = newServ.getByUsername(username).get(); //Generic Employee
			
			String userName = byUnm.getUsername();
			String passWord = byUnm.getPassword();
			
			if (passWord.equals(password)) {
				return uDAO.logInUser(userName, passWord);

			} else {
				System.out.println("Incorrect Password!");
				throw new UserLoginFailedException(); 
			}
			
		} 

		return null;
	}

	/**
	 * <ul>
	 * <li>Should ensure that the username/email provided is unique.</li>
	 * <li>Must throw exception if the username/email is not unique.</li>
	 * <li>Should persist the user object upon successful registration.</li> *******
	 * <li>Must throw exception if registration is unsuccessful.</li>
	 * <li>Must return user object if the user registers successfully.</li>
	 * <li>Must throw exception if provided user has a non-zero ID</li>
	 * </ul>
	 *
	 * Note: userToBeRegistered will have an id=0, additional fields may be null.
	 * After registration, the id will be a positive integer.
	 */
	public User register(User userToBeRegistered) throws NewUserHasNonZeroIdException, UsernameNotUniqueException, RegistrationUnsuccessfulException {

//		NEED TO CREATE A CHECK IF REGISTRATION UNSUCCESSFUL
		
		UserDAO uDAO = new UserDAO();

		if (userToBeRegistered != null) {

			User registeredUser = userToBeRegistered;

			String registeredUnm = registeredUser.getUsername();

			int registeredId = registeredUser.getId();

			if (uDAO.getByUsername(registeredUnm).isPresent()) {

				System.out.println("Username matches a username saved in the database. \" + \"Please sign in");

				throw new UsernameNotUniqueException();

			} else if (registeredId != 0) {

				System.out.println("ID should be 0!");

				throw new NewUserHasNonZeroIdException();

			}
			
			return registeredUser; 

		}

		return uDAO.create(userToBeRegistered);

	}

	/**
	 * This is an example method signature for retrieving the currently logged-in
	 * user. It leverages the Optional type which is a useful interface to handle
	 * the possibility of a user being unavailable.
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
