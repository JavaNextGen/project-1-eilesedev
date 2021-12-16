package com.revature.models;

import java.util.Scanner;

public class UserMenu {
	
	//Build CLI here
	public void menuDisplay() {
		//Instance variables
		
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
		    	s.nextLine(); //move to nextLine because nextInt() doesnt't 
		    	
		    	System.out.println("Enter your Username below: ");
		    	String username = s.nextLine(); 
		    	
		    	System.out.println("Enter your password below: ");
		    	String password = s.nextLine(); 
		    	
		    	System.out.println("Welcome Employee!");
		    	break; 
			}
			case "M":{
				System.out.println("Enter your User ID below: ");//USER VALIDATION -- ALSO LET USER KNOW # NEEDED
				int mId = s.nextInt(); 
		    	s.nextLine(); //move to nextLine because nextInt() doesnt't 
		    	
		    	System.out.println("Enter your Username below: ");
		    	String mUsername = s.nextLine(); 
		    	
		    	System.out.println("Enter your password below: ");
		    	String mPassword = s.nextLine(); 
		    	
		    	System.out.println("Welcome Manager!");
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
