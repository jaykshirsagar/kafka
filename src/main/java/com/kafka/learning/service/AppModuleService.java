package com.kafka.learning.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.learning.dto.AppModuleDto;
import com.kafka.learning.dto.ApplicationDto;
import com.kafka.learning.mapper.AppModuleMapper;
import com.kafka.learning.model.AppModule;
import com.kafka.learning.repository.AppModuleRepository;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppModuleService {

    final AppModuleRepository appModuleRepository;
    @Autowired
    final KafkaTemplate<String, Object> kafkaTemplate;
    final ObjectMapper objectMapper;

    public AppModule save(AppModule appModule) {
        AppModuleDto appModuleDto = AppModuleMapper.INSTANCE.appModuleToAppModuleDto(appModuleRepository.save(appModule));
        kafkaTemplate.send("module", appModuleDto);
        return appModule;
    }

    public List<AppModule> findAll() {
        List<AppModule> appModules = appModuleRepository.findAll();
        List<AppModuleDto> appModuleDtoList = AppModuleMapper.INSTANCE.appModuleToAppModuleDto(appModules);
        return appModules;
    }

    public Optional<AppModule> findById(String id) {
        Optional<AppModule> appModules = appModuleRepository.findById(id);
        return appModules;
    }

    public void deleteById(String id) {
        appModuleRepository.deleteById(id);
    }

    @KafkaListener(topics = "module", groupId = "my-group")
    public void listen(String message) throws JsonProcessingException {
        AppModuleDto appModuleDto = objectMapper.readValue(message, AppModuleDto.class);
        System.out.println("Received message: " + message);
    }
}
