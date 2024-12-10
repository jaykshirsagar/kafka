package com.kafka.learning.controller;

import com.kafka.learning.model.Application;
import com.kafka.learning.service.ApplicationService;
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
class ApplicationControllerTest {

    @InjectMocks
    private ApplicationController applicationController;

    @Mock
    private ApplicationService applicationService;

    private Application application;

    @BeforeEach
    void setUp() {
        application = new Application();
        application.setId("1");
        application.setName("TestApplication");
    }

    @Test
    void testSave() {
        when(applicationService.save(any(Application.class))).thenReturn(application);

        Application result = applicationController.save(application);

        assertNotNull(result);
        assertEquals("TestApplication", result.getName());
        verify(applicationService, times(1)).save(application);
    }

    @Test
    void testFindAll() {
        List<Application> applications = new ArrayList<>();
        applications.add(application);

        when(applicationService.findAll()).thenReturn(applications);

        List<Application> result = applicationController.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(application, result.get(0));
        verify(applicationService, times(1)).findAll();
    }

    @Test
    void testFindById() {
        when(applicationService.findById("1")).thenReturn(Optional.of(application));

        Optional<Application> result = applicationController.findById("1");

        assertTrue(result.isPresent());
        assertEquals(application, result.get());
        verify(applicationService, times(1)).findById("1");
    }

    @Test
    void testDeleteById() {
        doNothing().when(applicationService).deleteById("1");

        applicationController.deleteById("1");

        verify(applicationService, times(1)).deleteById("1");
    }
}
