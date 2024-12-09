package com.kafka.learning.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kafka.learning.dto.AppModuleDto;
import com.kafka.learning.dto.ClientDto;
import com.kafka.learning.mapper.AppModuleMapper;
import com.kafka.learning.mapper.ClientMapper;
import com.kafka.learning.model.AppModule;
import com.kafka.learning.model.Client;
import com.kafka.learning.service.AppModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class AppModuleController {

    final AppModuleService appModuleService;

    @PostMapping("/moduleSave")
    public AppModule save(@RequestBody AppModule appModule) {
        return appModuleService.save(appModule);
    }

    // Get all AppModules
    @GetMapping("/allModules")
    public List<AppModule> findAll() {
        return appModuleService.findAll();
    }

    // Get an AppModule by ID
    @GetMapping("/getModule/{id}")
    public Optional<AppModule> findById(@PathVariable String id) {
        return appModuleService.findById(id);
    }

    // Delete an AppModule by ID
    @DeleteMapping("/deleteModule/{id}")
    public void deleteById(@PathVariable String id) {
        appModuleService.deleteById(id);
    }

    @GetMapping("/getModuleFromPartition/{id}/{partition}")
    public List<AppModule> getModuleFromPartition(@PathVariable int id, @PathVariable int partition) throws JsonProcessingException {
        List<AppModuleDto> appModuleDtoList = appModuleService.getFromPartition(id,partition);
        List<AppModule> appModules = AppModuleMapper.INSTANCE.appModuleDtoToAppModule(appModuleDtoList);
        return appModules;
    }
}
