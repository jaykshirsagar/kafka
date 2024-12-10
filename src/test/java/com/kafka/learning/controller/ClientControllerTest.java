package com.kafka.learning.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kafka.learning.dto.ClientDto;
import com.kafka.learning.mapper.ClientMapper;
import com.kafka.learning.model.Client;
import com.kafka.learning.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientControllerTest {

    @InjectMocks
    private ClientController clientController;

    @Mock
    private ClientService clientService;

    private Client client;

    @BeforeEach
    void setUp() {
        client = new Client();
        client.setId("1");
        client.setName("TestClient");
    }

    @Test
    void testSave() {
        when(clientService.save(any(Client.class))).thenReturn(client);

        Client result = clientController.save(client);

        assertNotNull(result);
        assertEquals("TestClient", result.getName());
        verify(clientService, times(1)).save(client);
    }

    @Test
    void testFindAll() {
        List<Client> clients = new ArrayList<>();
        clients.add(client);

        when(clientService.findAll()).thenReturn(clients);

        List<Client> result = clientController.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(client, result.get(0));
        verify(clientService, times(1)).findAll();
    }

    @Test
    void testFindById() {
        when(clientService.findById("1")).thenReturn(Optional.of(client));

        Optional<Client> result = clientController.findById("1");

        assertTrue(result.isPresent());
        assertEquals(client, result.get());
        verify(clientService, times(1)).findById("1");
    }

    @Test
    void testDeleteById() {
        doNothing().when(clientService).deleteById("1");

        clientController.deleteById("1");

        verify(clientService, times(1)).deleteById("1");
    }
}
