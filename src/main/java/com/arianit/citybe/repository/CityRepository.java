package com.arianit.citybe.repository;

import com.arianit.citybe.entity.City;
import com.arianit.citybe.entity.TypeOfGastronome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {
    @Query("SELECT c.cityId FROM City c WHERE c.cityId IN :cityIds")
    List<Long> findExistingCityIds(@Param("cityIds") List<Long> cityIds);
        List<City> findCitiesByCityPrefix(String cityPrefix);

}