package com.kafka.learning.controller;

import com.kafka.learning.model.Client;
import com.kafka.learning.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class ClientController {
    final ClientService clientService;

    @PostMapping("/clientSave")
    public Client save(@RequestBody Client Client) {
        return clientService.save(Client);
    }

    // Get all Clients
    @GetMapping("/allClients")
    public List<Client> findAll() {
        return clientService.findAll();
    }

    // Get an Client by ID
    @GetMapping("/getClient/{id}")
    public Optional<Client> findById(@PathVariable String id) {
        return clientService.findById(id);
    }

    // Delete an Client by ID
    @DeleteMapping("/deleteClient/{id}")
    public void deleteById(@PathVariable String id) {
        clientService.deleteById(id);
    }

    @GetMapping("/getAllFromKafka")
    public void getKafka()
    {
        clientService.getAllFromKafka();
    }
}
