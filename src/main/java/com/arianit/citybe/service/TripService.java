package com.arianit.citybe.service;

import com.arianit.citybe.dto.CityDto;
import com.arianit.citybe.dto.GastronomeDto;
import com.arianit.citybe.dto.TripDto;
import com.arianit.citybe.entity.City;
import com.arianit.citybe.entity.Gastronome;
import com.arianit.citybe.entity.Trip;
import com.arianit.citybe.entity.TypeOfGastronome;
import com.arianit.citybe.mapper.CityMapper;
import com.arianit.citybe.mapper.GastronomeMapper;
import com.arianit.citybe.mapper.TripMapper;
import com.arianit.citybe.repository.CityRepository;
import com.arianit.citybe.repository.TripRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TripService {
    private final TripRepository tripRepository;
    private final TripMapper tripMapper;
    private final CityService cityService;
    private final CityRepository cityRepository;
    private final CityMapper cityMapper;
    private final GastronomeMapper gastronomeMapper;
    @Autowired
    public TripService(TripRepository tripRepository, TripMapper tripMapper, CityService cityService,
                       CityRepository cityRepository, CityMapper cityMapper, GastronomeMapper gastronomeMapper) {
        this.tripRepository = tripRepository;
        this.tripMapper = tripMapper;
        this.cityService = cityService;
        this.cityRepository = cityRepository;
        this.cityMapper = cityMapper;
        this.gastronomeMapper = gastronomeMapper;
    }


    public List<TripDto> createAdvanceTrip2(List<Long> cityIds, List<TypeOfGastronome> gastronomyTypes) {
        return tripRepository.findTripDetailsByCityIdsAndGastronomyTypes(cityIds, gastronomyTypes);
    }


    public TripDto createAdvanceTrip(List<Long> cityIds, List<TypeOfGastronome> gastronomyTypes) {
        List<City> cities = cityRepository.findAllById(cityIds);
        Trip trip = new Trip();
        trip.setCityIds(cityIds);
        trip.setTypesOfGastronome(gastronomyTypes);

        trip = tripRepository.save(trip);

        List<CityDto> cityDtos = cities.stream()
                .map(city -> {
                    List<Gastronome> filteredGastronomies = city.getGastronomes().stream()
                            .filter(g -> gastronomyTypes.contains(g.getTypeOfGastronome()) && g.isSponsored())
                            .collect(Collectors.toList());
                    filteredGastronomies.addAll(city.getGastronomes().stream()
                            .filter(g -> gastronomyTypes.contains(g.getTypeOfGastronome()) && !g.isSponsored())
                            .collect(Collectors.toList()));
                    city.setGastronomes(filteredGastronomies);
                    return cityMapper.mapEntityToDto(city);
                })
                .collect(Collectors.toList());

        TripDto tripDto = TripDto.builder()
                .id(trip.getId())
                .cityIds(cityIds)
                .typesOfGastronome(gastronomyTypes)
                .cityDtos(cityDtos)
                .build();

        return tripDto;
    }


    public List<TripDto> getAllTrips() {
        List<Trip> trips = tripRepository.findAll();
        List<TripDto> tripDtos = new ArrayList<>();

        for (Trip trip : trips) {
            List<CityDto> cityDtos = new ArrayList<>();
            for (Long cityId : trip.getCityIds()) {
                City city = cityRepository.findById(cityId)
                        .orElseThrow(() -> new EntityNotFoundException("City not found with ID: " + cityId));

                CityDto cityDto = cityMapper.mapEntityToDto(city);

                List<Gastronome> gastronomies = city.getGastronomes().stream()
                        .filter(g -> trip.getTypesOfGastronome().contains(g.getTypeOfGastronome()))
                        .collect(Collectors.toList());
                List<GastronomeDto> gastronomeDtos = gastronomies.stream()
                        .map(gastronomeMapper::mapEntityToDto)
                        .collect(Collectors.toList());
                cityDto.setGastronomeDtos(gastronomeDtos);

                cityDtos.add(cityDto);
            }

            TripDto tripDto = new TripDto();
            tripDto.setId(trip.getId());
            tripDto.setNumOfDays(trip.getNumOfDays());
            tripDto.setCityIds(trip.getCityIds());
            tripDto.setTypesOfGastronome(trip.getTypesOfGastronome());
            tripDto.setCityDtos(cityDtos);

            tripDtos.add(tripDto);
        }

        return tripDtos;
    }


    public TripDto getTripById(Long tripId) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new EntityNotFoundException("Trip not found with id " + tripId));

        List<CityDto> cityDtos = new ArrayList<>();
        for (Long cityId : trip.getCityIds()) {
            City city = cityRepository.findById(cityId)
                    .orElseThrow(() -> new EntityNotFoundException("City not found with ID: " + cityId));

            CityDto cityDto = cityMapper.mapEntityToDto(city);

            List<Gastronome> gastronomies = city.getGastronomes().stream()
                    .filter(g -> trip.getTypesOfGastronome().contains(g.getTypeOfGastronome()))
                    .collect(Collectors.toList());
            List<GastronomeDto> gastronomeDtos = gastronomies.stream()
                    .map(gastronomeMapper::mapEntityToDto)
                    .collect(Collectors.toList());
            cityDto.setGastronomeDtos(gastronomeDtos);

            cityDtos.add(cityDto);
        }

        TripDto tripDto = new TripDto();
        tripDto.setId(trip.getId());
        tripDto.setNumOfDays(trip.getNumOfDays());
        tripDto.setCityIds(trip.getCityIds());
        tripDto.setTypesOfGastronome(trip.getTypesOfGastronome());
        tripDto.setCityDtos(cityDtos);

        return tripDto;
    }


    public void deleteTrip(Long tripId) {
        if (!tripRepository.existsById(tripId)) {
            throw new EntityNotFoundException("Trip not found with id " + tripId);
        }
        tripRepository.deleteById(tripId);
    }
}