package com.bar.travelplanner.service.impl;

import com.bar.travelplanner.dto.JwtAuthResponse;
import com.bar.travelplanner.dto.LoginDTO;
import com.bar.travelplanner.dto.RegisterDTO;
import com.bar.travelplanner.entity.Role;
import com.bar.travelplanner.entity.User;
import com.bar.travelplanner.exception.EmailAlreadyExistsException;
import com.bar.travelplanner.exception.UsernameAlreadyExistsException;
import com.bar.travelplanner.repository.RoleRepository;
import com.bar.travelplanner.repository.UserRepository;
import com.bar.travelplanner.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public void addUser(RegisterDTO registerDTO) {
        if(userRepository.existsByUsername(registerDTO.getUsername())) {
            throw new UsernameAlreadyExistsException();
        }

        if(userRepository.existsByEmail(registerDTO.getEmail())) {
            throw new EmailAlreadyExistsException();
        }

        User user = createUser(registerDTO);
        userRepository.save(user);
    }

    @Override
    public JwtAuthResponse login(LoginDTO loginDTO, String token) {
        Optional<User> userOptional = userRepository.findByUsernameOrEmail(loginDTO.getUsernameOrEmail(),
                loginDTO.getUsernameOrEmail());

        String role = null;
        if(userOptional.isPresent()) {
            User loggedInUser = userOptional.get();
            Optional<Role> optionalRole = loggedInUser.getRoles().stream().findFirst();

            if(optionalRole.isPresent()) {
                role = optionalRole.get().getName();
            }
        }

        return JwtAuthResponse.builder()
                .accessToken(token)
                .role(role)
                .build();
    }

    private User createUser(RegisterDTO registerDTO) {
        User user = new User();
        user.setName(registerDTO.getName());
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_ADMIN");
        roles.add(userRole);

        user.setRoles(roles);

        return user;
    }
}