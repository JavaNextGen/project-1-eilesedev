package com.revature.controllers;

import com.google.gson.Gson;
import com.revature.models.Reimbursement;
import com.revature.services.AuthService;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;

import io.javalin.http.Handler;

public class ReimbursementController {

	AuthService auth = new AuthService();
	UserService us = new UserService();
	ReimbursementService rs = new ReimbursementService(); 

	public Handler createReimbursementHandler = (ctx) -> {

		if (ctx.req.getSession(true) != null) {
			
			System.out.println(ctx.req.getSession(true)); 
			
			String body = ctx.body();

			Gson gson = new Gson();
			
			System.out.println(body);

			Reimbursement r = gson.fromJson(body, Reimbursement.class);

//			public Reimbursement(int statusId, User author, Timestamp submitted, double amount, int typeId) 
			
//			Reimbursement r = new Reimbursement(rdto.getStatusID(), rdto.getAuthor(), rdto.getSubmitted(), rdto.getAmount(), rdto.getTypeID()); 
			
//			System.out.println(r);

			rs.create(r); 

			ctx.result("Reimbursement Successfully Added!");

			ctx.status(201);

		} else {
			ctx.result("Reimbursement Could Not be added");
			ctx.status(406);

		}
	};

}
