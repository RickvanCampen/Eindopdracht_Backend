package com.example.eindopdracht_backend_ipmroved.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Levering {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate leverDatum;
    private String leverancier;
    private String trackingCode;

    @OneToMany(mappedBy = "levering", cascade = CascadeType.ALL)
    private List<Aankoopbon> aankoopbonnen;

}
