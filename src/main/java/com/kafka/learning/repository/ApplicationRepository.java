package com.kafka.learning.repository;

import com.kafka.learning.model.Application;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ApplicationRepository extends MongoRepository<Application,String> {
    Optional<Application> findByName(String name);
}
