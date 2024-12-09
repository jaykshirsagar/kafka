package com.kafka.learning.repository;


import com.kafka.learning.model.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ClientRepository extends MongoRepository<Client,String> {
    Optional<Client> findByName(String name);
}
