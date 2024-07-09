package com.bar.travelplanner.factory;

import com.bar.travelplanner.dto.ItineraryRequestDTO;
import com.bar.travelplanner.dto.ItineraryResponseDTO;
import com.bar.travelplanner.entity.Itinerary;
import com.bar.travelplanner.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ItineraryFactory {

    public Itinerary createItinerary(ItineraryRequestDTO itineraryRequestDTO, User user) {
        Itinerary itinerary = new Itinerary();
        itinerary.setDestination(itineraryRequestDTO.getDestination());
        itinerary.setActivityTypes(itineraryRequestDTO.getActivityTypes());
        itinerary.setTripDuration(itineraryRequestDTO.getTripDuration());
        itinerary.setUser(user);

        return itinerary;
    }

    public void updateItinerary(Itinerary itinerary, ItineraryRequestDTO itineraryRequestDTO) {
        itinerary.setDestination(itineraryRequestDTO.getDestination());
        itinerary.setActivityTypes(itineraryRequestDTO.getActivityTypes());
        itinerary.setTripDuration(itineraryRequestDTO.getTripDuration());
    }

    public ItineraryResponseDTO createItineraryResponseDTO(Itinerary itinerary) {
        return ItineraryResponseDTO.builder()
                .id(itinerary.getId())
                .destination(itinerary.getDestination())
                .tripDuration(itinerary.getTripDuration())
                .activityTypes(itinerary.getActivityTypes())
                .tripPlan(itinerary.getTripPlan())
                .build();
    }
}
