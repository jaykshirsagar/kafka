package com.kafka.learning.repository;

import com.kafka.learning.model.AppModule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface AppModuleRepository extends MongoRepository<AppModule, String> {
    Optional<AppModule> findByName(String name);
}
