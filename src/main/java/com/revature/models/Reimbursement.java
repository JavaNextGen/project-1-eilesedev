package com.revature.models;

import java.sql.Timestamp;

import com.revature.exceptions.RegistrationUnsuccessfulException;
import com.revature.repositories.UserDAO;
import com.revature.services.ReimbursementService;
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
	ReimbursementService rs = new ReimbursementService(); 

	private Timestamp submitted;
	private Timestamp resolved;
	private ReimbursementType reimb_type;
//	private int reimb_amount;

//	private int reimb_status_id;

//	private int reimb_status_id;
//	private int author; 

	/**
	 * This includes the minimum parameters needed for the
	 * {@link com.revature.models.AbstractReimbursement} class. If other fields are
	 * needed, please create additional constructors.
	 */
	public Reimbursement(int id, Status status, User author, User resolver, double reimb_amount) {
		super(id, status, author, resolver, reimb_amount);
	}

//	reimb_amount, reimb_author"
//			+ "reimb_status_id, reimb_type_id
	// Constructor for creating a reimbursement
//	public Reimbursement(int reimb_amount, User author, Status status, ReimbursementType reimbType) {
//		this.reimb_amount = reimb_amount; 
//		super.setAuthor(author);
//		super.setStatus(status);
//		this.reimb_type = reimbType; 
//	}
	
	//Update reimbursements
	public Reimbursement(int statusID, int resolverID) {
		
		if(statusID == 1) {
			super.setStatus(Status.PENDING);
		} else if(statusID == 2) {
			super.setStatus(Status.APPROVED);
		} else {
			super.setStatus(Status.DENIED);
		}
		
		super.setResolver(us.getUserById(resolverID));

	}

	//creating reimbursements
	public Reimbursement(double reimb_amount, User author, Status status, ReimbursementType reimbType, User resolver) {

		super.setAmount(reimb_amount);
		super.setAuthor(author);

		super.setStatus(status);
		this.setType(reimbType);
		super.setResolver(resolver);

	}

	// Tried this not working
	public Reimbursement(Status pending, User author, User resolver, int d, ReimbursementType type) {
		super.setStatus(pending);
		super.setAuthor(author);
		super.setResolver(resolver);
		super.setAmount(d);
		this.setType(type);

	}

	public Reimbursement(Status status, User author, double amount, ReimbursementType type) {
		super.setStatus(status);
		super.setAuthor(author);
		super.setAmount(amount);
		this.setType(type);
	}

	// Reimbursement created from front-end
//	{"typeId":"1","amount":"20000","status":1}

//	public Reimbursement(int id, int typeId, double amount, int status) { //
//
//		super.setId(id);
//
//		// Loding ID to Lodging
//		if (typeId == 1) {
//			this.setType(ReimbursementType.LODGING);
//		} else if (typeId == 2) {
//			this.setType(ReimbursementType.TRAVEL);
//		} else if (typeId == 3) {
//			this.setType(ReimbursementType.FOOD);
//		} else if (typeId == 4) {
//			this.setType(ReimbursementType.OTHER);
//		}
//
//		// status ID to Status
//		if (status == 1)
//			super.setStatus(Status.PENDING);
//
//		super.setAmount(amount);
//
//	}
	
//	Reimbursement(rmbDTO.getStatusID(), rmbDTO.getAuthor(), rmbDTO.getAmount(), rmbDTO.getTypeID(), rmbDTO.getResolver())

	//On trying to fix reimbursements, this is what I used
	public Reimbursement(int statusID, int authorID, double amount, int typeId, int resolverID) {
		
		if(statusID == 1) {
			super.setStatus(Status.PENDING);
		} else if(statusID == 2) {
			super.setStatus(Status.APPROVED);
		} else {
			super.setStatus(Status.DENIED);
		}
		
		super.setAuthor(us.getUserById(authorID));
		super.setAmount(amount);
		super.setResolver(us.getUserById(resolverID));
		
		if (typeId == 1) {
			this.setType(ReimbursementType.LODGING);
		} else if (typeId == 2) {
			this.setType(ReimbursementType.TRAVEL);
		} else if (typeId == 3) {
			this.setType(ReimbursementType.FOOD);
		} else if (typeId == 4) {
			this.setType(ReimbursementType.OTHER);
		}
	}
	
	public Reimbursement(int id, Status status, int author, int resolver, double amount, Timestamp submitted,
			Timestamp resolved, ReimbursementType type) {
		super.setId(id);
		super.setStatus(status);
		super.setAuthor(uDAO.getUserById(author));
		super.setResolver(uDAO.getUserById(resolver));
		super.setAmount(amount);
		this.submitted = submitted;
		this.resolved = resolved;
		this.reimb_type = type;
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
		return reimb_type;
	}

	public void setType(ReimbursementType type) {
		this.reimb_type = type;
	}
//
//	public int getReimb_amount() {
//		return reimb_amount;
//	}
//
//	public void setReimb_amount(int reimb_amount) {
//		this.reimb_amount = reimb_amount;
//	}

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
