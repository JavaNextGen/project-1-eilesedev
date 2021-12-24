package com.revature;
import java.sql.Connection;
import java.sql.SQLException;

import com.revature.models.UserMenu;
import com.revature.util.ConnectionFactory;

public class Driver {

    public static void main(String[] args) {
    	
    	//Test database connection -- try with resources
    	try(Connection conn = ConnectionFactory.getConnection()){
    		System.out.println("Connection successful");
    	} catch(SQLException e){
    		System.out.println("Database Connection failed");
    		e.printStackTrace(); 
    	}
    	
    	//Run Menu
    	UserMenu user = new UserMenu(); 
    	user.menuDisplay();
    	
    }
}
