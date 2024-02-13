package com.example.camping.reservation.dto;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class ReservationListResponse {

    private final Page<ReservationResponse> reservationResponseList;


    public ReservationListResponse(Page<ReservationResponse> reservationResponseList) {
        this.reservationResponseList = reservationResponseList;
    }

    public static ReservationListResponse toDto(Page<ReservationResponse> reservationResponseList) {
        return new ReservationListResponse(reservationResponseList);
    }

}
