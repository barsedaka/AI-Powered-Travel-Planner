package com.bar.travelplanner.service.impl;

import com.bar.travelplanner.entity.User;
import com.bar.travelplanner.exception.ResourceNotFoundException;
import com.bar.travelplanner.repository.UserRepository;
import com.bar.travelplanner.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpringSecurityService implements SecurityService {
    private final UserRepository userRepository;

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            String username = authentication.getName();
            return userRepository.findByUsername(username)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        }
        return null;
    }
}
