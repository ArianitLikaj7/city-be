package com.arianit.citybe.dto;


import com.arianit.citybe.entity.TypeOfGastronome;
import lombok.*;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TripDto {
    private Long id;
    private int numOfDays;
    private List<Long> cityIds;
    private List<TypeOfGastronome> typesOfGastronome;
    private List<CityDto> cityDtos;
}