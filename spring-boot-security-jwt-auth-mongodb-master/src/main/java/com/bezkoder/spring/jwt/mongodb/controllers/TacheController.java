package com.bezkoder.spring.jwt.mongodb.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.jwt.mongodb.models.Backlog;
import com.bezkoder.spring.jwt.mongodb.models.Projet;
import com.bezkoder.spring.jwt.mongodb.models.Tache;
import com.bezkoder.spring.jwt.mongodb.payload.request.TacheRequest;
import com.bezkoder.spring.jwt.mongodb.repository.BacklogRepository;
import com.bezkoder.spring.jwt.mongodb.repository.TacheRepository;
import com.bezkoder.spring.jwt.mongodb.security.services.TacheServiceImp;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/api/taches")
public class TacheController 
{	
	@Autowired
	TacheRepository tacheRepository;

	@Autowired
	TacheServiceImp tacheservice;
	@Autowired
	BacklogRepository backlogRepository;
	@PostMapping("/tach/{backlogId}")
	@CrossOrigin(origins="http://localhost:4200")
	public ResponseEntity<Tache> createTache(@RequestBody TacheRequest task, @PathVariable String backlogId) throws Exception{
	    try {
	    	Tache tache = new Tache();
	    	tache.setDescription(task.getDescription());
	    	tache.setTitle(task.getTitle());
	    	tache.setDatedebut(task.getDatedebut());
	    	tache.setDatefin(task.getDatefin());
	       tacheRepository.insert(tache);
	       Optional<Backlog> backlogdb = backlogRepository.findById(backlogId);
	       Backlog backlog = backlogdb.get();
	       backlog.addTache(tache);
	       backlogRepository.save(backlog);
	      return new ResponseEntity<>(tache, HttpStatus.CREATED);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	throw new Exception(e.getMessage());	    }
	  }

	  @PutMapping("/{id}")
		@CrossOrigin(origins="http://localhost:4200")
	  public ResponseEntity<Tache> updateTache(@PathVariable("id") String id, @RequestBody Tache taches) {
	    Optional<Tache> TachesData =  tacheRepository.findById(id);

	    if (TachesData.isPresent()) {
	      Tache _tache = TachesData.get();
	      _tache.setTitle(taches.getTitle());
	      _tache.setDescription(taches.getDescription());
	      _tache.setDatedebut(taches.getDatedebut());
	      _tache.setDatefin(taches.getDatefin());
	     
	      
	      return new ResponseEntity<>( tacheRepository.save(_tache), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

	  @DeleteMapping("/{id}")
	 public void deleteTache(@PathVariable("id") String id) throws Exception{
		  try {	
		       tacheRepository.deleteById(id);
		    } catch (Exception e) {
		    	throw new Exception(e.getMessage());	    }
		  }
		@GetMapping("/{backlogId}")
		@CrossOrigin(origins="http://localhost:4200")
	    public ResponseEntity<List<Tache>> getTachesBacklogById(@PathVariable String backlogId) {
		List<Tache> taches = tacheservice.getTacheByBacklogId(backlogId);
		if(taches != null && !taches.isEmpty()) {
			return new ResponseEntity<>(taches , HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
}