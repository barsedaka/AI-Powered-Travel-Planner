package com.bar.travelplanner.dto;

import lombok.*;

@Data
@Builder
public class JwtAuthResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private String role;
}
