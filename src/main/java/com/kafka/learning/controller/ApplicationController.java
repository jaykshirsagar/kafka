package com.kafka.learning.controller;

import com.kafka.learning.model.AppModule;
import com.kafka.learning.model.Application;
import com.kafka.learning.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class ApplicationController {
    @Autowired
    ApplicationService applicationService;

    @PostMapping("/applicationSave")
    public Application save(@RequestBody Application application) {
        return applicationService.save(application);
    }

    // Get all AppModules
    @GetMapping("/allApplications")
    public List<Application> findAll() {
        return applicationService.findAll();
    }

    // Get an AppModule by ID
    @GetMapping("/getApplication/{id}")
    public Optional<Application> findById(@PathVariable String id) {
        return applicationService.findById(id);
    }

    // Delete an AppModule by ID
    @DeleteMapping("/deleteApplication/{id}")
    public void deleteById(@PathVariable String id) {
        applicationService.deleteById(id);
    }
}
