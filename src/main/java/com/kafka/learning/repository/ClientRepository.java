package com.kafka.learning.repository;


import com.kafka.learning.model.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientRepository extends MongoRepository<Client,String> {
}
