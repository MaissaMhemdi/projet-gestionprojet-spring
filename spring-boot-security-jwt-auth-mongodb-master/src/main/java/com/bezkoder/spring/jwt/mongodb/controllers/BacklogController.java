package com.bezkoder.spring.jwt.mongodb.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.jwt.mongodb.models.Backlog;
import com.bezkoder.spring.jwt.mongodb.models.Projet;
import com.bezkoder.spring.jwt.mongodb.models.Sprint;
import com.bezkoder.spring.jwt.mongodb.payload.request.BacklogRequest;
import com.bezkoder.spring.jwt.mongodb.payload.request.SprintRequest;
import com.bezkoder.spring.jwt.mongodb.payload.response.MessageResponse;
import com.bezkoder.spring.jwt.mongodb.repository.BacklogRepository;
import com.bezkoder.spring.jwt.mongodb.repository.ProjetRepository;
@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/api/backlogs")
public class BacklogController {
	
	@Autowired
	private ProjetRepository projetRepository;
	@Autowired
	private BacklogRepository repository;
	@PostMapping("/backlog/{idProject}")
	@CrossOrigin(origins="http://localhost:4200")
	public String createBacklog(@RequestBody BacklogRequest backlogRequested, @PathVariable String idProject) {
		Optional<Projet> projet = projetRepository.findById(idProject);
		if(projet.isPresent()) {
        Backlog backlog = projet.get().getBacklog();
		/*if(backlogRequested != null) {
			backlog.setComplexite(backlogRequested.getComplexite());
			backlog.setEstimation(backlogRequested.getEstimation());
			
		}*/
		repository.save(backlog);
		return "Order placed successfully...";
	}
		return  "Not successfully!";
		
	}
	
	
	@GetMapping("/{id}")
	@CrossOrigin(origins="http://localhost:4200")
    public ResponseEntity<Backlog> getBacklogById(@PathVariable String id) {
		Optional<Backlog> backlog =  repository.findById(id);

	    if (backlog.isPresent()) {
	      return new ResponseEntity<>(backlog.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

}
