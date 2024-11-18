package com.kafka.learning.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class AppModuleDto {
    @JsonProperty("_id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("version")
    private String version;

    public AppModuleDto() {
    }

    public AppModuleDto(String id, String name, String version) {
        this.id = id;
        this.name = name;
        this.version = version;
    }
}
