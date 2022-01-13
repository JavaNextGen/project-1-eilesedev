package com.revature.controllers;

import java.util.Optional;

import com.google.gson.Gson;
import com.revature.models.User;
import com.revature.services.AuthService;
import com.revature.services.UserService;

import io.javalin.http.Handler;

public class UserController {

	User user = new User();

	AuthService auth = new AuthService();
	UserService us = new UserService();

	public Handler getUserByIdHandler = (ctx) -> {
		if (ctx.req.getSession(true) != null) {

			String uId = ctx.pathParam("Id");

			User userById = us.getUserById(Integer.parseInt(uId));

			Gson gson = new Gson();

			String JSONEmp = gson.toJson(userById);

			ctx.result(JSONEmp);

			ctx.status(200);

		}

		else {
			ctx.result("Failed to retrieve employees!");
			ctx.status(404);
		}
	};

	public Handler getUserByUsernameHandler = (ctx) -> {
		if (ctx.req.getSession(true) != null) {

			String username = ctx.pathParam("username");

			Optional<User> userByUsername = us.getByUsername(username);

			Gson gson = new Gson();

			String JSONUser = gson.toJson(userByUsername);

			ctx.result(JSONUser);

			ctx.status(200);

		}

		else {
			ctx.result("Failed to retrieve employees!");
			ctx.status(404);
		}
	};

	// Create a Handler for Employee Login

	// Create a Handler for Employees to view past tickets

	// Create a Handler for Employees to add reimbursement requests

	// Create a Handler for Managers to login

	// and view reimbursement requests
	// approve or deny reimbursements
	// filter request by status

}
