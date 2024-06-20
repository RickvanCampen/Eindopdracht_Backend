package com.example.eindopdracht_backend_ipmroved.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fietsgarage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naam;
    private String adres;
    private String woonplaats;
    private String postcode;

    @OneToMany(mappedBy = "fietsgarage", cascade = CascadeType.ALL)
    private List<Fiets> fietsen;

}
