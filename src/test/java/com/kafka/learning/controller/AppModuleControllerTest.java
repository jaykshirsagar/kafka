package com.kafka.learning.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kafka.learning.dto.AppModuleDto;
import com.kafka.learning.mapper.AppModuleMapper;
import com.kafka.learning.model.AppModule;
import com.kafka.learning.service.AppModuleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AppModuleControllerTest {

    @InjectMocks
    private AppModuleController appModuleController;

    @Mock
    private AppModuleService appModuleService;

    private AppModule appModule;

    @BeforeEach
    void setUp() {
        appModule = new AppModule();
        appModule.setId("1");
        appModule.setName("TestModule");
    }

    @Test
    void testSave() {
        when(appModuleService.save(any(AppModule.class))).thenReturn(appModule);

        AppModule result = appModuleController.save(appModule);

        assertNotNull(result);
        assertEquals("TestModule", result.getName());
        verify(appModuleService, times(1)).save(appModule);
    }

    @Test
    void testFindAll() {
        List<AppModule> appModules = new ArrayList<>();
        appModules.add(appModule);

        when(appModuleService.findAll()).thenReturn(appModules);

        List<AppModule> result = appModuleController.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(appModule, result.get(0));
        verify(appModuleService, times(1)).findAll();
    }

    @Test
    void testFindById() {
        when(appModuleService.findById("1")).thenReturn(Optional.of(appModule));

        Optional<AppModule> result = appModuleController.findById("1");

        assertTrue(result.isPresent());
        assertEquals(appModule, result.get());
        verify(appModuleService, times(1)).findById("1");
    }

    @Test
    void testDeleteById() {
        doNothing().when(appModuleService).deleteById("1");

        appModuleController.deleteById("1");

        verify(appModuleService, times(1)).deleteById("1");
    }

}
