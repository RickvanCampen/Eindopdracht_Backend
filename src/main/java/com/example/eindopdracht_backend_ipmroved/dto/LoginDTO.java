package com.example.eindopdracht_backend_ipmroved.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

    @NotBlank(message = "Gebruikersnaam is verplicht")
    private String username;

    @NotBlank(message = "Wachtwoord is verplicht")
    private String password;
}
