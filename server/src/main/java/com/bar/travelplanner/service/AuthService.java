package com.bar.travelplanner.service;

import com.bar.travelplanner.dto.JwtAuthResponse;
import com.bar.travelplanner.dto.LoginDTO;
import com.bar.travelplanner.dto.RegisterDTO;

public interface AuthService {

    String register(RegisterDTO registerDTO);
    JwtAuthResponse login(LoginDTO loginDTO);
}
