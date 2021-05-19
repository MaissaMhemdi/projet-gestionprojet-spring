package com.bezkoder.spring.jwt.mongodb.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
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
import com.bezkoder.spring.jwt.mongodb.models.Sprint;
import com.bezkoder.spring.jwt.mongodb.models.Tache;
import com.bezkoder.spring.jwt.mongodb.models.User;
import com.bezkoder.spring.jwt.mongodb.payload.request.SignupRequest;
import com.bezkoder.spring.jwt.mongodb.payload.request.SprintRequest;
import com.bezkoder.spring.jwt.mongodb.payload.request.TacheRequest;
import com.bezkoder.spring.jwt.mongodb.payload.response.MessageResponse;
import com.bezkoder.spring.jwt.mongodb.repository.BacklogRepository;
import com.bezkoder.spring.jwt.mongodb.repository.SprintRepository;
import com.bezkoder.spring.jwt.mongodb.repository.TacheRepository;
import com.bezkoder.spring.jwt.mongodb.security.services.SprintServiceImp;



@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/api/sprints")
public class SprintController {


	@Autowired
	SprintRepository sprintRepository;

	@Autowired
	SprintServiceImp sprintservice;
	
	@Autowired
	BacklogRepository backlogRepository;
	@Autowired
	TacheRepository tacheRepository;
	
	@PostMapping("/sprint/{idBacklog}")
	@CrossOrigin(origins="http://localhost:4200")
	public ResponseEntity<?> createSprint(@RequestBody SprintRequest sprintRequest,@PathVariable String idBacklog){
		Optional<Backlog> backlog = backlogRepository.findById(idBacklog);
		if(backlog.isPresent()) {
			
	    	if (sprintRepository.existsByNomSprint(sprintRequest.getNomSprint())) {
				return ResponseEntity
						.badRequest()
						.body(new MessageResponse("Error: NomSprint existe!"));
	    	}
	    
	    	Sprint sprint = new Sprint( sprintRequest.getNomSprint(),sprintRequest.getDescriptionSprint() ,sprintRequest.getComplexite() ,sprintRequest.getEstimation(),sprintRequest.getDatedebut(), sprintRequest.getDatefin());
	    	for(String idTache : sprintRequest.getTaches()) {
	    		Optional<Tache> tache = tacheRepository.findById(idTache);
	    		if(tache.isPresent()) {
	    		sprint.addTache(tache.get());
	    		}
	    	}
	    	sprintRepository.save(sprint);
	    	Backlog backlogentity = backlog.get();
	    	backlogentity.addSprint(sprint);
	    	backlogRepository.save(backlogentity);
	    	return ResponseEntity.ok(new MessageResponse("successfully!"));
		}
		else {
			return ResponseEntity.ok(new MessageResponse("Not successfully!"));
		}
	  }

	  @PutMapping("/{id}")
		@CrossOrigin(origins="http://localhost:4200")
	  public ResponseEntity<Sprint> updateSprint(@PathVariable("id") String id, @RequestBody Sprint sprints) {
	    Optional<Sprint> SprintsData =  sprintRepository.findById(id);

	    if (SprintsData.isPresent()) {
	     Sprint  _sprint = SprintsData.get();
	      _sprint.setNomSprint(sprints.getNomSprint());
	      _sprint.setDescriptionSprint(sprints.getDescriptionSprint());
	      _sprint.setComplexite(sprints.getComplexite());
	      _sprint.setEstimation(sprints.getEstimation());
	      _sprint.setDatedebut(sprints.getDatedebut());
	      _sprint.setDatefin(sprints.getDatefin());
	      _sprint.setTache(sprints.getTache());
	      
	      return new ResponseEntity<>( sprintRepository.save(_sprint), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

	  @DeleteMapping("/{id}")
	 public void deleteSprint(@PathVariable("id") String id) throws Exception{
		  try {	
		       sprintRepository.deleteById(id);
		    } catch (Exception e) {
		    	throw new Exception(e.getMessage());	    }
		  }
		@GetMapping("/{idbacklog}")
		@CrossOrigin(origins="http://localhost:4200")
	    public ResponseEntity<List<Sprint>> getSprintByIdBacklog(@PathVariable String idbacklog) {
			List<Sprint> sprint = sprintservice.getSprintByIdBacklog(idbacklog);
		if(sprint != null && !sprint.isEmpty() ) {
			return new ResponseEntity<>(sprint , HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}

	
}

