package com.example.eindopdracht_backend_ipmroved.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Onderdeel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naam;
    private double prijs;
    private String beschrijving;
    private int aantalOpVoorraad;

}
