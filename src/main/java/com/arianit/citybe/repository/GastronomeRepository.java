package com.arianit.citybe.repository;

import com.arianit.citybe.entity.Gastronome;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GastronomeRepository extends JpaRepository<Gastronome, Long> {
}
