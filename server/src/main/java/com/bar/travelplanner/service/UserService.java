package com.bar.travelplanner.service;

import com.bar.travelplanner.dto.JwtAuthResponse;
import com.bar.travelplanner.dto.LoginDTO;
import com.bar.travelplanner.dto.RegisterDTO;

public interface UserService {

    void addUser(RegisterDTO registerDTO);

    JwtAuthResponse login(LoginDTO loginDTO, String token);
}
