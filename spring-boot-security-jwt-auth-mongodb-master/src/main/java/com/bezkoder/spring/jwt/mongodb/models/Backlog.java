package com.bezkoder.spring.jwt.mongodb.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "Backlog")

public class Backlog {
  @Id
  private String id;

  private String name;

  @DBRef
  private List<Sprint> sprints = new ArrayList();
  @DBRef
  private List<Tache> tache =new ArrayList();



public Backlog() {
	super();
	// TODO Auto-generated constructor stub
}


public List<Tache> getTache() {
	return tache;
}


public void setTache(List<Tache> tache) {
	this.tache = tache;
}







public Backlog(String name, List<Sprint> sprints, List<Tache> tache) {
	super();
	this.name = name;
	this.sprints = sprints;
	this.tache = tache;
}


public List<Sprint> getSprints() {
	return sprints;
}
public void setSprints(List<Sprint> sprints) {
	this.sprints = sprints;
}

public void addSprint(Sprint sprint) {
	this.sprints.add(sprint);
}


public String getName() {
	return name;
}


public void setName(String name) {
	this.name = name;
}

public void addTache(Tache tache) {
	this.tache.add(tache);
}

}
