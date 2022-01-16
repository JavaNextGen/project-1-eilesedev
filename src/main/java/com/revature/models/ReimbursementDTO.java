package com.revature.models;

import java.sql.Timestamp;
import java.util.Date;
import java.util.TimeZone;

public class ReimbursementDTO {

	private int statusID;
	private int author;
	private double amount;
	private int typeID;
	private int resolver;
	
	
	public ReimbursementDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ReimbursementDTO(int statusID, int author, double amount, int typeID) {
		super();
		this.statusID = statusID;
		this.author = author;
		this.amount = amount;
		this.typeID = typeID;
	}

	public int getStatusID() {
		return statusID;
	}

	public void setStatusID(int statusID) {
		this.statusID = statusID;
	}

	public int getAuthor() {
		return author;
	}

	public void setAuthor(int author) {
		this.author = author;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getTypeID() {
		return typeID;
	}

	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}

	public int getResolver() {
		return resolver;
	}

	public void setResolver(int resolver) {
		this.resolver = resolver;
	}

	
	
	
}
