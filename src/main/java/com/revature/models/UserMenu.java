package com.revature.models;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;
import java.util.TimeZone;

import com.revature.exceptions.UserLoginFailedException;
import com.revature.repositories.UserDAO;
import com.revature.services.AuthService;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;

public class UserMenu {
	Calendar newCal = Calendar.getInstance(); 
	TimeZone today = TimeZone.getDefault();
	
	//Build CLI here
	public void menuDisplay() {
		//Instance variables
		Role userRole = null; 
		int id = 0; 

//        Date currentDate = Calendar.getInstance(TimeZone.getDefault()).getTime() 
		
		//Object to use methods in User.java
//		UserDAO newUser = new UserDAO(); //Let's get rid of this if the service layer works DAO should not be exposed
		
		AuthService auth = new AuthService(); 
		
		UserService newServ = new UserService(); 
		
//		User u = new User(); 
		
		ReimbursementService reimb = new ReimbursementService(); 
		
		boolean menuDisplay = true; 
		//Accept user input --Scanner class
		Scanner s = new Scanner(System.in); 
		
		//Actual menu displayed to user
		System.out.println("*****************************************************");
		System.out.println("            Welcome to Your ERS Manager");
		System.out.println("*****************************************************");
		
		//display menu as long as menuDisplay boolean is true -- EXTRAS First Name, Last Name, email for both Roles
		while(menuDisplay) {
			System.out.println("Please press 'R' to register a new account and 'L' to log-in"
					+ " If you want to exit the application type 'exit'");
			
			//parse user input 
			String input = s.nextLine(); 
			
			//takes user input and executes appropriate code
			switch(input.toUpperCase()) {
			case "R":{
				//allow the user to enter employee/manager
				System.out.println("If you are an Employee, type 'Employee' now"); 
				System.out.println("If you are a Finance Manager, type 'Finance Manager' now");
		    	String roleId = s.nextLine(); //pass this as parameter into register method
//		    	s.nextLine(); 
		    	
		    	//Assign ID to enum what if I turn an enum into an ID -- user enters role, I use hash to compare the objects and then enters an Id into the database
		    	
		    	//I should be able to allow the user to compare the string they entered with the role
		    	//If employee enters this then roleID has to be the enum that is entered into the object and how the crud do I pass that object into the database?
		    	
		    	//must be able to ignore case
		    	switch(roleId) { 
		    	case "Employee": {
		    			 userRole = Role.EMPLOYEE; 
		    			 break; 
		    		}
		    	case "Finance Manager" : {
		    			 userRole = Role.FINANCE_MANAGER;
		    			 break; 
		    		}
		    	}
		    	
		    	System.out.println("Enter your new username below: ");
		    	String username = s.nextLine(); 
		    	
		    	System.out.println("Choose a strong password for your new account below: ");
		    	String password = s.nextLine(); 
		    	
		    	System.out.println("Enter your first name below: ");
		    	String fName = s.nextLine(); 
		    
		    	System.out.println("Enter your last name below: ");
		    	String lName = s.nextLine(); 
		    	
		    	System.out.println("Enter your email address: ");
		    	String email = s.nextLine(); 
		    	
//		    	int id = 0; 
		    	//If the user is entering their information they shouldn't be entering the ID 
		    	User newU = new User(username, password, fName, lName, email, userRole); 
		    	System.out.println(newU.toString());
//		    	newUser.create(newU); 
		    	auth.register(newU);
		    	
		    	
		    	//Run method to create an account here 
		    	
		    	//THIS IS WHERE WE BREAK FOR SUB MENUS -- More functionality, less code? ENUMS, MULTITHREADING? 
//		    	System.out.println("Welcome Employee!" + '\n');
//		    	System.out.println("Press 'V' to view past tickets OR");
//		    	System.out.println("Press 'A' to add a reimbursement"); 
//		    	String subMenu = s.nextLine(); 
//		    	if(subMenu.toUpperCase().equals("V")) {
//		    		System.out.println("You are now viewing past tickets");
//		    	} else if(subMenu.toUpperCase().equals("A")) {
//		    		System.out.println("Add a reimbursement");
//		    	}
//		    	//SUB MENU ENDS
		    	break; 
			}
			case "L":{
				//User is asked to login in the UserMenu class
				//Run method to sign-in here
				System.out.println("Enter your Username below: ");
				String username = s.nextLine(); 
				
				System.out.println("Enter the password for your account below: ");
		    	String password = s.nextLine(); 
		    	
		    	//Create user from object pulled from database
//				User dbU = new User();
		    	Optional<User> usernombre = newServ.getByUsername(username); 

		    	
		    	//Create variables for user object
		    	int loginId; 
//		    	String loginUnm; 
//		    	String loginPas; 
		    	Role loginRole; 

//					if(auth.login(usernombre.get().toString(), password) != null) {
						int logInId = auth.login(username, password).getId(); 
						String logInUsername = auth.login(username, password).getUsername(); 
						String logInPswd = auth.login(logInUsername, password).getPassword();
						Role logInRole = auth.login(logInUsername, password).getRole(); 
						User loggedInUser = new User(logInId, logInUsername, logInPswd,logInRole);
						
						User absManager = new User(0, Role.FINANCE_MANAGER); 
						
						System.out.println("You are a(n) " + loggedInUser.getRole());
						
						if(logInRole.equals(Role.EMPLOYEE)) {
							System.out.println("Enter your reimbursement request below: ");

							double amount = s.nextDouble(); 
							s.nextLine();
							
//							 public Reimbursement(Status status, User author, double amount)
							
							//Create new reimbursement object
							Reimbursement newReimb = new Reimbursement(Status.PENDING, loggedInUser, today, amount);  
							reimb.create(newReimb); 
							
//							System.out.println("Success your reimbursement has been added!");
							
							newReimb.toString();
						}
//					}
						
				
					
					
					
				
			
				
//				String f_name = usernombre.get().getF_Name();
				
//				System.out.println("Hi Again " + f_name);
//				Role uRole = usernombre.get().getUserRole();
//				System.out.println("You are a(n) " + loginRole);
//				User us = new User(usernombre.get().getUsername()); 
				
				
				
				 
				
				
				
		    	break; 
			}
			
			
			case "EXIT":{
				System.out.println("You've exited the menu. Goodbye!");
				s.close();
				menuDisplay = false; 
				break; 
			}
			default: {
				System.out.println("Invalid entry, try again!");
				break; 
			}
			}//End of switch
			
		}//End of while loop
	}

}
