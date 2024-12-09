package com.kafka.learning.mapper;

import com.kafka.learning.dto.ApplicationDto;
import com.kafka.learning.dto.ClientDto;
import com.kafka.learning.model.AppModule;
import com.kafka.learning.model.Application;
import com.kafka.learning.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = ApplicationMapper.class)
public interface ClientMapper {
    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    ClientDto clientToClientDto(Client client);

    Client clientDtoToClient(ClientDto clientDto);

    List<ClientDto> clientToClientDto(List<Client> client);

    List<Client> clientDtoToClient(List<ClientDto> clientDto);
}
