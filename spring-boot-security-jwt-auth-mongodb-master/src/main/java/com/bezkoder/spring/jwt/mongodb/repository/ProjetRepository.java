package com.bezkoder.spring.jwt.mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import com.bezkoder.spring.jwt.mongodb.models.Projet;

@Repository

public interface ProjetRepository extends MongoRepository<Projet, String> {
	 
       Projet  findByTitle(String title);


}
