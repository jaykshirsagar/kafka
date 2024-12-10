package com.kafka.learning.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.learning.dto.ApplicationDto;
import com.kafka.learning.mapper.ApplicationMapper;
import com.kafka.learning.model.Application;
import com.kafka.learning.repository.ApplicationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApplicationServiceTest {

    @InjectMocks
    private ApplicationService applicationService;

    @Mock
    private ApplicationRepository applicationRepository;

    @Mock
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Mock
    private ObjectMapper objectMapper;

    private Application application;
    private ApplicationDto applicationDto;

    @BeforeEach
    void setUp() {
        application = new Application();
        application.setId("1");
        application.setName("TestApplication");

        applicationDto = new ApplicationDto();
        applicationDto.setId("1");
        applicationDto.setName("TestApplication");
    }

    @Test
    void testSave() {
        when(kafkaTemplate.send(eq("application"), any(ApplicationDto.class))).thenReturn(null);

        Application result = applicationService.save(application);

        assertEquals(application, result);
        verify(kafkaTemplate, times(1)).send(eq("application"), any(ApplicationDto.class));
    }

    @Test
    void testFindAll() {
        List<Application> applicationList = new ArrayList<>();
        applicationList.add(application);

        when(applicationRepository.findAll()).thenReturn(applicationList);

        List<Application> result = applicationService.findAll();

        assertEquals(1, result.size());
        assertEquals(application, result.get(0));
        verify(applicationRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        when(applicationRepository.findById("1")).thenReturn(Optional.of(application));

        Optional<Application> result = applicationService.findById("1");

        assertTrue(result.isPresent());
        assertEquals(application, result.get());
        verify(applicationRepository, times(1)).findById("1");
    }

    @Test
    void testDeleteById() {
        doNothing().when(applicationRepository).deleteById("1");

        applicationService.deleteById("1");

        verify(applicationRepository, times(1)).deleteById("1");
    }

}
