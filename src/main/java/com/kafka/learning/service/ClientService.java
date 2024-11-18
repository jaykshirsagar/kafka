package com.kafka.learning.service;

import com.kafka.learning.dto.ClientDto;
import com.kafka.learning.mapper.ClientMapper;
import com.kafka.learning.model.Client;
import com.kafka.learning.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;

    public Client save(Client Client) {
        ClientDto ClientDto = ClientMapper.INSTANCE.clientToClientDto(clientRepository.save(Client));
        return Client;
    }

    public List<Client> findAll() {
        List<Client> Clients = clientRepository.findAll();
        List<ClientDto> ClientDtoList = ClientMapper.INSTANCE.clientToClientDto(Clients);
        return Clients;
    }

    public Optional<Client> findById(String id) {
        Optional<Client> Clients = clientRepository.findById(id);
        return Clients;
    }

    public void deleteById(String id) {
        clientRepository.deleteById(id);
    }
}
