package com.example.eindopdracht_backend_ipmroved.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileResponse {
    private String username;
    private String email;

    public ProfileResponse(String username, String email) {
        this.username = username;
        this.email = email;
    }

}


