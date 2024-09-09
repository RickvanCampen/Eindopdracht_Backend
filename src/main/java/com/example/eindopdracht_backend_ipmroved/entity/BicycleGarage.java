package com.example.eindopdracht_backend_ipmroved.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "bicyclegarage")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BicycleGarage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToMany(mappedBy = "bicycleGarage")
    @Column(name = "users")
    private List<User> users;
}
