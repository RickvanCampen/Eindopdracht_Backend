package com.example.eindopdracht_backend_ipmroved.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Checkbeurt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naam;

    private LocalDate datum;

    private String opmerkingen;

    private boolean isVoltooid;

    @ManyToOne
    private Fiets fiets;

    @ManyToOne
    private Medewerker medewerker;

    @OneToMany(mappedBy = "checkbeurt", cascade = CascadeType.ALL)
    private List<Reparatie> reparaties;

}
