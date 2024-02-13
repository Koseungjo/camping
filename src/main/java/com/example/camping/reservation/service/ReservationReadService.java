package com.example.camping.reservation.service;

import com.example.camping.reservation.dto.ReservationDetailResponse;
import com.example.camping.reservation.dto.ReservationListResponse;
import com.example.camping.reservation.dto.ReservationResponse;
import com.example.camping.reservation.dto.ReservationSearchRequest;
import com.example.camping.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationReadService {

    private final ReservationRepository reservationRepository;
    public ReservationListResponse getReservationList(Pageable pageable, ReservationSearchRequest request) {
        return ReservationListResponse.toDto(reservationRepository.getReservationList(
                pageable, request.getUserId(), request.getCampgroundName(),
                request.getStartDate(), request.getEndDate()).map(ReservationResponse::toResponse));
    }

    public ReservationDetailResponse getReservationDetail(Long id, Long userId) {
        return ReservationDetailResponse.toDto(reservationRepository.findByIdAndUserId(id, userId).orElseThrow(
                () -> new NoSuchElementException("존재하지 않는 예약 입니다.")
        ));
    }
}
