package com.revature.services;

import com.revature.exceptions.UserLoginFailedException;
import com.revature.models.Reimbursement;
import com.revature.models.Role;
import com.revature.models.Status;
import com.revature.models.User;
import com.revature.repositories.ReimbursementDAO;
import com.revature.repositories.UserDAO;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
		
//		User author = us.getUserById(Id);
//		
//		Reimbursement wAuthor = newReimbursement; 
//		
//		wAuthor.setAuthor(author);

		return rDAO.create(newReimbursement);

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

	public Reimbursement process(Reimbursement unprocessedReimbursement, Status finalStatus, User resolver){
				//resolver should be set from the service layer
				//reimb status id should be set from service layer
				//reimb id is just the ID of the unprocessed reimburseemnt
		
		try {
			if(as.login(resolver.getUsername(), resolver.getPassword()) != null && resolver.getUserRole() == Role.FINANCE_MANAGER) {
				
				//Check to see that the reimbursement request exists -- how?
				if(rDAO.getById(unprocessedReimbursement.getId()) != null) {
					
				} else {
					System.out.println("Reimbursement Request not found! Please create a request.");
					throw new UserLoginFailedException(); 
				}
				
				Status newStatus = finalStatus; 
				Reimbursement processedReimb = unprocessedReimbursement; 
				processedReimb.setStatus(newStatus); 
				processedReimb.setResolver(resolver);
				
				return rDAO.update(processedReimb); 
				
			} else {
				
				System.out.println("Reimbursement could not be processed! Try again!");
				throw new UserLoginFailedException(); 
			}
		} catch (UserLoginFailedException e) {
			System.out.println("You're not logged in as a finance manager!");
			e.printStackTrace();
		}
		
		
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

		//Changing it back for tests; need to check postman to see if it passes

		return rDAO.getByStatus(status);
	}
	
	//Get all reimbursements by Author
	public List<Reimbursement> getReimbursementByAuthor(User author) {

		//Changing it back for tests; need to check postman to see if it passes

		return rDAO.getAll(author);
	}
	
	public Optional<Reimbursement> getById(int id) {
		return rDAO.getById(id);
	}
}
