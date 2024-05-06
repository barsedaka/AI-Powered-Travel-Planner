package com.bar.travelplanner.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItineraryResponseDTO {

    private Long id;
    private String destination;
    private List<String> activityTypes;
    private Integer tripDuration;
    private String tripPlan;
}
