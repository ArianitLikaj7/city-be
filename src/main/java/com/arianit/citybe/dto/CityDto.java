package com.arianit.citybe.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CityDto {
    private Long cityId;
    private String nameOfCity;
    private String culturalHeritage;
    private String cityPrefix;
    private List<GastronomeDto> gastronomeDtos;

}