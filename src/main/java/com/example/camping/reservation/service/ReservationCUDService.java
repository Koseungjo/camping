package com.example.camping.reservation.service;

import com.example.camping.campground.entity.Campground;
import com.example.camping.campground.repository.CampgroundRepository;
import com.example.camping.reservation.dto.ReservationCreateRequest;
import com.example.camping.reservation.dto.ReservationDetailResponse;
import com.example.camping.reservation.dto.ReservationResponse;
import com.example.camping.reservation.entity.Reservation;
import com.example.camping.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ReservationCUDService {

    private final ReservationRepository reservationRepository;
    private final CampgroundRepository campgroundRepository;
    public ReservationResponse reservationCreate(ReservationCreateRequest request) {
        Campground campground = campgroundRepository.findById(request.getCampgroundId()).orElseThrow(
                () -> new NoSuchElementException("존재하지 않는 캠핑장 입니다,")
        );
        Reservation reservation = Reservation.toEntity(request, campground);
        reservationRepository.save(reservation);
        return ReservationResponse.toResponse(reservation);
    }

    public ReservationDetailResponse cancelReservation(Long id, Long userId) {
        Reservation reservation = reservationRepository.findByIdAndUserId(id, userId).orElseThrow(
                () -> new NoSuchElementException("존재하지 않는 예약 입니다.")
        );
        reservation.cancel();
        return ReservationDetailResponse.toDto(reservation);
    }

    public void deleteReservation(Long id, Long userId) {
        Reservation reservation = reservationRepository.findByIdAndUserId(id, userId).orElseThrow(
                () -> new NoSuchElementException("존재하지 않는 예약 입니다.")
        );
        reservationRepository.delete(reservation);
    }
}
