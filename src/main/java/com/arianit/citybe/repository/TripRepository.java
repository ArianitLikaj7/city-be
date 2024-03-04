package com.arianit.citybe.repository;

import com.arianit.citybe.dto.TripDto;
import com.arianit.citybe.entity.Trip;
import com.arianit.citybe.entity.TypeOfGastronome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long> {

    @Query(value = "SELECT DISTINCT " +
            "       t.id AS id, " +
            "       t.city_ids AS cityIds, " +
            "       t.types_of_gastronome AS gastronomyTypes " +
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
    List<TripDto> findTripDetailsByCityIdsAndGastronomyTypes(@Param("cityIds") List<Long> cityIds,
                                                             @Param("gastronomyTypes") List<TypeOfGastronome> gastronomyTypes);
}
