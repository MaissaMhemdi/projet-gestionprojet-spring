package com.bezkoder.spring.jwt.mongodb.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
import com.bezkoder.spring.jwt.mongodb.models.ERole;
import com.bezkoder.spring.jwt.mongodb.models.Projet;
import com.bezkoder.spring.jwt.mongodb.models.Role;
import com.bezkoder.spring.jwt.mongodb.models.Tache;
import com.bezkoder.spring.jwt.mongodb.models.User;
import com.bezkoder.spring.jwt.mongodb.repository.BacklogRepository;
import com.bezkoder.spring.jwt.mongodb.repository.ProjetRepository;
import com.bezkoder.spring.jwt.mongodb.repository.RoleRepository;
import com.bezkoder.spring.jwt.mongodb.repository.UserRepository;
import com.bezkoder.spring.jwt.mongodb.security.services.ProjetServiceImpl;
import com.bezkoder.spring.jwt.mongodb.security.services.TacheServiceImp;
import com.bezkoder.spring.jwt.mongodb.security.services.UserServiceImp;


@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/api/projets")
public class ProjetController {
  
	@Autowired
	TacheServiceImp tacheservice;
	@Autowired
	  ProjetRepository projetRepository;

	@Autowired
	UserServiceImp userService;
	@Autowired
	  UserRepository userRepository;

	@Autowired
	  BacklogRepository backlogRepository;
	@Autowired
	  RoleRepository roleRepository;

	@Autowired
	  ProjetServiceImpl projetImp;
	 /* @GetMapping("/")	
	  @CrossOrigin(origins="http://localhost:4200")
	 public ResponseEntity<List<Projet>> getAllTutorials(@RequestParam(required = false) String title) {
	    try {
	      List<Projet> tutorials = new ArrayList<Projet>();

	      if (title == null)
	    	  projetRepository.findAll().forEach(tutorials::add);
	      else
	    	  projetRepository.findByTitleContaining(title).forEach(tutorials::add);

	      if (tutorials.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }

	      return new ResponseEntity<>(tutorials, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	  @GetMapping("/{id}")
		@CrossOrigin(origins="http://localhost:4200")
	  public ResponseEntity<Projet> getTutorialById(@PathVariable("id") String id) {
	    Optional<Projet> tutorialData =  projetRepository.findById(id);

	    if (tutorialData.isPresent()) {
	      return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }*/

	  @PostMapping("/pro")
		@CrossOrigin(origins="http://localhost:4200")
	  public ResponseEntity<Projet> createProjet(@RequestBody Projet tutorial) throws Exception{
	    try {
	    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			 String username;
			if (principal instanceof UserDetails) {
			 username = ((UserDetails)principal).getUsername();
			} else {
			 username = principal.toString();
			}
			Optional<User> user = userRepository.findByUsername(username);
	    	Projet project = new Projet();
	    	project.setDescription(tutorial.getDescription());
	    	project.setTitle(tutorial.getTitle());
	    	project.setDateDebut(tutorial.getDateDebut());
	    	project.setDateFin(tutorial.getDateFin());
	    	if(user.isPresent()) {
	    	project.addUser(user.get());
	    	}
	    	Backlog backlog =new Backlog();
	    	backlog.setName(project.getTitle());
	    	backlogRepository.insert(backlog);
	    	project.setBacklog(backlog);
	       projetRepository.insert(project);
	      return new ResponseEntity<>(project, HttpStatus.CREATED);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	throw new Exception(e.getMessage());	    }
	  }

	  @PutMapping("/{id}")
		@CrossOrigin(origins="http://localhost:4200")
	  public ResponseEntity<Projet> updateTutorial(@PathVariable("id") String id, @RequestBody Projet tutorial) {
	    Optional<Projet> tutorialData =  projetRepository.findById(id);

	    if (tutorialData.isPresent()) {
	      Projet _tutorial = tutorialData.get();
	      _tutorial.setTitle(tutorial.getTitle());
	      _tutorial.setDescription(tutorial.getDescription());
	      _tutorial.setDateDebut(tutorial.getDateDebut());
	      _tutorial.setDateFin(tutorial.getDateFin());
	     
	      
	      return new ResponseEntity<>( projetRepository.save(_tutorial), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

	  @DeleteMapping("/{id}")
		@CrossOrigin(origins="http://localhost:4200")
	  public void deleteTutorial(@PathVariable("id") String id) throws Exception{
	    try {	
	      projetRepository.deleteById(id);
	    } catch (Exception e) {
	    	throw new Exception(e.getMessage());	    }
	  }

	  @DeleteMapping("/projets")
		@CrossOrigin(origins="http://localhost:4200")
	  public ResponseEntity<HttpStatus> deleteAllTutorials() throws Exception{
	    try {
	    	 projetRepository.deleteAll();
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
throw new Exception(e.getMessage());
}
	  }
	  @GetMapping("/projetsuser")
		@CrossOrigin(origins = "http://localhost:4200")
		public ResponseEntity <List<Projet>> getProjets() {
			
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			 String username;
			if (principal instanceof UserDetails) {
			 username = ((UserDetails)principal).getUsername();
			} else {
			 username = principal.toString();
			}
			Optional<User> user = userRepository.findByUsername(username);
		
	     List<Projet> projets ;
			if (user.isPresent()) {
				 projets =projetImp.getProjetsByUser(user.get());
				return new ResponseEntity<>(projets , HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
	
		@GetMapping("/maissa/{projetId}")
		@CrossOrigin(origins="http://localhost:4200")
	    public ResponseEntity<List<User>> getUserByIdProjet(@PathVariable String projetId) {
		List<User> users = userService.getUserByProjetId(projetId);
		if(users != null && !users.isEmpty()) {
			return new ResponseEntity<>(users , HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		  @PutMapping("/user/{id}")
			@CrossOrigin(origins="http://localhost:4200")
		  public ResponseEntity<User> updateUserByprojet(@PathVariable("id") String id, @RequestBody User user) {
		    Optional<User> userup =  userRepository.findById(id);

		    if (userup.isPresent()) {
		    	
		      User _user = userup.get();
		     
			    
		      _user.setRoles(user.getRoles());
		     
		      return new ResponseEntity<>( userRepository.save(_user), HttpStatus.OK);
		    } else {
		      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    }
		  }
		
			@GetMapping("/{projetid}")
			@CrossOrigin(origins="http://localhost:4200")
		    public ResponseEntity<Backlog> getBacklogByIdProjet(@PathVariable String projetid) {
				Backlog backlog = tacheservice.getBacklogByIdProjet(projetid);
			if(backlog != null ) {
				return new ResponseEntity<>(backlog , HttpStatus.OK);
				} else {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
			}
	
}
