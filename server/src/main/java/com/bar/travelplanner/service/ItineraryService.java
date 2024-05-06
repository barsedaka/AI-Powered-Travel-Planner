package com.bar.travelplanner.service;

import com.bar.travelplanner.dto.ItineraryRequestDTO;
import com.bar.travelplanner.dto.ItineraryResponseDTO;

import java.io.IOException;
import java.util.List;

public interface ItineraryService {

    ItineraryResponseDTO addItinerary(ItineraryRequestDTO itineraryRequestDTO) throws IOException, InterruptedException;

    ItineraryResponseDTO getItineraryById(Long id);

    List<ItineraryResponseDTO> getAllItineraries();

    ItineraryResponseDTO updateItinerary(ItineraryRequestDTO itineraryRequestDTO, Long id) throws IOException, InterruptedException;

    void deleteItinerary(Long id);

}
