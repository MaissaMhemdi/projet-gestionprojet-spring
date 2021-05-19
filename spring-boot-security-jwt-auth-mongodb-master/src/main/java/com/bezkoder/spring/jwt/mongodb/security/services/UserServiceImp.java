package com.bezkoder.spring.jwt.mongodb.security.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.jwt.mongodb.models.Backlog;
import com.bezkoder.spring.jwt.mongodb.models.Projet;
import com.bezkoder.spring.jwt.mongodb.models.Tache;
import com.bezkoder.spring.jwt.mongodb.models.User;
import com.bezkoder.spring.jwt.mongodb.repository.ProjetRepository;
@Service
public class UserServiceImp {

	@Autowired
	private ProjetRepository  projetRepository;

	public List<User> getUserByProjetId(String projetId) {
	 	Optional<Projet> projetfromdb = projetRepository.findById(projetId);
	 	if(projetfromdb.isPresent()) {
	 		Projet projet = projetfromdb.get();
	 		
	 		return projet.getUsers();
	 		
	 	}
	 	return null;
	}


}

