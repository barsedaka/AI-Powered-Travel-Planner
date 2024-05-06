package com.bar.travelplanner.dto.mapper;

import com.bar.travelplanner.dto.ItineraryResponseDTO;
import com.bar.travelplanner.entity.Itinerary;
import org.springframework.stereotype.Service;

@Service
public class ItineraryResponseDTOMapper {
    public ItineraryResponseDTO toItineraryResponseDTO(Itinerary itinerary) {
        return ItineraryResponseDTO.builder()
                .id(itinerary.getId())
                .destination(itinerary.getDestination())
                .tripDuration(itinerary.getTripDuration())
                .activityTypes(itinerary.getActivityTypes())
                .tripPlan(itinerary.getTripPlan())
                .build();
    }
}
