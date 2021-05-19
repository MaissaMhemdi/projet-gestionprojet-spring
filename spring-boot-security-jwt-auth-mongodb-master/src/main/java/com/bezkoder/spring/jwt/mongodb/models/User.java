package com.bezkoder.spring.jwt.mongodb.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
  @Id
  private String id;

  @NotBlank
  @Size(max = 20)
  private String username;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;
  
  private String image;

  @NotBlank
  @Size(max = 120)
  private String password;
  @DBRef
  private Set<Role> roles = new HashSet<>();

  @DBRef
  private List<Projet> projets_ids;
  public User(@NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 50) @Email String email, String image,
		@NotBlank @Size(max = 120) String password, List<Projet> projets_ids, Set<Role> roles) {
	super();
	this.username = username;
	this.email = email;
	this.image = image;
	this.password = password;
	this.projets_ids = projets_ids;
	this.roles = roles;
}

public List<Projet> getProjets_ids() {
	return projets_ids;
}

public void setProjets_ids(List<Projet> projets_ids) {
	this.projets_ids = projets_ids;
}


  public User() {
  }

  public User(String username, String email, String password, String image) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.image=image;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getImage() {
	return image;
}

public void setImage(String image) {
	this.image = image;
}

public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }
}
