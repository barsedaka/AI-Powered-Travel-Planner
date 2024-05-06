package com.bar.travelplanner.service.impl;

import com.bar.travelplanner.dto.chatGPT.ChatGPTMessageDTO;
import com.bar.travelplanner.dto.ItineraryRequestDTO;
import com.bar.travelplanner.service.ChatGPTRequestHandlerService;
import com.bar.travelplanner.service.ChatGPTRequestService;
import com.bar.travelplanner.service.ChatGPTResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ChatGPTRequestHandlerServiceImpl implements ChatGPTRequestHandlerService{

    private final ChatGPTRequestService chatGPTRequestService;
    private final ChatGPTResponseService chatGPTResponseService;


    @Override
    public String createAnswer(ItineraryRequestDTO itineraryRequestDTO) throws IOException, InterruptedException {
        String response = chatGPTRequestService.createAnswer(itineraryRequestDTO);

        ChatGPTMessageDTO chatGPTMessageDTO = chatGPTResponseService.getMessageFromResponse(response);

        return chatGPTMessageDTO.getContent();
    }
}
