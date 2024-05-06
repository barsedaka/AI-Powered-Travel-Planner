package com.bar.travelplanner.dto;

import com.bar.travelplanner.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ItineraryRequestDTO {

    private Long id;
    private String destination;
    private List<String> activityTypes;
    private Integer tripDuration;
    private String tripPlan;
    private User user;
}
