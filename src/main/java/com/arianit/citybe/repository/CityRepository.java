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
    @Query(value = "SELECT DISTINCT " +
            "       t.id AS trip_id, " +
            "       t.city_ids AS city_ids, " +
            "       t.types_of_gastronome AS gastronomy_types, " +
            "       c.id AS city_id, " +
            "       c.name AS city_name, " +
            "       g.id AS gastronomy_id, " +
            "       g.name AS gastronomy_name " +
            "FROM " +
            "       trip t " +
            "JOIN " +
            "       city c ON c.id IN (SELECT UNNEST(t.city_ids)) " +
            "JOIN " +
            "       gastronomy g ON g.city_id = c.id " +
            "WHERE " +
            "       c.id IN (:cityIds) " +
            "       AND g.type_of_gastronome IN (:gastronomyTypes) " +
            "ORDER BY " +
            "       CASE WHEN g.is_sponsored THEN 0 ELSE 1 END",
            nativeQuery = true)
    List<Object[]> findTripDetailsByCityIdsAndGastronomyTypes(@Param("cityIds") List<Long> cityIds,
                                                              @Param("gastronomyTypes") List<TypeOfGastronome> gastronomyTypes);
}