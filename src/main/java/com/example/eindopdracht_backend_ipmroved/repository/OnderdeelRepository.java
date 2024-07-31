package com.example.eindopdracht_backend_ipmroved.repository;

import com.example.eindopdracht_backend_ipmroved.entity.Onderdeel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OnderdeelRepository extends JpaRepository<Onderdeel, Long> {

    List<Onderdeel> findByNaamContainingIgnoreCase(String naam);

    List<Onderdeel> findByPrijsLessThan(double prijs);
}
