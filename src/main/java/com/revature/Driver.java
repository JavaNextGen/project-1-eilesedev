package com.revature;

import java.util.Scanner;

import com.revature.models.AbstractUser;
import com.revature.models.Role;

public class Driver {

    public static void main(String[] args) {
    	/**
    	 * This is where the User enters their information   	 
    	 * */
    	//User info-- read in from Scanner -- VALIDATION LATER
    	System.out.println("User: Enter your information here");
    	Scanner s = new Scanner(System.in); 
    	
    	int id = s.nextInt(); 
    	String username = s.nextLine(); 
    	String password = s.nextLine(); 
    	//HOW DOES USER CHOOSE THEIR ROLE?
    	//Closer scanner
    	s.close(); 
    	
    	//Instantiate a User object and pass in the above values 
    	AbstractUser newUser = new AbstractUser(); 
    	newUser.setId(id);
    	newUser.setUsername(username);
    	newUser.setPassword(password);
//    	newUser.setRole(role);
    	
    	
    	
    	//Manager enters their information -- read in from Scanner
    	System.out.println("Manager: Enter your information here");
    }
}
