package com.bezkoder.spring.jwt.mongodb.security.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.bezkoder.spring.jwt.mongodb.models.Backlog;
import com.bezkoder.spring.jwt.mongodb.models.Projet;
import com.bezkoder.spring.jwt.mongodb.models.Sprint;
import com.bezkoder.spring.jwt.mongodb.repository.BacklogRepository;

public class SprintServiceImp {
	@Autowired
	private BacklogRepository  backlogRepository;

	public List<Sprint> getSprintByIdBacklog(String idbacklog) {
		Optional<Backlog> backlogfromdb = backlogRepository.findById(idbacklog);
	 	if(backlogfromdb.isPresent()) {
	 		Backlog backlog = backlogfromdb.get();
	 		
	 		return backlog.getSprints();
	 	}
		return null;
	}
}
