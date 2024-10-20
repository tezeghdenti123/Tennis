package com.bezkoder.springjwt.mappers;

import com.bezkoder.springjwt.DTO.PrivateClientDTO;
import com.bezkoder.springjwt.DTO.PublicClientDTO;
import com.bezkoder.springjwt.models.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PrivateClientMappers {
    PrivateClientMappers INSTANCE= Mappers.getMapper(PrivateClientMappers.class);
    @Mapping(source = "groupe.id",target = "groupeId")
    PrivateClientDTO toDTO(Client client);
    @Mapping(source = "groupeId",target = "groupe.id")
    Client toEntity(PrivateClientDTO privateClientDTO);
}
