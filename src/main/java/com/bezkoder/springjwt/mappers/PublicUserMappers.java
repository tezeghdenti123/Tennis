package com.bezkoder.springjwt.mappers;

import com.bezkoder.springjwt.DTO.PublicUserDTO;
import com.bezkoder.springjwt.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PublicUserMappers {
    PublicUserMappers INSTANCE= Mappers.getMapper(PublicUserMappers.class);
    PublicUserDTO toDTO(User user);
    User toEntity(PublicUserDTO publicUserDTO);
}
