package com.example.camping.reservation.enums;

import lombok.Getter;

@Getter
public enum ReservationStatus {
    CONFIRMED("CONFIRMED","확정"),
    CANCELLED("CANCELLED","취소"),
    PENDING("PENDING","대기 중"),
    COMPLETED("COMPLETED","완료");

    private final String code;
    private final String description;

    ReservationStatus(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
