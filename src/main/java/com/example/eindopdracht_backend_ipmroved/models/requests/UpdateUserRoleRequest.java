package com.example.eindopdracht_backend_ipmroved.models.requests;

import com.example.eindopdracht_backend_ipmroved.models.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRoleRequest {
    @JsonProperty
    private String username;

    @JsonProperty
    private Role role;
}

