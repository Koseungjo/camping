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
public class ReservationDetailResponse {
    private Long id;

    private Long userId;
    private String campgroundName;
    private String campgroundLocation;
    private String campgroundDescription;
    private LocalDate reservationDate;
    private Long numberOfPeople;
    private String status;

    public static ReservationDetailResponse toDto(Reservation reservation) {
        return ReservationDetailResponse.builder()
                .id(reservation.getId())
                .userId(reservation.getUserId())
                .campgroundName(reservation.getCampground().getName())
                .campgroundLocation(reservation.getCampground().getLocation())
                .campgroundDescription(reservation.getCampground().getDescription())
                .reservationDate(reservation.getReservationDate())
                .numberOfPeople(reservation.getNumberOfPeople())
                .status(reservation.getStatus())
                .build();
    }
}
