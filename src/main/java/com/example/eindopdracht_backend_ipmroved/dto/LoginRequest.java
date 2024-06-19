package com.example.eindopdracht_backend_ipmroved.dto;

public class LoginRequest {

    private String gebruikersnaam;
    private String wachtwoord;

    // Getters and setters
    public String getUsername() {
        return gebruikersnaam;
    }

    public void setUsername(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }

    public String getPassword() {
        return wachtwoord;
    }

    public void setPassword(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }
}
