package com.revature.controllers;

import java.util.List;
import java.util.Optional;

import com.google.gson.Gson;
import com.revature.models.RegistrationDTO;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementDTO;
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
	ReimbursementDTO rmb = new ReimbursementDTO(); 
	
//	public ReimbursementDTO(int statusID, int author, double amount, int typeID, int resolver) 
	
	public Handler createReimbursementHandler = (ctx) -> {
		if(ctx.req.getSession(true) != null) {
			
			String body = ctx.body(); 
			
			Gson gson = new Gson(); 
			
			ReimbursementDTO rmbDTO = gson.fromJson(body, ReimbursementDTO.class); 
//			System.out.println(rmbDTO.toString()); //This hits
			
			Reimbursement rmbdt = new Reimbursement(rmbDTO.getStatusID(), rmbDTO.getAuthor(), rmbDTO.getAmount(), rmbDTO.getTypeID()); 
			
			System.out.println(rmbdt.toString());
			
			rs.create(rmbdt);
			
			ctx.result(rmbDTO + "Reimbursement Successfully Created!");
			
			ctx.status(201);	
		} else {
			ctx.result("Registration Unsuccessful");
			ctx.status(406);

		}
	};

/*public Handler registerUserHandler = (ctx) -> {

		if (ctx.req.getSession(true) != null) {
			String body = ctx.body();

			Gson gson = new Gson();

			RegistrationDTO rdto = gson.fromJson(body, RegistrationDTO.class);
			System.out.println(rdto);

//			String username, String password, String fName, String lName, String email, int roleId

			User fromDTO = new User(rdto.getUsername(), rdto.getPassword(), rdto.getfName(), rdto.getlName(),
					rdto.getEmail(), rdto.getRole());
			
			System.out.println(rdto.getfName());

			auth.register(fromDTO);

			ctx.result("User Successfully Registered!");

			ctx.status(201);

		} else {
			ctx.result("Registration Unsuccessful");
			ctx.status(406);

		}
	};
*/

	public Handler getReimbByStatusHandler = (ctx) -> {
		if (ctx.req.getSession(true) != null) {

			String status = ctx.pathParam("Status");
			Status s = Status.PENDING;

			if (status.equalsIgnoreCase("pending"))
				s = Status.PENDING;
			else if (status.equalsIgnoreCase("approved"))
				s = Status.APPROVED;
			else if (status.equalsIgnoreCase("denied"))
				s = Status.DENIED;

			// How do I pass in an enum into the path param of the ctx object?

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

	public Handler getReimbByAuthorHandler = (ctx) -> {
		if (ctx.req.getSession(true) != null) {

			String author = ctx.pathParam("authorId");

			User userAuthor = us.getUserById(Integer.parseInt(author));

			List<Reimbursement> allReimbursementsByAuthor = rs.getReimbursementByAuthor(userAuthor);

			Gson gson = new Gson();

			String JSONByAuthor = gson.toJson(allReimbursementsByAuthor);

			ctx.result(JSONByAuthor);
			ctx.status(200);

		} else {
			ctx.result("Can't find any reimbursements by this user");
			ctx.status(401);
		}
	};

	public Handler updateReimbursementHandler = (ctx) -> {

		if (ctx.req.getSession(true) != null) {
						
		

            Gson gson = new Gson();
            
			String body = ctx.body();
			
//			Reimbursement process(Reimbursement unprocessedReimbursement, Status finalStatus, User resolver)
			
			
	        Reimbursement updated = gson.fromJson(body, Reimbursement.class);

			rs.process(updated, updated.getStatus(), updated.getResolver());
			ctx.result("Reimbursement successfully updated!");
			ctx.status(200);

		} else {
			ctx.result("Reimbursement could not be updated");
			ctx.status(404);
		}
	};
	
	public Handler getAllReimb = (ctx) -> {
		if (ctx.req.getSession(true) != null) {

			// How do I pass in an enum into the path param of the ctx object?

			List<Reimbursement> allReimbursements = rs.getAllReimbursements();

			Gson gson = new Gson();

			String JSONReimbursements = gson.toJson(allReimbursements);

			ctx.result(JSONReimbursements);
			ctx.status(200);

		} else {
			ctx.result("No reimbursements could be found");
			ctx.status(400);
		}
	};

}
