package com.kafka.learning.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.learning.dto.ClientDto;
import com.kafka.learning.mapper.ClientMapper;
import com.kafka.learning.model.Client;
import com.kafka.learning.repository.ClientRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Mock
    private KafkaConsumer<String, Object> kafkaConsumer;

    @Mock
    private ObjectMapper objectMapper;

    private Client client;
    private ClientDto clientDto;

    @BeforeEach
    void setUp() {
        client = new Client();
        client.setId("1");
        client.setName("TestClient");

        clientDto = new ClientDto();
        clientDto.setId("1");
        clientDto.setName("TestClient");
    }

    @Test
    void testSave() {
        when(kafkaTemplate.send(eq("clients"), any(ClientDto.class))).thenReturn(null);

        Client result = clientService.save(client);

        assertEquals(client, result);
        verify(kafkaTemplate, times(1)).send(eq("clients"), any(ClientDto.class));
    }

    @Test
    void testFindAll() {
        List<Client> clientList = new ArrayList<>();
        clientList.add(client);

        when(clientRepository.findAll()).thenReturn(clientList);

        List<Client> result = clientService.findAll();

        assertEquals(1, result.size());
        assertEquals(client, result.get(0));
        verify(clientRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        when(clientRepository.findById("1")).thenReturn(Optional.of(client));

        Optional<Client> result = clientService.findById("1");

        assertTrue(result.isPresent());
        assertEquals(client, result.get());
        verify(clientRepository, times(1)).findById("1");
    }

    @Test
    void testDeleteById() {
        doNothing().when(clientRepository).deleteById("1");

        clientService.deleteById("1");

        verify(clientRepository, times(1)).deleteById("1");
    }

}
