package com.revature.controllers;

import java.security.Principal;

import org.eclipse.jetty.server.Response;

import com.google.gson.Gson;
import com.revature.models.LoginDTO;
import com.revature.models.RegistrationDTO;
import com.revature.models.User;
import com.revature.services.AuthService;
import com.revature.services.UserService;

import io.javalin.http.Handler;

public class AuthController {

	AuthService auth = new AuthService();

	UserService us = new UserService();

	public Handler registerUserHandler = (ctx) -> {

		if (ctx.req.getSession(true) != null) {
			String body = ctx.body();

			Gson gson = new Gson();

			RegistrationDTO rdto = gson.fromJson(body, RegistrationDTO.class);

//			String username, String password, String fName, String lName, String email, int roleId

			User fromDTO = new User(rdto.getUsername(), rdto.getPassword(), rdto.getfName(), rdto.getlName(),
					rdto.getEmail(), rdto.getRole());

			auth.register(fromDTO);

			ctx.result("User Successfully Registered!");

			ctx.status(201);

		} else {
			ctx.result("Registration Unsuccessful");
			ctx.status(406);

		}
	};

	public Handler loginUserHandler = (ctx) -> {

		String body = ctx.body();

		Gson gson = new Gson();

		// Create loginDTO and place here
		LoginDTO ldto = gson.fromJson(body, LoginDTO.class);

		if (auth.login(ldto.getUsername(), ldto.getPassword()) != null) {

			ctx.req.getSession();

			// Is there a way to pass back permissions?

			ctx.status(202);

//			ctx.response(auth.login(ldto.getUsername(), ldto.getPassword()));

			ctx.result("Login Successful!");

		} else {

			ctx.status(401);

			ctx.result("Login Failed!");
		}

	};

}
