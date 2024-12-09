package com.kafka.learning.mapper;

import com.kafka.learning.dto.AppModuleDto;
import com.kafka.learning.model.AppModule;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AppModuleMapper {

    AppModuleMapper INSTANCE = Mappers.getMapper(AppModuleMapper.class);

    AppModuleDto appModuleToAppModuleDto(AppModule appModule);

    AppModule appModuleDtoToAppModule(AppModuleDto appModuleDto);

    List<AppModuleDto> appModuleToAppModuleDto(List<AppModule> appModule);

    List<AppModule> appModuleDtoToAppModule(List<AppModuleDto> appModuleDto);

}
