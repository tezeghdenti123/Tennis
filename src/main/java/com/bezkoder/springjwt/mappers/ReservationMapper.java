package com.bezkoder.springjwt.mappers;

import com.bezkoder.springjwt.DTO.ReservationDTO;
import com.bezkoder.springjwt.models.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReservationMapper {
    ReservationMapper INSTANCE= Mappers.getMapper(ReservationMapper.class);
    ReservationDTO toDTO(Reservation reservation);
    Reservation toEntity(ReservationDTO reservationDTO);
}
