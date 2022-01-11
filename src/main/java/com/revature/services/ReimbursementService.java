package com.revature.services;

import com.revature.models.Reimbursement;
import com.revature.models.Status;
import com.revature.models.User;
import com.revature.repositories.ReimbursementDAO;
import com.revature.repositories.UserDAO;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * The ReimbursementService should handle the submission, processing, and
 * retrieval of Reimbursements for the ERS application.
 *
 * {@code process} and {@code getReimbursementsByStatus} are the minimum methods
 * required; however, additional methods can be added.
 *
 * Examples:
 * <ul>
 * <li>Create Reimbursement</li>
 * <li>Update Reimbursement</li>
 * <li>Get Reimbursements by ID</li>
 * <li>Get Reimbursements by Author</li>
 * <li>Get Reimbursements by Resolver</li>
 * <li>Get All Reimbursements</li>
 * </ul>
 */
public class ReimbursementService {

	UserDAO uDAO = new UserDAO();
	ReimbursementDAO rDAO = new ReimbursementDAO();
	AuthService as = new AuthService();
	UserService us = new UserService(); 

	public Reimbursement create(Reimbursement newReimbursement) {
		
//		if(as.login(us.getByUsername(username).get(), password))
		
//		User loggedInUser = auth.login(username, password);
		
		return rDAO.create(newReimbursement);




//		if(auth.login(usernombre.get().toString(), password) != null) {
////		int logInId = auth.login(username, password).getId(); 
////		String logInUsername = auth.login(username, password).getUsername(); 
////		String logInPswd = auth.login(logInUsername, password).getPassword();
////		Role logInRole = auth.login(username, password).getRole(); 
////		User loggedInUser = new User(logInId, logInUsername, logInPswd,logInRole);
////		
//////		User absManager = new User(0, Role.FINANCE_MANAGER); 
////		
////		System.out.println("You are a(n) " + loggedInUser.getRole());
////		
//		if(loggedInUser.getUserRole().equals(Role.EMPLOYEE)) {
//			System.out.println("Enter your reimbursement request below: ");
//
//			double amount = s.nextDouble(); 
//			s.nextLine();
//			
////			 public Reimbursement(Status status, User author, double amount)
//			
//			//Create new reimbursement object
//			Reimbursement newReimb = new Reimbursement(Status.PENDING, loggedInUser, today, amount);  
//			reimb.create(newReimb); 
//			
////			

		// Control flow--no finance managers can create their own reimbursements only
		// employees;

//		return null;

	}

	/**
	 * <ul>
	 * <li>Should ensure that the user is logged in as a Finance Manager</li>
	 * <li>Must throw exception if user is not logged in as a Finance Manager</li>
	 * <li>Should ensure that the reimbursement request exists</li>
	 * <li>Must throw exception if the reimbursement request is not found</li>
	 * <li>Should persist the updated reimbursement status with resolver
	 * information</li>
	 * <li>Must throw exception if persistence is unsuccessful</li>
	 * </ul>
	 *
	 * Note: unprocessedReimbursement will have a status of PENDING, a non-zero ID
	 * and amount, and a non-null Author. The Resolver should be null. Additional
	 * fields may be null. After processing, the reimbursement will have its status
	 * changed to either APPROVED or DENIED.
	 */

	public Reimbursement process(Reimbursement unprocessedReimbursement, Status finalStatus, User resolver) {
		return null;
	}

	/**
	 * Should retrieve all reimbursements with the correct status.
	 */
//    when(reimbursementDAO.getByStatus(any())).thenReturn(GENERIC_ALL_PENDING_REIMBURSEMENTS);
//	
//	assertEquals(GENERIC_ALL_PENDING_REIMBURSEMENTS, reimbursementService.getReimbursementsByStatus(Status.PENDING));
//	
//	verify(reimbursementDAO).getByStatus(Status.PENDING);
	public List<Reimbursement> getReimbursementsByStatus(Status status) {

		return rDAO.getByStatus(status);
	}
}
