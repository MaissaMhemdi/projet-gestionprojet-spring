package com.bezkoder.spring.jwt.mongodb.payload.request;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;

import com.bezkoder.spring.jwt.mongodb.models.Sprint;
import com.bezkoder.spring.jwt.mongodb.models.Tache;

public class BacklogRequest {
	
	  private String  Complexite;
	  private String estimation;
	  private String name;
	  private List<Sprint> sprints = new ArrayList();
	  private List<Tache> tache =new ArrayList();
	public BacklogRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BacklogRequest(String complexite, String estimation, String name, List<Sprint> sprints, List<Tache> tache) {
		super();
		Complexite = complexite;
		this.estimation = estimation;
		this.name = name;
		this.sprints = sprints;
		this.tache = tache;
	}
	public String getComplexite() {
		return Complexite;
	}
	public void setComplexite(String complexite) {
		Complexite = complexite;
	}
	public String getEstimation() {
		return estimation;
	}
	public void setEstimation(String estimation) {
		this.estimation = estimation;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Sprint> getSprints() {
		return sprints;
	}
	public void setSprints(List<Sprint> sprints) {
		this.sprints = sprints;
	}
	public List<Tache> getTache() {
		return tache;
	}
	public void setTache(List<Tache> tache) {
		this.tache = tache;
	}

}
