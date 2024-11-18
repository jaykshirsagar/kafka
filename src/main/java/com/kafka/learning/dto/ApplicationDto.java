package com.kafka.learning.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kafka.learning.model.AppModule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationDto {
    private String id;
    private String name;
    private String version;
    private List<AppModule> modules;
}
