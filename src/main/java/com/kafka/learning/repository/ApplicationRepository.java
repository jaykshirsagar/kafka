package com.kafka.learning.repository;

import com.kafka.learning.model.Application;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ApplicationRepository extends MongoRepository<Application,String> {
}
