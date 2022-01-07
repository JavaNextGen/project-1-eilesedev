package com.revature.repositories;

import com.revature.exceptions.RegistrationUnsuccessfulException;
import com.revature.models.Reimbursement;
import com.revature.models.Role;
import com.revature.models.Status;
import com.revature.models.User;
import com.revature.util.ConnectionFactory;

//import static org.junit.Assert.assertEquals;
//import static org.mockito.Matchers.any;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
//import java.util.SimpleTimeZone;
import java.util.TimeZone;

public class ReimbursementDAO {
	 Calendar newCal = Calendar.getInstance();
	TimeZone newtime = TimeZone.getDefault(); 

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
		int statusId;

		switch (status) {
		case PENDING: {
			statusId = 1;
			break;
		}
		case APPROVED: {
			statusId = 2;
			break;
		}
		case DENIED: {
			statusId = 3;
			break;
		}
		}

		try (Connection conn = ConnectionFactory.getConnection()) {
			// Any reimbursement status passed in should return a list of all reimbursements
			// of that type

			ResultSet rs = null;

			String sql = "SELECT * from ers_reimbursement WHERE reimb_status_id=statusId";
			
			PreparedStatement ps = conn.prepareStatement(sql); 
			
			rs = ps.executeQuery(); 
			
			List<Reimbursement> reimbursementList = new ArrayList<>(); 
			
			while(rs.next()) {
				Reimbursement r = new Reimbursement(
						rs.getInt("reimb_id"),
						Status.values()[rs.getInt("reimb_status_id") - 1],
						rs.getInt("reimb_author"),
						rs.getInt("reimb_resolver"),
						rs.getFloat("reimb_amount"),
						rs.getTimestamp("reimb_submitted"), 
						rs.getTimestamp("reimb_resolved")					
						);
				
				reimbursementList.add(r); 

			}
			
			return reimbursementList; 

		} catch (SQLException e) {
			System.out.println("Something has gone wrong!");
			e.printStackTrace();
		}

		// Or an empty list if there are no reimbursements
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
    				+ "reimb_submitted, reimb_amount)" + "VALUES (?, ?, ?, ?)";
    		
    		PreparedStatement p = conn.prepareStatement(sqlCreate);
    		
    		p.setInt(1, newReimbursement.getStatus().ordinal() + 1);
    		p.setInt(2, newReimbursement.getAuthor().getId());
    		p.setTimestamp(3, Timestamp.valueOf("2022-01-07 05:20:02"));
    		p.setDouble(4, newReimbursement.getAmount());
    		
    		p.executeUpdate(); // use for inserts, updates, and deletes
    		
    		// TEST - send to console if successful
    		System.out.println("Reimbursement Successfully added!");
    		System.out.println("Please check in on the status of your reimbursement in a few weeks");
    		
    		
    	} catch(SQLException e) {
    		System.out.println("Reimbursement could not be created!");
    		e.getStackTrace(); 
    	}
    	return newReimbursement;
    }
    
}
