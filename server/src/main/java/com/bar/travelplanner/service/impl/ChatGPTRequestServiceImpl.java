package com.bar.travelplanner.service.impl;

import com.bar.travelplanner.dto.chatGPT.ChatGPTMessageDTO;
import com.bar.travelplanner.dto.chatGPT.ChatGPTRequestDTO;
import com.bar.travelplanner.dto.ItineraryRequestDTO;
import com.bar.travelplanner.service.ChatGPTRequestService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import static com.bar.travelplanner.utils.constants.Constants.*;

@Service
@Log4j2
@RequiredArgsConstructor
public class ChatGPTRequestServiceImpl implements ChatGPTRequestService {

    private final ObjectMapper objectMapper;

    @Value("${OPENAI_API_KEY}")
    private String openAiKey;

    @Override
    public String createAnswer(ItineraryRequestDTO itineraryRequestDTO) throws IOException, InterruptedException {
        ChatGPTRequestDTO chatGPTRequestDTO = createChatGPTRequestDTO(itineraryRequestDTO);
        log.info("Sending request to ChatGPT: {}", chatGPTRequestDTO);
        String requestBody = objectMapper.writeValueAsString(chatGPTRequestDTO);

        return sendChatGPTRequest(requestBody);
    }

    @Override
    public ChatGPTRequestDTO createChatGPTRequestDTO(ItineraryRequestDTO itineraryRequestDTO) {
        List<ChatGPTMessageDTO> messages = new ArrayList<>();
        messages.add(new ChatGPTMessageDTO("user", getChatGPTRequestMessage(itineraryRequestDTO)));

        return ChatGPTRequestDTO.builder()
                .model(CHAT_COMPLETION)
                .maxTokens(OPEN_AI_MAX_TOKENS)
                .temperature(OPEN_AI_TEMPERATURE)
                .messages(messages)
                .build();
    }

    @Override
    public String sendChatGPTRequest(String requestBody) throws InterruptedException, IOException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(CHAT_COMPLETION_URL))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, openAiKey)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
    }

    private String getChatGPTRequestMessage(ItineraryRequestDTO itineraryRequestDTO) {
        String message = "You are an travel expert. Give me an itinerary for " + itineraryRequestDTO.getDestination() +
                "for " + itineraryRequestDTO.getTripDuration() + " days, assume each day starting at 10am and ending at 8pm " +
                "having a buffer of 30 minutes between each activity. I like to " + itineraryRequestDTO.getActivityTypes().toString();

        message += "Limit the length of output json string to 1200 characters. Generate a structured JSON representation " +
                "for the travel itinerary. " +
                "{\n" +
                "  \"days\": [\n" +
                "    {\n" +
                "      \"day\": 1,\n" +
                "      \"activities\": [\n" +
                "        {\n" +
                "          \"title\": \"Activity 1\",\n" +
                "          \"description\": \"Description of Activity 1\",\n" +
                "          \"link\": \"https://example.com/activity1\",\n" +
                "          \"start_time\": \"10:00 AM\",\n" +
                "          \"end_time\": \"12:00 PM\",\n" +
                "          \"location\": \"https://maps.google.com/?q=location1\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"title\": \"Activity 2\",\n" +
                "          \"description\": \"Description of Activity 2\",\n" +
                "          \"link\": \"https://example.com/activity2\",\n" +
                "          \"start_time\": \"02:00 PM\",\n" +
                "          \"end_time\": \"04:00 PM\",\n" +
                "          \"location\": \"https://maps.google.com/?q=location2\"\n" +
                "        },\n" +
                "        ....\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"day\": 2,\n" +
                "      \"activities\": [\n" +
                "        {\n" +
                "          \"title\": \"Another Activity 1\",\n" +
                "          \"description\": \"Description of Another Activity 1\",\n" +
                "          \"start_time\": \"09:30 AM\",\n" +
                "          \"end_time\": \"11:30 AM\",\n" +
                "          \"location\": \"https://maps.google.com/?q=location1\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"title\": \"Another Activity 2\",\n" +
                "          \"description\": \"Description of Another Activity 2\",\n" +
                "          \"start_time\": \"01:00 PM\",\n" +
                "          \"end_time\": \"03:00 PM\",\n" +
                "          \"location\": \"https://maps.google.com/?q=location2\"\n" +
                "        },\n" +
                "        ...\n" +
                "        day " +   itineraryRequestDTO.getTripDuration() +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}" +
                "Ensure that each day has a 'day' field and a list of 'activities' with 'title', 'description', " +
                "'start_time', 'end_time', and 'location' fields. Keep descriptions concise.\n";

        return message;
    }
}