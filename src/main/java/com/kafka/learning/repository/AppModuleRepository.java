package com.kafka.learning.repository;

import com.kafka.learning.model.AppModule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface AppModuleRepository extends MongoRepository<AppModule, String> {
}
