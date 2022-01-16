package com.revature;
import java.sql.Connection;
import java.sql.SQLException;

import com.revature.controllers.AuthController;
import com.revature.controllers.ReimbursementController;
import com.revature.controllers.UserController;
import com.revature.exceptions.UserLoginFailedException;
import com.revature.util.ConnectionFactory;

import io.javalin.Javalin;

public class Driver {

    public static void main(String[] args) throws UserLoginFailedException {
    	
    	AuthController ac = new AuthController();
    	UserController uc = new UserController(); 
    	ReimbursementController rc = new ReimbursementController(); 
    	
    	//Test database connection -- try with resources
    	try(Connection conn = ConnectionFactory.getConnection()){
    		System.out.println("Connection successful");
    	} catch(SQLException e){
    		System.out.println("Database Connection failed");
    		e.printStackTrace(); 
    	}
    	
//    	Run Menu
//    	UserMenu user = new UserMenu(); 
//    	user.menuDisplay();
    	
//    	Set up Javalin Here
    	Javalin app = Javalin.create(
    				config -> {
    					config.enableCorsForAllOrigins(); //allows server to process Javascript
    				}
    			).start(3001); 
    	
    	//Register user
    	app.post("/auth/register", ac.registerUserHandler);
    	
    	//Login user
    	app.post("/auth/login", ac.loginUserHandler);
    	
    	//Get user by Id
//    	java app.get("/employee/{Id}", cec.getEmpbyIdHandler);
    	app.get("/employee/{Id}", uc.getUserByIdHandler);
    	
    	//Create Reimbursement
    	app.post("/reimbursements/create", rc.createReimbursementHandler); //Half working
    	
    	app.get("/reimbursements/get/{Status}", rc.getReimbByStatusHandler);
    	
    	app.get("/reimbursements/get/author/{authorId}", rc.getReimbByAuthorHandler);
    	
    	app.get("/employee/username/{username}", uc.getUserByUsernameHandler);
    	
    	app.put("/reimbursements/update/{rId}/{resolver}/{status}", rc.updateReimbursementHandler);
    	
    }
}
