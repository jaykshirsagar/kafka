package com.kafka.learning.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.learning.dto.AppModuleDto;
import com.kafka.learning.mapper.AppModuleMapper;
import com.kafka.learning.model.AppModule;
import com.kafka.learning.repository.AppModuleRepository;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.TopicPartition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AppModuleServiceTest {

    @InjectMocks
    private AppModuleService appModuleService;

    @Mock
    private AppModuleRepository appModuleRepository;

    @Mock
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Mock
    private KafkaConsumer<String, Object> kafkaConsumer;

    @Mock
    private ObjectMapper objectMapper;

    private AppModule appModule;
    private AppModuleDto appModuleDto;

    @BeforeEach
    void setUp() {
        appModule = new AppModule();
        appModule.setId("1");
        appModule.setName("TestModule");

        appModuleDto = new AppModuleDto();
        appModuleDto.setId("1");
        appModuleDto.setName("TestModule");
    }

    @Test
    void testSave() {
        when(kafkaTemplate.send(eq("module"), any(AppModuleDto.class))).thenReturn(null);

        AppModule result = appModuleService.save(appModule);

        assertEquals(appModule, result);
        verify(kafkaTemplate, times(1)).send(eq("module"), any(AppModuleDto.class));
    }

    @Test
    void testFindAll() {
        List<AppModule> appModuleList = new ArrayList<>();
        appModuleList.add(appModule);

        when(appModuleRepository.findAll()).thenReturn(appModuleList);

        List<AppModule> result = appModuleService.findAll();

        assertEquals(1, result.size());
        verify(appModuleRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        when(appModuleRepository.findById("1")).thenReturn(Optional.of(appModule));

        Optional<AppModule> result = appModuleService.findById("1");

        assertTrue(result.isPresent());
        assertEquals(appModule, result.get());
        verify(appModuleRepository, times(1)).findById("1");
    }

    @Test
    void testDeleteById() {
        doNothing().when(appModuleRepository).deleteById("1");

        appModuleService.deleteById("1");

        verify(appModuleRepository, times(1)).deleteById("1");
    }

}
