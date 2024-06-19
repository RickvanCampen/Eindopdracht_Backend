package com.example.eindopdracht_backend_ipmroved.config;

import com.example.eindopdracht_backend_ipmroved.repository.TokenRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CustomLogoutHandler implements LogoutHandler {

    private final TokenRepository tokenRepository;

    public CustomLogoutHandler(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public void logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Authentication authentication) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            tokenRepository.findByAccessToken(token).ifPresent(storedToken -> {
                // Assuming you have a method to set the token as logged out
                storedToken.setLoggedOut(true);
                tokenRepository.save(storedToken);
            });
        }

        SecurityContextHolder.clearContext();
    }
}
