package com.example.eindopdracht_backend_ipmroved.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KlantDTO {

    private Long id;

    @NotBlank(message = "Voornaam is verplicht")
    @Size(max = 50, message = "Voornaam mag niet langer zijn dan 50 tekens")
    private String voornaam;

    @NotBlank(message = "Achternaam is verplicht")
    @Size(max = 50, message = "Achternaam mag niet langer zijn dan 50 tekens")
    private String achternaam;

    @NotBlank(message = "E-mail is verplicht")
    @Email(message = "E-mail moet een geldig e-mailadres zijn")
    private String email;

    @NotBlank(message = "Telefoonnummer is verplicht")
    @Pattern(regexp = "\\d{10}", message = "Telefoonnummer moet precies 10 cijfers bevatten")
    private String telefoonnummer;

    @NotBlank(message = "Adres is verplicht")
    private String adres;

    @NotBlank(message = "Woonplaats is verplicht")
    private String woonplaats;

    @NotBlank(message = "Postcode is verplicht")
    @Pattern(regexp = "\\d{4} \\w{2}", message = "Postcode moet het formaat '1234 AB' hebben")
    private String postcode;

    private boolean premium;

    private List<String> aankoopGeschiedenis;

    public boolean isPremium() {
        return premium;
    }

    public void setIsPremium(boolean premium) { // Gebruik 'setPremium'
        this.premium = premium;
    }
}
