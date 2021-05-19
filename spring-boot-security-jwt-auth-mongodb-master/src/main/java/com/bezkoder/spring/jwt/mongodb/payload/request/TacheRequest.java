package com.bezkoder.spring.jwt.mongodb.payload.request;

import java.util.Date;

import javax.persistence.Id;

public class TacheRequest {
	
	private String title;
	private String description;
	private Date datedebut;
	private Date datefin;
	
	
	
	
	public TacheRequest() {
		super();
		// TODO Auto-generated constructor stub
	}




	public String getTitle() {
		return title;
	}




	public void setTitle(String title) {
		this.title = title;
	}




	public String getDescription() {
		return description;
	}




	public void setDescription(String description) {
		this.description = description;
	}




	public Date getDatedebut() {
		return datedebut;
	}




	public void setDatedebut(Date datedebut) {
		this.datedebut = datedebut;
	}




	public Date getDatefin() {
		return datefin;
	}




	public void setDatefin(Date datefin) {
		this.datefin = datefin;
	}




	public TacheRequest(String title, String description, Date datedebut, Date datefin) {
		super();
		this.title = title;
		this.description = description;
		this.datedebut = datedebut;
		this.datefin = datefin;
	}
	
}
