package com.example.eindopdracht_backend_ipmroved.repository;

import com.example.eindopdracht_backend_ipmroved.entity.BicycleGarage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BicycleGarageRepository extends JpaRepository<BicycleGarage, Integer> {
}
