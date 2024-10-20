package com.bezkoder.springjwt.mappers;

import com.bezkoder.springjwt.DTO.PublicCoachDTO;
import com.bezkoder.springjwt.models.Coach;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PublicCoachMapper {
    PublicCoachMapper INSTANCE= Mappers.getMapper(PublicCoachMapper.class);
    PublicCoachDTO toDTO(Coach coach);
    Coach toEntity(PublicCoachDTO publicCoachDTO);
}
