package com.bezkoder.spring.jwt.mongodb.payload.request;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bezkoder.spring.jwt.mongodb.models.Tache;

public class SprintRequest {
	private String id;

	private String nomSprint;
	private String descriptionSprint;
	  private String  complexite;
	  private String estimation;
	private Date datedebut;
	private Date datefin ;
	private List<String> taches ;
	
	public SprintRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public SprintRequest(String nomSprint, String descriptionSprint, String complexite, Date datedebut, Date datefin) {
		super();
		this.nomSprint = nomSprint;
		this.descriptionSprint = descriptionSprint;
		this.complexite = complexite;
		this.datedebut = datedebut;
		this.datefin = datefin;
		
	}

	public SprintRequest(String nomSprint, String descriptionSprint, String complexite, String estimation,
			Date datedebut, Date datefin, List<String> taches) {
		super();
		this.nomSprint = nomSprint;
		this.descriptionSprint = descriptionSprint;
		this.complexite = complexite;
		this.estimation = estimation;
		this.datedebut = datedebut;
		this.datefin = datefin;
		this.taches = taches;
	}

	public String getEstimation() {
		return estimation;
	}

	public void setEstimation(String estimation) {
		this.estimation = estimation;
	}

	public List<String> getTaches() {
		return taches;
	}
	public void setTaches(List<String> taches) {
		this.taches = taches;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getComplexite() {
		return complexite;
	}
	public void setComplexite(String complexite) {
		complexite = complexite;
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
public void addTache(String tache) {
	this.taches.add(tache);
}
}
