package com.bezkoder.springjwt.mappers;

import com.bezkoder.springjwt.DTO.PublicAdminDTO;
import com.bezkoder.springjwt.models.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PublicAdminMapper {
    PublicAdminMapper INSTANCE= Mappers.getMapper(PublicAdminMapper.class);
    PublicAdminDTO toDTO(Admin admin);
    Admin toEntity(PublicAdminDTO publicAdminDTO);
}
