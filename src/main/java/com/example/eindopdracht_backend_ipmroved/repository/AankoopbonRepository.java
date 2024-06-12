package com.example.eindopdracht_backend_ipmroved.repository;

import com.example.eindopdracht_backend_ipmroved.entity.Aankoopbon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AankoopbonRepository extends JpaRepository<Aankoopbon, Long> {
    List<Aankoopbon> findByKlantId(Long klantId);
}
