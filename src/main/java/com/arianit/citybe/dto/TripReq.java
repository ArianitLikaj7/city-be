package com.arianit.citybe.dto;

import com.arianit.citybe.entity.TypeOfGastronome;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TripReq {
    private List<TypeOfGastronome> typeOfGastronomes;
    private List<Long> cityIds;
}