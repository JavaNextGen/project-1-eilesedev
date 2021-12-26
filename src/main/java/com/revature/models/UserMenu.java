package com.revature.models;

import java.util.Scanner;

public class UserMenu {
	
	//Build CLI here
	public void menuDisplay() {
//		//Instance variables
//		String subMenu; //Used to switch into sub-menu
//		String addReimburse; 
		
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
					+ "If you want to exit the application type 'exit'");
			
			//parse user input 
			String input = s.nextLine(); 
			
			//takes user input and executes appropriate code
			switch(input.toUpperCase()) {
			case "R":{
				//User info entered -- here
				System.out.println("If you are an Employee, Enter the Number 1 Now"); 
				System.out.println("If you are a Finance Manager, Enter the Number 2 Now");
		    	int roleId = s.nextInt(); //pass this as parameter into register method
		    	s.nextLine(); 
		    	
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
		    	
		    	User newU = new User(roleId, email, email, null, email, email, email); 
		    	
		    	
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
				//Run method to sign-in here
				System.out.println("Enter your User ID below: ");//USER VALIDATION -- ALSO LET USER KNOW # NEEDED
				int mId = s.nextInt(); 
		    	s.nextLine(); 
		    	
		    	System.out.println("Enter your Username below: ");
		    	String mUsername = s.nextLine(); 
		    	
		    	System.out.println("Enter your password below: ");
		    	String mPassword = s.nextLine(); 
		    	
		    	//THIS IS WHERE WE BREAK FOR SUB MENUS -- More functionality, less code? ENUMS, MULTITHREADING?
		    	System.out.println("Welcome Manager!");
		    	System.out.println("Press 'V' to view reimbursements OR");
		    	System.out.println("Press 'A' to approve or deny a request OR"); 
		    	System.out.println("Press 'F' to Filter user requests by status"); 
		    	String subMenu = s.nextLine(); 
		    	if(subMenu.toUpperCase().equals("V")) {
		    		System.out.println("You are now viewing reimbursements");
		    	} else if(subMenu.toUpperCase().equals("A")) {
		    		System.out.println("Approve or deny request for time off");
		    	}  else if(subMenu.toUpperCase().equals("F")) {
		    		System.out.println("You are now viewing all employee requests "
		    				+ "for time off. Filter your results...");
		    	} 
		    	//SUB MENU ENDS
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
