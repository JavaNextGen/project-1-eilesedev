package com.revature.controllers;

import com.google.gson.Gson;
import com.revature.models.User;
import com.revature.services.AuthService;

import io.javalin.http.Handler;

public class UserController {
	
	//Create new User object
	AuthService auth = new AuthService(); 
	
	//CAN context object throw exceptions?
	
	//TEST -- Get user record from the database
	public Handler getUserTestHandler = (ctx) -> {
		if(ctx.req.getSession() != null) {
			String uId = ctx.pathParam("Id"); 
			
			User thisU = auth.
		}else {
			
		}
	};
	
	//Create a Handler to Register User
	public Handler registerUserHandler = (ctx) -> {
		if(ctx.req.getSession() != null) {
			
			String body = ctx.body(); 
			
			Gson gson = new Gson(); 
			User create = gson.fromJson(body, User.class);
			
			auth.register(create); 
			
			System.out.println("Successfully added employee!");
			ctx.status(201); 
			
			
			
		}else {
			System.out.println("Failed to insert employee");
			ctx.status(404); 
		}
	};
	
	//Create a Handler for Employee Login
	
	//Create a Handler for Employees to view past tickets
	
	//Create a Handler for Employees to add reimbursement requests
	
	//Create a Handler for Managers to login 
	
	//and view reimbursement requests
	//approve or deny reimbursements
	//filter request by status
	
	
	
	

}
