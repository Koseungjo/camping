package com.example.camping.reservation.dto;

import com.example.camping.reservation.entity.Reservation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationResponse {
    private Long id;
    private Long userId;
    private String campgroundName;
    private LocalDate reservationDate;
    private Long numberOfPeople;

    public static ReservationResponse toResponse(Reservation reservation) {
        return ReservationResponse.builder()
                .id(reservation.getId())
                .userId(reservation.getUserId())
                .campgroundName(reservation.getCampground().getName())
                .reservationDate(reservation.getReservationDate())
                .numberOfPeople(reservation.getNumberOfPeople())
                .build();
    }
}
