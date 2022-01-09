package com.revature.controllers;

import com.google.gson.Gson;
import com.revature.models.User;
import com.revature.services.AuthService;

import io.javalin.http.Handler;

public class UserController {
	
	User user = new User(); 
	
	AuthService auth = new AuthService(); 

	// CAN context object throw exceptions?

	// TEST -- Get user record from the database
	public Handler getUserByUsername = (ctx) -> {
		// 1. Enter username in pathParam
		// 2. Return user record based on username
		// 3. Return empty optional if user doesn't exits
		// STRETCH -- Redirect user to registration path and have them register
		if (ctx.req.getSession() != null) {

			String uId = ctx.pathParam("username");

//			User u = 

//			User thisU = auth.
		} else {

		}
	};
	// Handler to register new user

//	public Handler registerUserHandler = (ctx) -> {
//
//		if (ctx.req.getSession(true) != null) {
//			String body = ctx.body();
//
//			Gson gson = new Gson();
//
//			User JSONRegisteredUser = gson.fromJson(body, User.class);
//
//			auth.register(JSONRegisteredUser);
//
//			ctx.result("User Successfully Registered!");
//
//			ctx.status(201);
//
//		} else {
//			ctx.result("Registration Unsuccessful");
//			ctx.status(406);
//
//		}
//	};

	// Create a Handler for Employee Login

	// Create a Handler for Employees to view past tickets

	// Create a Handler for Employees to add reimbursement requests

	// Create a Handler for Managers to login

	// and view reimbursement requests
	// approve or deny reimbursements
	// filter request by status

}
