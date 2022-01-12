package com.revature.repositories;

import com.revature.exceptions.RegistrationUnsuccessfulException;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementType;
import com.revature.models.Role;
import com.revature.models.Status;
import com.revature.models.User;
import com.revature.services.UserService;
import com.revature.util.ConnectionFactory;

//import static org.junit.Assert.assertEquals;
//import static org.mockito.Matchers.any;
//import static org.mockito.Matchers.anyInt;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;

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

	UserService us = new UserService();

	/**
	 * Should retrieve a Reimbursement from the DB with the corresponding id or an
	 * empty optional if there is no match.
	 */
	public Optional<Reimbursement> getById(int id) {
		return Optional.empty();
	}

	/**
	 * Should retrieve a List of Reimbursements from the DB with the corresponding
	 * Status or an empty List if there are no matches.
	 */

	public List<Reimbursement> getByStatus(int status) {
		

		try (Connection conn = ConnectionFactory.getConnection()) {
//			int statusId = (status.ordinal() + 1); 
			// Any reimbursement status passed in should return a list of all reimbursements
			// of that type

			ResultSet rs = null;

			String sql = "SELECT * from ers_reimbursement WHERE reimb_status_id=?";

			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, status);

			rs = ps.executeQuery();

			List<Reimbursement> reimbursementList = new ArrayList<>();

			while (rs.next()) {
				Reimbursement r = new Reimbursement(rs.getInt("reimb_id"),
						Status.values()[rs.getInt("reimb_status_id") - 1], rs.getInt("reimb_author"),
						rs.getInt("reimb_resolver"), rs.getInt("reimb_amount"), rs.getTimestamp("reimb_submitted"),
						rs.getTimestamp("reimb_resolved"));

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
	 * <li>Should Update an existing Reimbursement record in the DB with the
	 * provided information.</li>
	 * <li>Should throw an exception if the update is unsuccessful.</li>
	 * <li>Should return a Reimbursement object with updated information.</li>
	 * </ul>
	 */
	public Reimbursement update(Reimbursement unprocessedReimbursement) {

		return null;
	}

	/**
	 * public void
	 * testProcessPassesWhenUserIsFinanceManagerAndReimbursementExistsAndUpdateSuccessful()
	 * {
	 * when(reimbursementDAO.getById(anyInt())).thenReturn(Optional.of(GENERIC_REIMBURSEMENT_1));
	 * when(reimbursementDAO.update(any())).thenReturn(GENERIC_REIMBURSEMENT_2);
	 * 
	 * assertEquals(GENERIC_REIMBURSEMENT_2,
	 * reimbursementService.process(REIMBURSEMENT_TO_PROCESS, Status.APPROVED,
	 * GENERIC_FINANCE_MANAGER_1));
	 * 
	 * verify(reimbursementDAO).getById(REIMBURSEMENT_TO_PROCESS.getId());
	 * verify(reimbursementDAO).update(REIMBURSEMENT_TO_PROCESS); }
	 * 
	 * 
	 * 
	 */

//	{"typeId":"1","amount":"20000","status":1}
	
	public Reimbursement create(Reimbursement newReimbursement) {

		try (Connection conn = ConnectionFactory.getConnection()) {


//			INSERT INTO ers_reimbursement(reimb_amount, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id)
//			VALUES (500.00, 1, NULL, 1, 2); 

			String sql = "INSERT INTO ers_reimbursement (reimb_amount, reimb_author"
					+ "reimb_status_id, reimb_type_id)" + "VALUES (?, ?, ?, ?)";

			PreparedStatement p = conn.prepareStatement(sql);

			
			p.setDouble(1, newReimbursement.getAmount());
			p.setObject(2, newReimbursement.getAuthor());
			p.setObject(3, newReimbursement.getStatus().ordinal() + 1);
			p.setObject(4, newReimbursement.getType().ordinal() + 1);

			p.executeUpdate();

			// TEST - send to console if successful
			System.out.println(newReimbursement.toString());
			System.out.println("Reimbursement Successfully added!");
			System.out.println("Please check in on the status of your reimbursement in a few weeks");

		} catch (SQLException e) {

			System.out.println("Reimbursement could not be created!");
			e.getStackTrace();

		}

		return newReimbursement;
	}

	
	//THIS IS THE ONE THAT I'M HAVING PROBLEMS WITH TESTING ANOTHER SOLUTION
	
//	public Reimbursement create(Reimbursement newReimbursement) {
//
//		try (Connection conn = ConnectionFactory.getConnection()) {
//
////    		Status pending, User author, User resolver, double d, ReimbursementType type
//			//Trying to fix db create issue on reimbursements
////			INSERT INTO ers_reimbursement(reimb_amount, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id)
////			VALUES (500.00, 1, NULL, 1, 2); 
//
//			String sql = "INSERT INTO ers_reimbursement (reimb_status_id, reimb_author, reimb_resolver"
//					+ "reimb_amount, reimb_type_id)" + "VALUES (?, ?, ?, ?, ?)";
//
//			PreparedStatement p = conn.prepareStatement(sql);
//
//			p.setInt(1, newReimbursement.getStatus().ordinal() + 1);
//			p.setInt(2, newReimbursement.getAuthor().getId());
//			p.setInt(3, newReimbursement.getResolver().getId());
//			p.setDouble(4, newReimbursement.getAmount());
//			p.setInt(5, newReimbursement.getType().ordinal() + 1);
//
//			p.executeUpdate();
//
//			// TEST - send to console if successful
//			System.out.println(newReimbursement.toString());
//			System.out.println("Reimbursement Successfully added!");
//			System.out.println("Please check in on the status of your reimbursement in a few weeks");
//
//		} catch (SQLException e) {
//
//			System.out.println("Reimbursement could not be created!");
//			e.getStackTrace();
//
//		}
//
//		return newReimbursement;
//	}
}
