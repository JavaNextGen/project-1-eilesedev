package com.revature.repositories;

import com.revature.exceptions.RegistrationUnsuccessfulException;
import com.revature.models.Reimbursement;
import com.revature.models.Status;
import com.revature.models.User;
import com.revature.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ReimbursementDAO {

    /**
     * Should retrieve a Reimbursement from the DB with the corresponding id or an empty optional if there is no match.
     */
    public Optional<Reimbursement> getById(int id) {
        return Optional.empty();
    }

    /**
     * Should retrieve a List of Reimbursements from the DB with the corresponding Status or an empty List if there are no matches.
     */
    public List<Reimbursement> getByStatus(Status status) {
        return Collections.emptyList();
    }

    /**
     * <ul>
     *     <li>Should Update an existing Reimbursement record in the DB with the provided information.</li>
     *     <li>Should throw an exception if the update is unsuccessful.</li>
     *     <li>Should return a Reimbursement object with updated information.</li>
     * </ul>
     */
    public Reimbursement update(Reimbursement unprocessedReimbursement) {
    	return null;
    }
    
    public Reimbursement create(Reimbursement newReimbursement) {
    	try (Connection conn = ConnectionFactory.getConnection()){
    		
    		// Create new reimbursement request
    		String sqlCreate = "INSERT INTO ers_reimbursement (reimb_status_id, reimb_author,"
    				+ "reimb_resolver, reimb_amount)" + "VALUES (?, ?, ?, ?)";
    		
    		PreparedStatement p = conn.prepareStatement(sqlCreate);
    		
    		p.setInt(1, newReimbursement.getStatus().ordinal() + 1);
    		p.setInt(2, newReimbursement.getAuthor().getId());
    		p.setInt(3, newReimbursement.getResolver().getId());
    		p.setDouble(4, newReimbursement.getAmount());
    		
    		p.executeUpdate(); // use for inserts, updates, and deletes
    		
    		// TEST - send to console if successful
    		System.out.println("Reimbursement Successfully added " + newReimbursement.getAuthor().getF_Name() + "!");
    		System.out.println("Please check in on the status of your reimbursement in a few weeks");
    		
    		
    	} catch(SQLException e) {
    		System.out.println("Reimbursement could not be created!");
    		e.getStackTrace(); 
    	}
    	return newReimbursement;
    }
    
}
