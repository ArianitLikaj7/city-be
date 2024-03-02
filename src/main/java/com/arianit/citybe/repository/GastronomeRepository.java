package com.arianit.citybe.repository;

import com.arianit.citybe.entity.Gastronome;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GastronomeRepository extends JpaRepository<Gastronome, Long> {
    List<Gastronome> findByCityCityId(Long locationId);
}
