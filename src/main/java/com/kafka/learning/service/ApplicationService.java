package com.kafka.learning.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.learning.dto.AppModuleDto;
import com.kafka.learning.dto.ApplicationDto;
import com.kafka.learning.dto.ClientDto;
import com.kafka.learning.mapper.AppModuleMapper;
import com.kafka.learning.mapper.ApplicationMapper;
import com.kafka.learning.mapper.ClientMapper;
import com.kafka.learning.model.AppModule;
import com.kafka.learning.model.Application;
import com.kafka.learning.model.Client;
import com.kafka.learning.repository.ApplicationRepository;
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
public class ApplicationService {

    final ApplicationRepository applicationRepository;
    @Autowired
    final KafkaTemplate<String, Object> kafkaTemplate;
    final ObjectMapper objectMapper;

    public Application save(Application application) {
        ApplicationDto applicationDto = ApplicationMapper.INSTANCE.applicationToApplicationDto(applicationRepository.save(application));
        kafkaTemplate.send("application", applicationDto);
        return application;
    }

    public List<Application> findAll() {
        List<Application> applications = applicationRepository.findAll();
        List<ApplicationDto> appModuleDtoList = ApplicationMapper.INSTANCE.applicationToApplicationDto(applications);
        return applications;
    }

    public Optional<Application> findById(String id) {
        Optional<Application> applications = applicationRepository.findById(id);
        return applications;
    }

    public void deleteById(String id) {
        applicationRepository.deleteById(id);
    }

    @KafkaListener(topics = "application", groupId = "my-group")
    public void listen(String message) throws JsonProcessingException {
        System.out.println("Received message: " + message);
        ApplicationDto applicationDto = objectMapper.readValue(message, ApplicationDto.class);
        Application application = ApplicationMapper.INSTANCE.applicationDtoToApplication(applicationDto);
        Optional<Application> existingApplication = applicationRepository.findByName(application.getName());
        if (!existingApplication.isPresent()) {
            applicationRepository.save(application);
            System.out.println("Application added to the database: " + application.toString());
        }
        else if(!existingApplication.equals(application))
        {
            application.setId(existingApplication.get().getId());
            applicationRepository.save(application);
            System.out.println("Application updated");
        }
        else System.out.println("Application already exist");
    }
}
