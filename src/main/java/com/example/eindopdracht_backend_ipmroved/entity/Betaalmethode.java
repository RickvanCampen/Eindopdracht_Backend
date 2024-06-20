package com.example.eindopdracht_backend_ipmroved.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Betaalmethode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String naam;

    @Column(length = 1000)
    private String beschrijving;

    @Column(nullable = false)
    private boolean actief;

    // No need to manually define constructors, getters, setters, toString with Lombok annotations

}
