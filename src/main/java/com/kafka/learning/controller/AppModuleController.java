package com.kafka.learning.controller;

import com.kafka.learning.dto.AppModuleDto;
import com.kafka.learning.model.AppModule;
import com.kafka.learning.service.AppModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/appmodule")
@CrossOrigin("*")
public class AppModuleController {

    @Autowired
    AppModuleService appModuleService;

    @PostMapping("/save")
    public ResponseEntity<AppModuleDto> save(@RequestBody AppModule appModule) {
        return ResponseEntity.ok(appModuleService.save(appModule));
    }

    // Get all AppModules
    @GetMapping("/findAll")
    public ResponseEntity<List<AppModuleDto>> findAll() {
        return ResponseEntity.ok(appModuleService.findAll());
    }

    // Get an AppModule by ID
    @GetMapping("/get/{id}")
    public ResponseEntity<AppModuleDto> findById(@PathVariable String id) {
        return appModuleService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete an AppModule by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        appModuleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
