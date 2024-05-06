package com.bar.travelplanner.service;

import com.bar.travelplanner.dto.ItineraryRequestDTO;

import java.io.IOException;

public interface ChatGPTRequestHandlerService {
    String createAnswer(ItineraryRequestDTO itineraryRequestDTO) throws IOException, InterruptedException;
}
