package com.bar.travelplanner.service.impl;

import com.bar.travelplanner.dto.chatGPT.ChatGPTMessageDTO;
import com.bar.travelplanner.dto.chatGPT.ChatGPTResponseDTO;
import com.bar.travelplanner.service.ChatGPTResponseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class ChatGPTResponseServiceImpl implements ChatGPTResponseService {

    private final ObjectMapper objectMapper;

    @Override
    public ChatGPTMessageDTO getMessageFromResponse(String response) throws JsonProcessingException {
        ChatGPTResponseDTO chatGPTResponseDTO = objectMapper.readValue(response, ChatGPTResponseDTO.class);
        ChatGPTMessageDTO chatGPTMessageDTO = chatGPTResponseDTO.getChoices().get(0).getMessage();

        log.info("----------AI_Response has sent successfully----------");
        log.debug("----------AI_Response : " + chatGPTResponseDTO + "----------");

        return chatGPTMessageDTO;
    }
}