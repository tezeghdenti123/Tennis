package com.bezkoder.springjwt.mappers;

import com.bezkoder.springjwt.DTO.PrivateAdminDTO;
import com.bezkoder.springjwt.models.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PrivateAdminMappers {
    PrivateAdminMappers INSTANCE= Mappers.getMapper(PrivateAdminMappers.class);
    @Mapping(source = "password",target = "password")
    PrivateAdminDTO toDTO(Admin admin);
    @Mapping(source = "password",target = "password")
    Admin toEntity(PrivateAdminDTO privateAdminDTO);

}
