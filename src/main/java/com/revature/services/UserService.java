package com.revature.services;

import java.util.Optional;
import java.util.function.Consumer;

import com.revature.exceptions.UsernameNotUniqueException;
import com.revature.models.AbstractUser;
import com.revature.models.User;
import com.revature.repositories.UserDAO;

/**
 * The UserService should handle the processing and retrieval of Users for the ERS application.
 *
 * {@code getByUsername} is the only method required;
 * however, additional methods can be added.
 *
 * Examples:
 * <ul>
 *     <li>Create User</li>
 *     <li>Update User Information</li>
 *     <li>Get Users by ID</li>
 *     <li>Get Users by Email</li>
 *     <li>Get All Users</li>
 * </ul>
 */
public class UserService { 
	UserDAO uDAO = new UserDAO();
	
	
	/**
	 *     Should retrieve a User with the corresponding username or an empty optional if there is no match.
     */
	public Optional<User> getByUsername(String username) {
		
	//=========================================ALREADY WORKS TESTING OTHER METHODS==============================================
		
		
		if(uDAO.getByUsername(username).isPresent()) {
			System.out.println("User exists in UserService!");
			User fromDAO = uDAO.getByUsername(username).get();
			
//			System.out.println("Hey " + fromDAO.getF_Name()); // this doesn't access every column in the DAO
//			
			return Optional.of(fromDAO); 
			
		} else {
			uDAO.getByUsername(username).orElseThrow(UsernameNotUniqueException :: new); 
					
		}
//		return Optional.of(user);//using username return the entire object
		return Optional.empty(); 
		
//=========================================ALREADY WORKS TESTING OTHER METHODS==============================================
		
		
//		Optional<User> opt = uDAO.getByUsername(username); 
//		
//		
//		User user = new User(); 
//		
//		//Create Consumer<User>
//		Consumer<User> setU = a -> user.setUsername(username);
//		
////		String userNom; 
//		uDAO.getByUsername(username).ifPresentOrElse(setU, UsernameNotUniqueException::new);
//		
//	uDAO.getByUsername(username).ifPresentOrElse(userNom -> uDAO.getByUsername(username).get(), UsernameNotUniqueException::new);
	
//	user.setUsername(userNom);	
		
		
//		return opt;
		

		
	}
}
