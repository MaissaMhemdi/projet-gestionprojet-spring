package com.bezkoder.spring.jwt.mongodb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.spring.jwt.mongodb.models.Tache;

@Repository
public interface TacheRepository extends MongoRepository<Tache, String> {
	

	
}
