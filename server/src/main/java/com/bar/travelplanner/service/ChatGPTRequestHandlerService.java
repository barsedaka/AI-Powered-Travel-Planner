package com.bar.travelplanner.service;

import com.bar.travelplanner.dto.ItineraryRequestDTO;

import java.io.IOException;

public interface ChatGPTRequestHandlerService {
    String generateTripPlan(ItineraryRequestDTO itineraryRequestDTO) throws IOException, InterruptedException;
}
