package com.revature.controllers;

import com.google.gson.Gson;
import com.revature.models.RegistrationDTO;
import com.revature.models.User;
import com.revature.services.AuthService;
import com.revature.services.UserService;

import io.javalin.http.Handler;

public class AuthController {
	
	AuthService auth = new AuthService(); 
	
	UserService us = new UserService(); 

	public Handler registerUserHandler = (ctx) -> {
		
		if(ctx.req.getSession() != null) {
			String body = ctx.body(); 
			
			Gson gson = new Gson(); 
			
			RegistrationDTO rdto = gson.fromJson(body, RegistrationDTO.class);  
			
//			String username, String password, String fName, String lName, String email, int roleId
			
			User fromDTO = new User(rdto.getUsername(), rdto.getPassword(), rdto.getfName(), rdto.getlName(), rdto.getEmail(), rdto.getRole()); 
			
			auth.register(fromDTO); 
			
			ctx.result("User Successfully Registered!");
			
			ctx.status(201); 
			
			
		}else {
			ctx.result("Registration Unsuccessful");
			ctx.status(406); 
			
		}
	};

}
