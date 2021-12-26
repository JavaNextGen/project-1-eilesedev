package com.revature.models;

/**
 * This concrete User class can include additional fields that can be used for
 * extended functionality of the ERS application.
 *
 * Example fields:
 * <ul>
 *     <li>First Name</li>
 *     <li>Last Name</li>
 *     <li>Email</li>
 *     <li>Phone Number</li> may be null
 *     <li>Address</li> may be null
 * </ul>
 *
 */



public class User extends AbstractUser {
	private String f_Name; 
	private String l_Name; 
	private String email; 
	private int p_num; 
	private String address; 

    public String getF_Name() {
		return f_Name;
	}

	public void setF_Name(String f_Name) {
		this.f_Name = f_Name;
	}

	public String getL_Name() {
		return l_Name;
	}

	public void setL_Name(String l_Name) {
		this.l_Name = l_Name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getP_num() {
		return p_num;
	}

	public void setP_num(int p_num) {
		this.p_num = p_num;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public User() {
        super();
    }

    /**
     * This includes the minimum parameters needed for the {@link com.revature.models.AbstractUser} class.
     * If other fields are needed, please create additional constructors.
     */
    public User(int id, String username, String password, Role role) {
        super(id, username, password, role);
    }
    
    //Basic Functionality to register user
    public User(String username, String password, int roleId, String fName, String lName, String email) {
        //enum switch statement here?
    	super(id, username, password, role);
        this.f_Name = fName; 
        this.l_Name = lName; 
        this.email = email; 
    }
    
    
    //Extended functionality -- user enters more information -- some of these fields may be null
    public User(int id, String username, String password, Role role, String f_name, String l_name, 
    		String email, int phone, String address) {
    	super(id, username, password, role); 
    	this.f_Name = f_name;
    	this.l_Name = l_name; 
    	this.email = email; 
    	this.p_num = phone; 
    	this.address = address; 
    }
}
