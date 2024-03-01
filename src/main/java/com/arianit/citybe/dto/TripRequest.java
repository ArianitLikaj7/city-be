package com.arianit.citybe.dto;
import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TripRequest {
    private String destination;
    private Date startDate;
    private Date endDate;
    private List<Long> cityIds;
}