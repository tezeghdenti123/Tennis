package com.bezkoder.springjwt.mappers;

import com.bezkoder.springjwt.DTO.StadeDTO;
import com.bezkoder.springjwt.models.Stade;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StadeMappers {
    StadeMappers INSTANCE= Mappers.getMapper(StadeMappers.class);
    StadeDTO toDTO(Stade stade);
    Stade toEntity(StadeDTO stadeDTO);
}
