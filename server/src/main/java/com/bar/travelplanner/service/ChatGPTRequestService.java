package com.bar.travelplanner.service;

import com.bar.travelplanner.dto.chatGPT.ChatGPTRequestDTO;
import com.bar.travelplanner.dto.ItineraryRequestDTO;

import java.io.IOException;

public interface ChatGPTRequestService {
    String createAnswer(ItineraryRequestDTO itineraryRequestDTO) throws IOException, InterruptedException;
    ChatGPTRequestDTO createChatGPTRequestDTO(ItineraryRequestDTO itineraryRequestDTO);
    String sendChatGPTRequest(String body) throws InterruptedException, IOException;

}
