package com.example.eindopdracht_backend_ipmroved.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter @Setter
public class Aankoopbon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate datum;

    private double totaalBedrag;

    @ManyToOne
    private Klant klant;

    @ManyToOne
    private Levering levering;

    @OneToMany(mappedBy = "aankoopbon", cascade = CascadeType.ALL)
    private List<BestellingOnderdeel> bestellingen;

    // Constructors
    public Aankoopbon() {}
}
