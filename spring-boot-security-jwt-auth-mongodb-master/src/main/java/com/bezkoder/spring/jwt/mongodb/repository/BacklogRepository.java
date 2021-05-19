package com.bezkoder.spring.jwt.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.spring.jwt.mongodb.models.Backlog;
@Repository

public interface BacklogRepository extends MongoRepository<Backlog, String> {

}
