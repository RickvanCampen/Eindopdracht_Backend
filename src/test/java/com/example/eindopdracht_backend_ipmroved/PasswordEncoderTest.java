package com.example.eindopdracht_backend_ipmroved;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordEncoderTest {

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    public void testPasswordEncoder() {
        String rawPassword = "TestPassword123";
        String encodedPassword = passwordEncoder.encode(rawPassword);

        assertTrue(passwordEncoder.matches(rawPassword, encodedPassword),
                "Password matches");

        String incorrectPassword = "IncorrectPassword";
        assertTrue(!passwordEncoder.matches(incorrectPassword, encodedPassword),
                "Incorrect password does not match");
    }
}
