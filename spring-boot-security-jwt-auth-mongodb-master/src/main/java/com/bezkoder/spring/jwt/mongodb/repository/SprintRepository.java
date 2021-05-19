package com.bezkoder.spring.jwt.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.spring.jwt.mongodb.models.Sprint;
@Repository
public interface SprintRepository extends MongoRepository<Sprint, String> {
	  Boolean existsByNomSprint(String nomSprint);
}
