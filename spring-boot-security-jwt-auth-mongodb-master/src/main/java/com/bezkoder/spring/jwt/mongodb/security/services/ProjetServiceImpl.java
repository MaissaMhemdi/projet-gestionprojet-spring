package com.bezkoder.spring.jwt.mongodb.security.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.jwt.mongodb.models.Projet;
import com.bezkoder.spring.jwt.mongodb.models.User;
import com.bezkoder.spring.jwt.mongodb.repository.ProjetRepository;
@Service
public class ProjetServiceImpl  {
	@Autowired
	private ProjetRepository projetrepository;
public List <Projet> getProjetsByUser(User user){
	List<Projet> projetlist=new ArrayList();
	List<User> users= new ArrayList();
	List<Projet> projets = projetrepository.findAll();
	if(!projets.isEmpty()) {
	for(Projet projet:projets) {
		if(projet.getUsers() !=null && !projet.getUsers().isEmpty()) {
	 users = projet.getUsers().stream().filter(userr-> userr.getEmail().equals(user.getEmail())).collect(Collectors.toList());
		if(!users.isEmpty()) {
			projetlist.add(projet);
		}	
	}}}
	return projetlist;
}
	
	

}
