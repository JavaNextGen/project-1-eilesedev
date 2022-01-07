package com.revature.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.TimeZone;

import com.revature.exceptions.RegistrationUnsuccessfulException;
import com.revature.repositories.UserDAO;
import com.revature.util.ConnectionFactory;

/**
 * This concrete Reimbursement class can include additional fields that can be used for
 * extended functionality of the ERS application.
 *
 * Example fields:
 * <ul>
 *     <li>Description</li>
 *     <li>Creation Date</li>
 *     <li>Resolution Date</li>
 *     <li>Receipt Image</li>
 * </ul>
 *
 */
public class Reimbursement extends AbstractReimbursement {
	User u = new User(); 
	UserDAO uDAO = new UserDAO(); 
	

	
	private TimeZone newDate; 
	private Timestamp newTime; 
	private Timestamp resolved;
	

    public Reimbursement() {
        super();
    }

    /**
     * This includes the minimum parameters needed for the {@link com.revature.models.AbstractReimbursement} class.
     * If other fields are needed, please create additional constructors.
     */
    public Reimbursement(int id, Status status, User author, User resolver, double amount) {	
        super(id, status, author, resolver, amount);
    }
    
    
    //Basic Functionality to register user
//    public User(String username, String password, String fName, String lName, String email, Role role) {
//        super.setUsername(username);
//        super.setPassword(password);
//        //The code above should take care of the ID problem
//        this.f_Name = fName; 
//        this.l_Name = lName; 
//        this.email = email; 
//        this.userRole = role; 
//    }
//    
    
    
    //Create reimbursements--no ID -- serial in database
    public Reimbursement(Status status, User author, TimeZone date, double amount) {	
        super.setStatus(status);
        super.setAuthor(author);
        super.setAmount(amount);
        this.newDate = date;
    }

	public Reimbursement(int id, Status status, int author, int resolver, float amount, Timestamp submitted, Timestamp resolved) {
		super.setId(id);
		super.setStatus(status);
		super.setAuthor(uDAO.getUserById(author)); 
		super.setResolver(uDAO.getUserById(resolver));
		super.setAmount(amount);
		this.newTime = submitted; 
		this.resolved = resolved; 
	}

	public TimeZone getNewDate() {
		return newDate;
	}

	public void setNewDate(TimeZone newtime) {
		this.newDate = newtime;
	}

	public Timestamp getNewTime() {
		return newTime;
	}

	public void setNewTime(Timestamp newTime) {
		this.newTime = newTime;
	}

	public Timestamp getResolved() {
		return resolved;
	}

	public void setResolved(Timestamp resolved) {
		this.resolved = resolved;
	}
    
    
    //constructor if User wants to enter what reimbursement is for --ADD in after basic func -- create enum for type
//    public Reimbursement(int id, Status status, User author, User resolver, double amount) {	
//        super(id, status, author, resolver, amount);
//    }
    

}
