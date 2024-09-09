package com.example.eindopdracht_backend_ipmroved.dto;

import com.example.eindopdracht_backend_ipmroved.entity.TokenType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TokenDTO {

    private int id;
    private String token;
    private TokenType tokenType;
    private int userId;
    private boolean loggedOut;
}

