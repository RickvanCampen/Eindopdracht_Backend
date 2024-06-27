package com.example.eindopdracht_backend_ipmroved.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fiets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String merk;
    private String model;
    private int jaar;
    private String frameMateriaal;
    private String framemaat;
    private String kleur;

    @ManyToOne
    private Fietsgarage fietsgarage;

    // Geen noodzaak voor expliciete constructor, getters en setters met Lombok
}