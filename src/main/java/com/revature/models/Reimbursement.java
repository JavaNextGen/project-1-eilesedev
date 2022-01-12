package com.revature.models;

import java.sql.Timestamp;

import com.revature.exceptions.RegistrationUnsuccessfulException;
import com.revature.repositories.UserDAO;
import com.revature.services.UserService;
import com.revature.util.ConnectionFactory;

/**
 * This concrete Reimbursement class can include additional fields that can be
 * used for extended functionality of the ERS application.
 *
 * Example fields:
 * <ul>
 * <li>Description</li>
 * <li>Creation Date</li>
 * <li>Resolution Date</li>
 * <li>Receipt Image</li>
 * </ul>
 *
 */
public class Reimbursement extends AbstractReimbursement {
	User u = new User();
	UserDAO uDAO = new UserDAO();
	UserService us = new UserService();

	private Timestamp submitted;
	private Timestamp resolved;
	private ReimbursementType reimb_type_id;
	private int typeId;
	private int rmbId;
	private int reimb_status_id;


//	private int reimb_status_id;
//	private int author; 

	/**
	 * This includes the minimum parameters needed for the
	 * {@link com.revature.models.AbstractReimbursement} class. If other fields are
	 * needed, please create additional constructors.
	 */
	public Reimbursement(int id, Status status, User author, User resolver, double amount) {
		super(id, status, author, resolver, amount);
	}
	
	
    //This is what I'm trying now to fix issue with creating reimbursements 1/11/22
	public Reimbursement(double reimb_amount, User author, Status status, ReimbursementType reimbType) { 
		
		super.setAmount(reimb_amount);
		super.setAuthor(author);
		
		
		super.setStatus(status);
		this.setType(reimbType);

	}

	//Tried this not working
	public Reimbursement(Status pending, User author, User resolver, double d, ReimbursementType type) {
		super.setStatus(pending);
		super.setAuthor(author);
		super.setResolver(resolver);
		super.setAmount(d);
		this.setType(type);

	}

	// Pulled from previous versions -- WAS WORKING!!!!!!!!!!!!!
	public Reimbursement(Status status, User author, double amount) {
		super.setStatus(status);
		super.setAuthor(author);
		super.setAmount(amount);
	}

	// Reimbursement created from front-end
//	{"typeId":"1","amount":"20000","status":1}

	public Reimbursement(int typeId, int amount, int status) { //

		// Loding ID to Lodging
		if (typeId == 1) {
			this.setType(ReimbursementType.LODGING);
		} else if (typeId == 2) {
			this.setType(ReimbursementType.TRAVEL);
		} else if (typeId == 3) {
			this.setType(ReimbursementType.FOOD);
		} else if (typeId == 4) {
			this.setType(ReimbursementType.OTHER);
		}

		// status ID to Status
		if (status == 1)
			super.setStatus(Status.PENDING);

		super.setAmount(amount);

	}

	public Reimbursement(int id, Status status, int author, int resolver, int amount, Timestamp submitted,
			Timestamp resolved) {
		super.setId(id);
		super.setStatus(status);
		super.setAuthor(uDAO.getUserById(author));
		super.setResolver(uDAO.getUserById(resolver));
		super.setAmount(amount);
		this.submitted = submitted;
		this.resolved = resolved;
	}

	public Timestamp getSubmittedTime() {
		return submitted;
	}

	public void setSubmittedTime(Timestamp newTime) {
		this.submitted = newTime;
	}

	public Timestamp getResolved() {
		return resolved;
	}

	public void setResolved(Timestamp resolved) {
		this.resolved = resolved;
	}

	public ReimbursementType getType() {
		return reimb_type_id;
	}

	public void setType(ReimbursementType type) {
		this.reimb_type_id = type;
	}

	
	public Reimbursement() {
		super();
	}
	// constructor if User wants to enter what reimbursement is for --ADD in after
	// basic func -- create enum for type
//    public Reimbursement(int id, Status status, User author, User resolver, double amount) {	
//        super(id, status, author, resolver, amount);
//    }


	@Override
	public String toString() {
		return "Reimbursement [getSubmittedTime()=" + getSubmittedTime() + ", getResolved()=" + getResolved()
				+ ", getType()=" + getType() + ", getId()=" + getId() + ", getStatus()=" + getStatus()
				+ ", getAuthor()=" + getAuthor() + ", getResolver()=" + getResolver() + ", getAmount()=" + getAmount()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + ", getClass()=" + getClass()
				+ "]";
	}






	

}
