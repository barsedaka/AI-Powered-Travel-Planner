package com.bar.travelplanner.dto.chatGPT;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@RequiredArgsConstructor
public class ChatGPTResponseDTO {
     String id;
     String object;
     Date created;
     String model;
     List<ChoiceDTO> choices;
     UsageDTO usage;
     String system_fingerprint;
}
