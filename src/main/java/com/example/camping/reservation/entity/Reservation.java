package com.example.camping.reservation.entity;

import com.example.camping.campground.entity.Campground;
import com.example.camping.global.entity.BaseTimeEntity;
import com.example.camping.reservation.dto.ReservationCreateRequest;
import com.example.camping.reservation.enums.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reservation extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campground_id")
    private Campground campground;
    private LocalDate reservationDate;
    private Long numberOfPeople;
    private String status;

    public static Reservation toEntity(ReservationCreateRequest request, Campground campground){
        return Reservation.builder()
                .userId(request.getUserId())
                .campground(campground)
                .reservationDate(request.getReservationDate())
                .numberOfPeople(request.getNumberOfPeople())
                .status(ReservationStatus.PENDING.getCode())
                .build();
    }

    public void cancel() {
        this.status = ReservationStatus.CANCELLED.getCode();
    }
}
