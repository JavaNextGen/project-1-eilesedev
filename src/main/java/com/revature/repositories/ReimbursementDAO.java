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
import java.sql.Statement;
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

		try (Connection conn = ConnectionFactory.getConnection()) {

			ResultSet rs = null;
			// retrieve user from the database with corresponding username
			String sql = "SELECT * from ers_reimbursement WHERE reimb_id=?";
			// Put the sql query into our statement object
			PreparedStatement ps = conn.prepareStatement(sql);
			// Here -- create SQL String statement to retrieve user record that matches
			// passed in username

			ps.setInt(1, id);
			rs = ps.executeQuery(); // Used to retrieve values from database

			while (rs.next()) {
				Reimbursement r = new Reimbursement(rs.getInt("reimb_id"),
						Status.values()[rs.getInt("reimb_status_id") - 1], rs.getInt("reimb_author"),
						rs.getInt("reimb_resolver"), rs.getInt("reimb_amount"), rs.getTimestamp("reimb_submitted"),
						rs.getTimestamp("reimb_resolved"), ReimbursementType.values()[rs.getInt("reimb_type_id") - 1]);
				
				System.out.println("Reimbursement " + r.toString() + " exists!");

				return Optional.ofNullable(r);
				
			}
			
		} catch (SQLException e) {
			System.out.println("Something has gone wrong!");
			e.printStackTrace();
		}

		return Optional.ofNullable(null);

	}

	/**
	 * Should retrieve a List of Reimbursements from the DB with the corresponding
	 * Status or an empty List if there are no matches.
	 */

	public List<Reimbursement> getByStatus(Status status) {

		int s;

		if (status == Status.PENDING)
			s = 1;
		else if (status == Status.APPROVED)
			s = 2;
		else
			s = 3;

		try (Connection conn = ConnectionFactory.getConnection()) {
//			int statusId = (status.ordinal() + 1); 
			// Any reimbursement status passed in should return a list of all reimbursements
			// of that type

			ResultSet rs = null;

			String sql = "SELECT * from ers_reimbursement WHERE reimb_status_id=?";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, s);

			rs = ps.executeQuery();

			List<Reimbursement> reimbursementList = new ArrayList<>();

			while (rs.next()) {
				Reimbursement r = new Reimbursement(rs.getInt("reimb_id"),
						Status.values()[rs.getInt("reimb_status_id") - 1], rs.getInt("reimb_author"),
						rs.getInt("reimb_resolver"), rs.getInt("reimb_amount"), rs.getTimestamp("reimb_submitted"),
						rs.getTimestamp("reimb_resolved"), ReimbursementType.values()[rs.getInt("reimb_type_id") - 1]);

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
	public Reimbursement update(Reimbursement processedReimbursement) {

		try (Connection conn = ConnectionFactory.getConnection()) {

//			ResultSet rs = null;

			int urId = processedReimbursement.getId();
			System.out.println(urId);
			
			int resolverId = processedReimbursement.getResolver().getId();
			System.out.println(resolverId);
			
			int statusId =  processedReimbursement.getStatus().ordinal();
			System.out.println(statusId);

			String sql = "UPDATE ers_reimbursement SET reimb_resolver=?, reimb_status_id=? WHERE reimb_id =?";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, resolverId);
			ps.setInt(2,statusId);
			ps.setInt(3, urId);

//			rs = ps.executeQuery();
			ps.executeUpdate();

//			List<Reimbursement> allReimbursements = new ArrayList<>();

//			while (rs.next()) {
//				Reimbursement r = new Reimbursement(rs.getInt("reimb_id"),
//						Status.values()[rs.getInt("reimb_status_id") - 1], rs.getInt("reimb_author"),
//						rs.getInt("reimb_resolver"), rs.getInt("reimb_amount"), rs.getTimestamp("reimb_submitted"),
//						rs.getTimestamp("reimb_resolved"), ReimbursementType.values()[rs.getInt("reimb_type_id") - 1]);
//
//				return r;
//
//			}

			System.out.println("DAO msg: your reimbursement is now: " + processedReimbursement.getStatus());
		} catch (SQLException e) {
			System.out.println("DAO msg: Something has gone wrong!");
			e.printStackTrace();
		}

		return null;
	}

//	public Reimbursement(int statusID, int authorID, double amount, int typeId)

	public Reimbursement create(Reimbursement newReimbursement) {

		try (Connection conn = ConnectionFactory.getConnection()) {

			int authorId = newReimbursement.getAuthor().getId(); // THIS WORKS
//			System.out.println(authorId);

			int statusId = newReimbursement.getStatus().ordinal(); // THIS ALSO WORKS
			System.out.println(statusId);

			int reimbId = newReimbursement.getType().ordinal();
			System.out.println(reimbId);

			String sql = "INSERT INTO ers_reimbursement(reimb_amount, reimb_author, "
					+ "reimb_status_id, reimb_type_id)" + "VALUES (?, ?, ?, ?)";

			System.out.println(sql);

			PreparedStatement p = conn.prepareStatement(sql);

			p.setDouble(1, newReimbursement.getAmount()); // this may be the problem
			p.setInt(2, authorId);
//			p.setInt(3,  nu);
			p.setInt(3, statusId + 1);
			p.setInt(4, reimbId + 1);

			p.executeUpdate();

			// TEST - send to console if successful
			System.out.println(newReimbursement.toString());
			System.out.println("Reimbursement Successfully added!");
			System.out.println("Please check in on the status of your reimbursement in a few weeks");

		} catch (SQLException e) {

			System.out.println("Reimbursement could not be created!");
			e.printStackTrace();

		}

		return null;
	}

	// Gets all reimbursements for a specific user

	public List<Reimbursement> getAll(User author) {

		try (Connection conn = ConnectionFactory.getConnection()) {

			ResultSet rs = null;

			String sql = "SELECT * from ers_reimbursement WHERE reimb_author=?";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, author.getId());

			rs = ps.executeQuery();

			List<Reimbursement> reimbursementList = new ArrayList<>();

			while (rs.next()) {
				Reimbursement r = new Reimbursement(rs.getInt("reimb_id"),
						Status.values()[rs.getInt("reimb_status_id") - 1], rs.getInt("reimb_author"),
						rs.getInt("reimb_resolver"), rs.getInt("reimb_amount"), rs.getTimestamp("reimb_submitted"),
						rs.getTimestamp("reimb_resolved"), ReimbursementType.values()[rs.getInt("reimb_type_id") - 1]);

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

//Gets all reimbursements from the DB
	public List<Reimbursement> getAll() {
		try (Connection conn = ConnectionFactory.getConnection()) {

			ResultSet rs = null;

			String sql = "SELECT * from ers_reimbursement";

			Statement ps = conn.createStatement();

			rs = ps.executeQuery(sql);

			List<Reimbursement> reimbursementList = new ArrayList<>();

			while (rs.next()) {
				Reimbursement r = new Reimbursement(rs.getInt("reimb_id"),
						Status.values()[rs.getInt("reimb_status_id") - 1], rs.getInt("reimb_author"),
						rs.getInt("reimb_resolver"), rs.getInt("reimb_amount"), rs.getTimestamp("reimb_submitted"),
						rs.getTimestamp("reimb_resolved"), ReimbursementType.values()[rs.getInt("reimb_type_id") - 1]);

				reimbursementList.add(r);

			}

			return reimbursementList;

		} catch (SQLException e) {
			System.out.println("From the DAO: Something has gone wrong getting all your reimbursements!");
			e.printStackTrace();
		}

		// Or an empty list if there are no reimbursements
		return Collections.emptyList();
	}

}
