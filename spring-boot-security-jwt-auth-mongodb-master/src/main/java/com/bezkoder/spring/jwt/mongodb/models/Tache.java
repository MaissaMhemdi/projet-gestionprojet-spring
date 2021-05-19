package com.bezkoder.spring.jwt.mongodb.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "taches")
public class Tache {
	@Id
	private String id;
	private String title;
	private String description;
	private Date datedebut;
	private Date datefin;
	
	
	public Tache() 
	{
		super();
	}
	


	public Tache(String title, String description, Date datedebut, Date datefin, List<Sprint> sprint) {
		super();
		this.title = title;
		this.description = description;
		this.datedebut = datedebut;
		this.datefin = datefin;
	}



	public Tache(String title, String description, Date datedebut, Date datefin) {
		super();
		this.title = title;
		this.description = description;
		this.datedebut = datedebut;
		this.datefin = datefin;
	}



	public Tache(String id, String title, String description) 
	{
		super();
		this.id = id;
		this.title = title;
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



	public String getId() 
	{
		return id;
	}
	public void setId(String id) 
	{
		this.id = id;
	}
	public String getTitle() 
	{
		return title;
	}
	public void setTitle(String title) 
	{
		this.title = title;
	}
	public String getDescription() 
	{
		return description;
	}
	public void setDescription(String description) 
	{
		this.description = description;
	}
	
	
}

