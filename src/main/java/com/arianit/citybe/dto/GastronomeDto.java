package com.arianit.citybe.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GastronomeDto {
    private Long gastronomeId;
    private Long cityId;
    private String nameOfGastronome;
    private String schedule;
    private Double longitude;
    private Double latitude;
    private String typeOfGastronome;
    private boolean sponsored;
    private List<String> attachments;


}