package com.bar.travelplanner.service.impl;

import com.bar.travelplanner.utils.constants.Constants;
import com.bar.travelplanner.dto.JwtAuthResponse;
import com.bar.travelplanner.dto.LoginDTO;
import com.bar.travelplanner.dto.RegisterDTO;
import com.bar.travelplanner.security.JwtTokenProvider;
import com.bar.travelplanner.service.AuthService;
import com.bar.travelplanner.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Log4j2
public class AuthServiceImpl implements AuthService {

    private UserService userService;
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public JwtAuthResponse login(LoginDTO loginDTO) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDTO.getUsernameOrEmail(),
                loginDTO.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);

        return userService.login(loginDTO, token);
    }

    @Override
    public String register(RegisterDTO registerDTO) {
        userService.addUser(registerDTO);
        log.info("user saved successfully!");

        return Constants.SUCCESSFULLY_REGISTERED_MESSAGE;
    }
}