package com.arianit.citybe.repository;

import com.arianit.citybe.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}
