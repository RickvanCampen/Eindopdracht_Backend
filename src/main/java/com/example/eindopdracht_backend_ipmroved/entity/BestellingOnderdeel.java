package com.example.eindopdracht_backend_ipmroved.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
public class BestellingOnderdeel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;

    private int hoeveelheid;

    private double prijs;

    public BestellingOnderdeel() {
    }
}
