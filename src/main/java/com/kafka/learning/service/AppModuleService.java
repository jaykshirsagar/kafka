package com.kafka.learning.service;

import com.kafka.learning.dto.AppModuleDto;
import com.kafka.learning.mapper.AppModuleMapper;
import com.kafka.learning.model.AppModule;
import com.kafka.learning.repository.AppModuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AppModuleService {

    @Autowired
    AppModuleRepository appModuleRepository;

    public AppModuleDto save(AppModule appModule) {
        AppModuleDto appModuleDto = AppModuleMapper.INSTANCE.appModuleToAppModuleDto(appModuleRepository.save(appModule));
        return appModuleDto;
    }

    public List<AppModuleDto> findAll() {
        List<AppModuleDto> appModuleDtoList = AppModuleMapper.INSTANCE.appModuleToAppModuleDto(appModuleRepository.findAll());
        return appModuleDtoList;
    }

    public Optional<AppModuleDto> findById(String id) {
        AppModuleDto appModuleDto = AppModuleMapper.INSTANCE.appModuleToAppModuleDto(appModuleRepository.findById(id));
        return Optional.ofNullable(appModuleDto);
    }

    public void deleteById(String id) {
        appModuleRepository.deleteById(id);
    }
}
