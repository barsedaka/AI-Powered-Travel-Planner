package com.bar.travelplanner.dto.chatGPT;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatGPTMessageDTO {

    private String role;
    private String content;

    @Override
    public String toString() {
        return "{" +
                "role:" + role +
                ", content: " + content +
                "}";
    }
}