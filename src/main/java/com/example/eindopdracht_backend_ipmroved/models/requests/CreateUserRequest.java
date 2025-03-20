package com.example.eindopdracht_backend_ipmroved.models.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    String password;
}