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
	private ReimbursementType type;
	private int typeId; 
//	private int author; 




	public Reimbursement() {
		super();
	}

	/**
	 * This includes the minimum parameters needed for the
	 * {@link com.revature.models.AbstractReimbursement} class. If other fields are
	 * needed, please create additional constructors.
	 */
	public Reimbursement(int id, Status status, User author, User resolver, double amount) {
		super(id, status, author, resolver, amount);
	}

	// Basic Functionality to register user
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

	// Create reimbursements--no ID -- serial in database
	
//	p.setDouble(1, newReimbursement.getAmount());	
//	p.setInt(2, newReimbursement.getAuthor().getId());
//	p.setInt(1, newReimbursement.getStatus().ordinal() + 1);
//	p.setInt(5, newReimbursement.getType().ordinal() + 1);
	
	public Reimbursement( double amount, int author, int status,  int type) { //

		// status ID to Status
		if (status == 1)
			super.setStatus(Status.PENDING);
		else if (status == 2) {
			super.setStatus(Status.APPROVED);
		} else {
			super.setStatus(Status.DENIED);
		}

		// Loding ID to Lodging
		if (type == 1) {
			this.setType(ReimbursementType.LODGING);
		} else if (type == 2) {
			this.setType(ReimbursementType.TRAVEL);
		} else if (type == 3) {
			this.setType(ReimbursementType.FOOD);
		} else {
			this.setType(ReimbursementType.OTHER);
		}
		
		//user passes in an ID; convert here from User ID to user object and set Abstract Reimbursement Author

		super.setAuthor(us.getUserById(author));
		super.setAmount(amount);

	}

	public Reimbursement(int id, Status status, int author, int resolver, float amount, Timestamp submitted,
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
		return type;
	}

	public void setType(ReimbursementType type) {
		this.type = type;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	

	// constructor if User wants to enter what reimbursement is for --ADD in after
	// basic func -- create enum for type
//    public Reimbursement(int id, Status status, User author, User resolver, double amount) {	
//        super(id, status, author, resolver, amount);
//    }

}
