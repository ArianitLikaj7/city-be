package com.arianit.citybe.repository;

import com.arianit.citybe.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByGastronomeGastronomeId(Long gastronomeId);
}
