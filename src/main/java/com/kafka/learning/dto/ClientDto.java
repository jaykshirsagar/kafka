package com.kafka.learning.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kafka.learning.model.Application;
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
public class ClientDto {
//    private String id;
    private String name;
    private List<Application> applications;
}
