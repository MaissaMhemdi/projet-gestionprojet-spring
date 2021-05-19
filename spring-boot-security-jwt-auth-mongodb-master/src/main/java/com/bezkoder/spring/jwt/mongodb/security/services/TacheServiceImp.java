package com.bezkoder.spring.jwt.mongodb.security.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.jwt.mongodb.models.Backlog;
import com.bezkoder.spring.jwt.mongodb.models.Projet;
import com.bezkoder.spring.jwt.mongodb.models.Tache;
import com.bezkoder.spring.jwt.mongodb.repository.BacklogRepository;
import com.bezkoder.spring.jwt.mongodb.repository.ProjetRepository;
@Service
public class TacheServiceImp {
	@Autowired
	  ProjetRepository projetRepository;
	@Autowired
	private BacklogRepository  backlogRepository;

	public List<Tache> getTacheByBacklogId(String backlogId) {
	 	Optional<Backlog> backlogfromdb = backlogRepository.findById(backlogId);
	 	if(backlogfromdb.isPresent()) {
	 		Backlog backlog = backlogfromdb.get();
	 		
	 		return backlog.getTache();
	 		
	 	}
	 	return null;
	}

	public Backlog getBacklogByIdProjet(String Idprojet) {
		Optional<Projet> projetfromdb = projetRepository.findById(Idprojet);
	 	if(projetfromdb.isPresent()) {
	 		Projet projet = projetfromdb.get();
	 		
	 		return projet.getBacklog();
	 	}
		return null;
	}

	


}
