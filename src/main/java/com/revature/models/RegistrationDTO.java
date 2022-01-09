package com.revature.models;

public class RegistrationDTO {

	private String username;
	private String password;
	private String fName;
	private String lName;
	private String email;
	private int role;

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public RegistrationDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RegistrationDTO(String username, String password, String fName, String lName, String email, int role) {
		super();
		this.username = username;
		this.password = password;
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public String toString() {
		return "RegistrationDTO [username=" + username + ", password=" + password + ", fName=" + fName + ", lName="
				+ lName + ", email=" + email + ", role=" + role + "]";
	}

}
