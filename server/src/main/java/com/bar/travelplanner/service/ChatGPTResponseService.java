package com.bar.travelplanner.service;

import com.bar.travelplanner.dto.chatGPT.ChatGPTMessageDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface ChatGPTResponseService {
    ChatGPTMessageDTO getMessageFromResponse(String response) throws JsonProcessingException;
}
