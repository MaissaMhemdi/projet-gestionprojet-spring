 package com.bezkoder.spring.jwt.mongodb.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "Sprints")

public class Sprint {
@Id
private String id;
private String nomSprint;
private String  complexite;
private String estimation;
private String descriptionSprint;

private Date datedebut;
private Date datefin ;
@DBRef
private List<Tache> tache =new ArrayList();



public Sprint() {
	super();
	// TODO Auto-generated constructor stub
}



public Sprint(String nomSprint, String descriptionSprint, Date datedebut, Date datefin,
		List<Tache> tache) {
	super();
	this.nomSprint = nomSprint;
	this.descriptionSprint = descriptionSprint;
	this.datedebut = datedebut;
	this.datefin = datefin;
	this.tache = tache;
}



public Sprint(String id, String nomSprint, String descriptionSprint,  Date datedebut, Date datefin,
		List<Tache> tache) {
	super();
	this.id = id;
	this.nomSprint = nomSprint;
	this.descriptionSprint = descriptionSprint;
	
	this.datedebut = datedebut;
	this.datefin = datefin;
	this.tache = tache;
}



public Sprint(String nomSprint, String complexite, String estimation, String descriptionSprint, Date datedebut,
		Date datefin) {
	super();
	this.nomSprint = nomSprint;
	this.complexite = complexite;
	this.estimation = estimation;
	this.descriptionSprint = descriptionSprint;
	this.datedebut = datedebut;
	this.datefin = datefin;
}



public Sprint(String nomSprint, String complexite, String estimation, String descriptionSprint, Date datedebut,
		Date datefin, List<Tache> tache) {
	super();
	this.nomSprint = nomSprint;
	this.complexite = complexite;
	this.estimation = estimation;
	this.descriptionSprint = descriptionSprint;
	this.datedebut = datedebut;
	this.datefin = datefin;
	this.tache = tache;
}



public Sprint(String nomSprint, String descriptionSprint, Date datedebut, Date datefin) {
	super();
	this.nomSprint = nomSprint;
	this.descriptionSprint = descriptionSprint;
	
	this.datedebut = datedebut;
	this.datefin = datefin;
}
public String getNomSprint() {
	return nomSprint;
}
public void setNomSprint(String nomSprint) {
	this.nomSprint = nomSprint;
}
public String getDescriptionSprint() {
	return descriptionSprint;
}
public void setDescriptionSprint(String descriptionSprint) {
	this.descriptionSprint = descriptionSprint;
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

public List<Tache> getTache() {
	return tache;
}



public void setTache(List<Tache> tache) {
	this.tache = tache;
}
public void addTache(Tache tache) {
	this.tache.add(tache);
}
 

public String getComplexite() {
	return complexite;
}



public void setComplexite(String complexite) {
	complexite = complexite;
}



public String getEstimation() {
	return estimation;
}



public void setEstimation(String estimation) {
	this.estimation = estimation;
}



@Override
public String toString() {
	return "Sprint [nomSprint=" + nomSprint + ", descriptionSprint=" + descriptionSprint 
			+ ", datedebut=" + datedebut + ", datefin=" + datefin + "]";
}
}
