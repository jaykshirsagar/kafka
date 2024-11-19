package com.kafka.learning.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.learning.dto.AppModuleDto;
import com.kafka.learning.dto.ClientDto;
import com.kafka.learning.mapper.ClientMapper;
import com.kafka.learning.model.Client;
import com.kafka.learning.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    final ClientRepository clientRepository;
    @Autowired
    final KafkaTemplate<String, Object> kafkaTemplate;
    final ObjectMapper objectMapper;

    public Client save(Client client) {
        ClientDto clientDto = ClientMapper.INSTANCE.clientToClientDto(clientRepository.save(client));
        kafkaTemplate.send("clients", clientDto);
        return client;
    }

    public List<Client> findAll() {
        List<Client> clients = clientRepository.findAll();
        List<ClientDto> clientDtoList = ClientMapper.INSTANCE.clientToClientDto(clients);
        return clients;
    }

    public Optional<Client> findById(String id) {
        Optional<Client> clients = clientRepository.findById(id);
        return clients;
    }

    public void deleteById(String id) {
        clientRepository.deleteById(id);
    }

    @KafkaListener(topics = "clients", groupId = "my-group")
    public void listen(String message) throws JsonProcessingException {
        ClientDto clientDto = objectMapper.readValue(message, ClientDto.class);
        System.out.println("Received message: " + message);
    }
}
