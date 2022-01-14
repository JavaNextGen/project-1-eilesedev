package com.revature.controllers;

import java.util.List;

import com.google.gson.Gson;
import com.revature.models.Reimbursement;
import com.revature.models.Status;
import com.revature.models.User;
import com.revature.services.AuthService;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;

import io.javalin.http.Handler;

public class ReimbursementController {

	AuthService auth = new AuthService();
	UserService us = new UserService();
	ReimbursementService rs = new ReimbursementService();

	public Handler createReimbursementHandler = (ctx) -> {

		String body = ctx.body();

		Gson gson = new Gson();

		System.out.println(body);

		Reimbursement r = gson.fromJson(body, Reimbursement.class);

		if (r != null) {

			ctx.req.getSession();

			System.out.println(r);

			rs.create(r);

			ctx.result("Reimbursement Successfully Added!");

			ctx.status(201);

		} else {
			ctx.result("Reimbursement Could Not be added");
			ctx.status(406);

		}
	};

	public Handler getAllReimbursementsHandler = (ctx) -> {
		if (ctx.req.getSession(true) != null) {
			
			String status = ctx.pathParam("Status");
			Status s = Status.PENDING; 
			
			if(status.equalsIgnoreCase("pending"))
				s = Status.PENDING; 
			else if(status.equalsIgnoreCase("approved"))
				s = Status.APPROVED; 
			else if(status.equalsIgnoreCase("denied"))
				s = Status.DENIED;
			
			
			//How do I pass in an enum into the path param of the ctx object?

			List<Reimbursement> allReimbursements = rs.getReimbursementsByStatus(s);

			Gson gson = new Gson();

			String JSONReimbursements = gson.toJson(allReimbursements);


			ctx.result(JSONReimbursements);
			ctx.status(200);

		} else {
			ctx.result("No reimbursements --- error...");
			ctx.status(401);
		}
	};
	
	public Handler updateReimbursementHandler = (ctx) -> {
		
		if (ctx.req.getSession(true) != null) {
			
            String body = ctx.body();

            Gson gson = new Gson();

            Reimbursement updated = gson.fromJson(body, Reimbursement.class);
            Status status = gson.fromJson(body, Status.class);
            User resolver = gson.fromJson(body, User.class);

            rs.process(updated, status, resolver);

            ctx.result("Reimbursement successfully updated!");
            ctx.status(200);

        } else {
            ctx.result("Reimbursement could not be updated");
            ctx.status(404);
        }
	};

}
