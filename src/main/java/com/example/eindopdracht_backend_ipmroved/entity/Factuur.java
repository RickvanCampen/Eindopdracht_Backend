package com.example.eindopdracht_backend_ipmroved.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Factuur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate factuurDatum;

    @ManyToOne
    private Klant klant;

    @ManyToOne
    private Betaalmethode betaalmethode;

    @OneToMany(mappedBy = "factuur", cascade = CascadeType.ALL)
    private List<Product> producten;

    // Andere getters en setters

}
