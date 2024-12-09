package com.kafka.learning.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.learning.dto.AppModuleDto;
import com.kafka.learning.dto.ApplicationDto;
import com.kafka.learning.dto.ClientDto;
import com.kafka.learning.mapper.AppModuleMapper;
import com.kafka.learning.mapper.ApplicationMapper;
import com.kafka.learning.model.AppModule;
import com.kafka.learning.model.Application;
import com.kafka.learning.repository.AppModuleRepository;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppModuleService {

    final AppModuleRepository appModuleRepository;
    @Autowired
    final KafkaTemplate<String, Object> kafkaTemplate;
    @Autowired
    final KafkaConsumer<String, Object> kafkaConsumer;
    final ObjectMapper objectMapper;

    public AppModule save(AppModule appModule) {
        AppModuleDto appModuleDto = AppModuleMapper.INSTANCE.appModuleToAppModuleDto(appModule);
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

    public List<AppModuleDto> getFromPartition(int offset, int partition) throws JsonProcessingException {
        List<AppModuleDto> appModuleDtoList = new ArrayList<>();
        kafkaConsumer.subscribe(Arrays.asList("module"));

        kafkaConsumer.seek(new TopicPartition("module",partition), offset);

        ConsumerRecords<String, Object> records = kafkaConsumer.poll(5000);

        for (ConsumerRecord<String, Object> record : records) {
            System.out.println(record.value().toString());
            appModuleDtoList.add(objectMapper.readValue(record.value().toString(),AppModuleDto.class));
        }
        return appModuleDtoList;
    }

    @KafkaListener(topics = "module", groupId = "my-group")
    public void listen(String message) throws JsonProcessingException {
        System.out.println("Received message: " + message);
        AppModuleDto appModuleDto = objectMapper.readValue(message, AppModuleDto.class);
        AppModule appModule = AppModuleMapper.INSTANCE.appModuleDtoToAppModule(appModuleDto);
        Optional<AppModule> existingModule = appModuleRepository.findByName(appModule.getName());
        if (!existingModule.isPresent()) {
            appModuleRepository.save(appModule);
            System.out.println("Module added to the database: " + appModule.toString());
        }
        else if(!existingModule.equals(appModule))
        {
            appModule.setId(existingModule.get().getId());
            appModuleRepository.save(appModule);
            System.out.println("Module updated");
        }
        else System.out.println("Module already exist");
    }
}
