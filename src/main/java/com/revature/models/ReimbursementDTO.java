package com.revature.models;

import java.sql.Timestamp;
import java.util.Date;
import java.util.TimeZone;

public class ReimbursementDTO {

	private int statusID;
	private int author;
	private Timestamp submitted;
	private double amount;
	private int typeID;
	
	public ReimbursementDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReimbursementDTO(int statusID, int author, Timestamp submitted, double amount, int typeID) {
		super();
		this.statusID = statusID;
		this.author = author;
		this.submitted = submitted;
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

	public Timestamp getSubmitted() {
		return submitted;
	}

	public void setSubmitted(Timestamp submitted) {
		this.submitted = submitted;
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

	@Override
	public String toString() {
		return "ReimbursementDTO [statusID=" + statusID + ", author=" + author + ", submitted=" + submitted
				+ ", amount=" + amount + ", typeID=" + typeID + "]";
	}

}
