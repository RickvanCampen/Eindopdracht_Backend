package com.example.eindopdracht_backend_ipmroved.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reparatie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fiets_id")
    private Fiets fiets;

    @ManyToOne
    @JoinColumn(name = "medewerker_id")
    private Medewerker medewerker;

    @ManyToOne
    @JoinColumn(name = "checkbeurt_id")
    private Checkbeurt checkbeurt;

    private String opmerkingen;

    private Date datum;

    // Andere getters en setters worden automatisch gegenereerd door Lombok

    // toString methode wordt automatisch gegenereerd door Lombok
}
