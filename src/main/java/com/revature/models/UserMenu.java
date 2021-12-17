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
		System.out.println("Welcome user: Enter M for Manager or E for Employee");
		System.out.println("*****************************************************");
		
		//display menu as long as menuDisplay boolean is true
		while(menuDisplay) {
			System.out.println("Hi! Enter M for Manager or E for Employee. "
					+ "If you want to exit the application type 'exit'");
			
			//parse user input 
			String input = s.nextLine(); 
			
			//takes user input and executes appropriate code
			switch(input.toUpperCase()) {
			case "E":{
				System.out.println("Enter your User ID below: ");//USER VALIDATION -- ALSO LET USER KNOW # NEEDED
				int id = s.nextInt(); 
		    	s.nextLine(); 
		    	
		    	System.out.println("Enter your Username below: ");
		    	String username = s.nextLine(); 
		    	
		    	System.out.println("Enter your password below: ");
		    	String password = s.nextLine(); 
		    	
		    	//THIS IS WHERE WE BREAK FOR SUB MENUS -- More functionality, less code? ENUMS, MULTITHREADING? 
		    	System.out.println("Welcome Employee!" + '\n');
		    	System.out.println("Press 'V' to view past tickets OR");
		    	System.out.println("Press 'A' to add a reimbursement"); 
		    	String subMenu = s.nextLine(); 
		    	if(subMenu.toUpperCase().equals("V")) {
		    		System.out.println("You are now viewing past tickets");
		    	} else if(subMenu.toUpperCase().equals("A")) {
		    		System.out.println("Add a reimbursement");
		    	}
		    	//SUB MENU ENDS
		    	break; 
			}
			case "M":{
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
