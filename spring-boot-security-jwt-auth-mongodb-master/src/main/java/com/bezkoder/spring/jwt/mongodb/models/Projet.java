package com.bezkoder.spring.jwt.mongodb.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.OneToMany;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(collection = "projets")
public class Projet {

	@Id
	private String id;
	private String title;
	private String description;
    private Date dateDebut;
    private Date dateFin;
	@DBRef
	private List<User> users = new ArrayList();
	@DBRef
	private Backlog backlog;
	

	public Projet() {

	}

	
	public Projet(String id, String title, String description, Date dateDebut, Date dateFin, List<User> users,
			Backlog backlog) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.users = users;
		this.backlog = backlog;
	}


	public Projet(String title, String description, Date dateDebut, Date dateFin) {
		super();
		this.title = title;
		this.description = description;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}

	public Projet(String title, String description, Date dateDebut, Date dateFin, List<User> users) {
		super();
		this.title = title;
		this.description = description;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.users = users;
	}
	public Backlog getBacklog() {
		return backlog;
	}

	public void setBacklog(Backlog backlog) {
		this.backlog = backlog;
	}
	

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public String getId() {
		return id;
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

	public Projet(String id, String title, String description, List<User> user) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.users = user;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public void addUser(User user) {
		this.users.add(user);
	}
	

	@Override
	public String toString() {
		return "Projet [id=" + id + ", title=" + title + ", description=" + description + ", dateDebut=" + dateDebut
				+ ", dateFin=" + dateFin + ", users=" + users + "]";
	}
	
	
}
