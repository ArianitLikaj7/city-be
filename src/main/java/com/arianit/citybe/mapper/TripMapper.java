package com.arianit.citybe.mapper;


import com.arianit.citybe.dto.TripDto;
import com.arianit.citybe.dto.TripRequest;
import com.arianit.citybe.entity.Trip;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TripMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public TripMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public TripDto mapEntityToDto(Trip trip){
        return modelMapper.map(trip, TripDto.class);
    }

    public Trip mapRequestToEntity(TripRequest tripRequest){
        return modelMapper.map(tripRequest, Trip.class);
    }

}