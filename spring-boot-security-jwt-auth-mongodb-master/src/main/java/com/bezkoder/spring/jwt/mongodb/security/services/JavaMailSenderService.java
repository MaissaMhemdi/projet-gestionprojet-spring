package com.bezkoder.spring.jwt.mongodb.security.services;


import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.jwt.mongodb.models.ERole;
import com.bezkoder.spring.jwt.mongodb.models.Projet;
import com.bezkoder.spring.jwt.mongodb.models.Role;
import com.bezkoder.spring.jwt.mongodb.models.User;
import com.bezkoder.spring.jwt.mongodb.repository.ProjetRepository;
import com.bezkoder.spring.jwt.mongodb.repository.RoleRepository;
import com.bezkoder.spring.jwt.mongodb.repository.UserRepository;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;

@Service
public class JavaMailSenderService {
	@Autowired
	private JavaMailSender emailSender;
	@Autowired
	private ProjetRepository projetRepository;
	@Autowired
	private UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;
	@Autowired
	PasswordEncoder encoder;
	
	public String sendSimpleMessageToNewUser(String to, String subject, String text, String projectName)
			throws MessagingException {
		
		Properties props = new Properties();
		SimpleMailMessage message = new SimpleMailMessage();
		props.put("mail.smtp.ssl.trust", "*");
		message.setTo(to);
		message.setSubject(subject);
		
		

		// test add user to project
		User user = new User();
		String username = to.substring(0,to.indexOf("@"));
		user.setEmail(to);
		user.setUsername(to.substring(0,to.indexOf("@")));
		String encodedPassword = encoder.encode(username);
		user.setPassword(encodedPassword);
		Optional<User> userfromdb = userRepository.findByUsername(username);
		Projet projet = projetRepository.findByTitle(projectName);
		if(!userfromdb.isPresent()) {
			setRole(user);
			userRepository.insert(user);	
		    projet.addUser(user);
			projetRepository.save(projet);
		}
		else {
			List<User> projectusers = projet.getUsers().stream().filter(userr-> userr.getUsername().equals(userfromdb.get().getUsername())).collect(Collectors.toList());
			if(projectusers.isEmpty()){
				  projet.addUser(userfromdb.get());	
					projetRepository.save(projet);
			}
			 
		}
		message.setText(text + " http://localhost:4200/login your password: " + encodedPassword);
		emailSender.send(message);
	
		return "email sended";
	}
	
private void setRole(User user) {
	

		Role userRole = roleRepository.findByName(ERole.ROLE_USER)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		

	user.getRoles().add(userRole);
}
}
