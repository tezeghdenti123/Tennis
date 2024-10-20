package com.bezkoder.springjwt.mappers;

import com.bezkoder.springjwt.DTO.PrivateClientDTO;
import com.bezkoder.springjwt.DTO.PublicClientDTO;
import com.bezkoder.springjwt.models.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PublicClientMappers {
    PublicClientMappers INSTANCE= Mappers.getMapper(PublicClientMappers.class);
    PublicClientDTO toDTO(Client client);
    Client toEntity(PublicClientDTO publicClientDTO);
}
