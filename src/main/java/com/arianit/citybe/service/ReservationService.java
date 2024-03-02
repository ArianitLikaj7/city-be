package com.arianit.citybe.service;

import com.arianit.citybe.dto.ReservationDto;
import com.arianit.citybe.entity.Gastronome;
import com.arianit.citybe.entity.Reservation;
import com.arianit.citybe.mapper.ReservationMapper;
import com.arianit.citybe.repository.GastronomeRepository;
import com.arianit.citybe.repository.ReservationRepository;
import com.arianit.citybe.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final GastronomeRepository gastronomeRepository;
    private final ReservationMapper reservationMapper;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, UserRepository userRepository,
                              GastronomeRepository gastronomeRepository, ReservationMapper reservationMapper) {
        this.reservationRepository = reservationRepository;
        this.gastronomeRepository = gastronomeRepository;
        this.reservationMapper = reservationMapper;
    }

    public ReservationDto saveReservation(Long gastronomeId, Reservation reservation) {
        Gastronome gastronome = gastronomeRepository.findById(gastronomeId)
                .orElseThrow(()-> new EntityNotFoundException("Gastronome not found "+gastronomeId));
        reservation.setGastronome(gastronome);
        reservationRepository.save(reservation);
        return reservationMapper.mapEntityToDto(reservation);
    }

    public List<ReservationDto> getReservationsByGastronomeId(Long gastronomeId){
        List<Reservation> reservations = reservationRepository.findByGastronomeGastronomeId(gastronomeId);
        return reservations.stream()
                .map(reservationMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    public ReservationDto getReservationById(Long gastronomeId, Long reservationId) {
        Gastronome gastronome = gastronomeRepository.findById(gastronomeId)
                .orElseThrow(()-> new EntityNotFoundException("Gastronome not found "+gastronomeId));
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(()-> new EntityNotFoundException("Reservation not found "+ reservationId));

        if(!reservation.getGastronome().getGastronomeId().equals(gastronome.getGastronomeId())){
            throw new IllegalArgumentException("Reservation doesn't belong to Gastronome");
        }
        return reservationMapper.mapEntityToDto(reservation);
    }


    @Transactional
    public void deleteReservation(Long gastronomeId, Long reservationId) {

        Gastronome gastronome = gastronomeRepository.findById(gastronomeId)
                .orElseThrow(()-> new EntityNotFoundException("Gastronome not found "+gastronomeId));
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(()-> new EntityNotFoundException("Reservation not found "+ reservationId));

        if(!reservation.getGastronome().getGastronomeId().equals(gastronome.getGastronomeId())){
            throw new IllegalArgumentException("Reservation doesn't belong to Gastronome");
        }
        reservationRepository.delete(reservation);
    }

    public ReservationDto updateReservation(Long gastronomeId, Long reservationId, Reservation reservation) {
        Gastronome gastronome = gastronomeRepository.findById(gastronomeId)
                .orElseThrow(()-> new EntityNotFoundException("Gastronome not found "+gastronomeId));
        Reservation existingReservation = reservationRepository.findById(reservationId)
                .orElseThrow(()-> new EntityNotFoundException("Reservation not found "+ reservationId));

        if(!existingReservation.getGastronome().getGastronomeId().equals(gastronome.getGastronomeId())){
            throw new IllegalArgumentException("Reservation doesn't belong to Gastronome");
        }

        if (reservation.getNumberOfPeople() != null) {
            existingReservation.setNumberOfPeople(reservation.getNumberOfPeople());
        }
        if (reservation.getPhoneNumber() != null) {
            existingReservation.setPhoneNumber(reservation.getPhoneNumber());
        }
        if (reservation.getSpecialRequests() != null) {
            existingReservation.setSpecialRequests(reservation.getSpecialRequests());
        }
        if (reservation.getStatus() != null) {
            existingReservation.setStatus(reservation.getStatus());
        }

        existingReservation = reservationRepository.save(existingReservation);
        return reservationMapper.mapEntityToDto(existingReservation);
    }
}