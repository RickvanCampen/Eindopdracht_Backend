package com.example.eindopdracht_backend_ipmroved.repository;

import com.example.eindopdracht_backend_ipmroved.entity.Voorraad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoorraadRepository extends JpaRepository<Voorraad, Long> {
    // Voeg eventuele aanvullende methoden toe die nodig zijn voor gegevensbewerkingen
}
