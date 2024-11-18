package com.kafka.learning.controller;

import com.kafka.learning.dto.AppModuleDto;
import com.kafka.learning.model.AppModule;
import com.kafka.learning.service.AppModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController("/appmodule")
@CrossOrigin("*")
public class AppModuleController {

    @Autowired
    AppModuleService appModuleService;

    @PostMapping("/save")
    public AppModuleDto save(@RequestBody AppModule appModule) {
        return appModuleService.save(appModule);
    }

    // Get all AppModules
    @GetMapping("/findAll")
    public List<AppModuleDto> findAll() {
        return appModuleService.findAll();
    }

    // Get an AppModule by ID
    @GetMapping("/get/{id}")
    public Optional<AppModuleDto> findById(@PathVariable String id) {
        return appModuleService.findById(id);
    }

    // Delete an AppModule by ID
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable String id) {
        appModuleService.deleteById(id);
    }
}
