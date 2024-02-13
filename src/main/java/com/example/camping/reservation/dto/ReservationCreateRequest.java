package com.example.camping.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationCreateRequest {
    private Long userId;
    private Long campgroundId;
    private LocalDate reservationDate;
    private Long numberOfPeople;

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
