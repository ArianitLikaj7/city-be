package com.arianit.citybe.mapper;

import com.arianit.citybe.dto.ReservationDto;
import com.arianit.citybe.entity.Reservation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public ReservationMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    public ReservationDto mapEntityToDto(Reservation reservation) {
        return modelMapper.map(reservation, ReservationDto.class);
    }
    public Reservation mapDtoToEntity(ReservationDto reservationDto) {
        return modelMapper.map(reservationDto, Reservation.class);
    }

}