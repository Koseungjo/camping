package com.example.camping.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationSearchRequest {

    private Long userId;
    private String campgroundName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long numberOfPeople;

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setStartDate(String startDate) {
        this.startDate = LocalDate.parse(startDate);
    }
    public void setEndDate(String startDate) {
        this.endDate = LocalDate.parse(startDate);
    }

}
