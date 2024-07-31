package com.example.eindopdracht_backend_ipmroved.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {

    @NotBlank(message = "Gebruikersnaam is verplicht")
    @Size(min = 3, max = 20, message = "Gebruikersnaam moet tussen de 3 en 20 tekens lang zijn")
    private String username;

    @NotBlank(message = "Wachtwoord is verplicht")
    @Size(min = 8, message = "Wachtwoord moet minimaal 8 tekens bevatten")
    private String password;

    @NotBlank(message = "E-mail is verplicht")
    @Email(message = "E-mail moet een geldig e-mailadres zijn")
    private String email;
}
