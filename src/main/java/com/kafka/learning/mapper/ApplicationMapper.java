package com.kafka.learning.mapper;

import com.kafka.learning.dto.AppModuleDto;
import com.kafka.learning.dto.ApplicationDto;
import com.kafka.learning.model.AppModule;
import com.kafka.learning.model.Application;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ApplicationMapper {

    ApplicationMapper INSTANCE = Mappers.getMapper(ApplicationMapper.class);

    ApplicationDto applicationToApplicationDto(Application application);

    Application applicationDtoToApplication(ApplicationDto applicationDto);

    List<ApplicationDto> applicationToApplicationDto(List<Application> application);
}
