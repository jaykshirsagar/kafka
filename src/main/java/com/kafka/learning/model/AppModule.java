package com.kafka.learning.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "app_module")
public class AppModule {
    @Id
    @JsonProperty("_id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("version")
    private String version;
}
