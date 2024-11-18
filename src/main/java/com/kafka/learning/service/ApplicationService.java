package com.kafka.learning.service;

import com.kafka.learning.dto.AppModuleDto;
import com.kafka.learning.dto.ApplicationDto;
import com.kafka.learning.mapper.AppModuleMapper;
import com.kafka.learning.mapper.ApplicationMapper;
import com.kafka.learning.model.AppModule;
import com.kafka.learning.model.Application;
import com.kafka.learning.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService {
    @Autowired
    ApplicationRepository applicationRepository;

    public Application save(Application application) {
        ApplicationDto applicationDto = ApplicationMapper.INSTANCE.applicationToApplicationDto(applicationRepository.save(application));
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
}
