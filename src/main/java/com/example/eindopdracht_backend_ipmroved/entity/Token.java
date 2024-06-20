package com.example.eindopdracht_backend_ipmroved.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String accessToken;

    @Column(nullable = false)
    private boolean loggedOut = false;

    // Constructor met accessToken parameter
    public Token(String accessToken) {
        this.accessToken = accessToken;
    }

}
