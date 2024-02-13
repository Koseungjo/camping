package com.example.camping.reservation.repository;

import com.example.camping.reservation.dto.ReservationResponse;
import com.example.camping.reservation.dto.ReservationSearchRequest;
import com.example.camping.reservation.entity.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query(value = "select r from Reservation r " +
            "join fetch r.campground " +
            "where r.userId = :userId " +
            "and r.campground.name like concat('%',:campgroundName,'%') " +
            "and r.reservationDate between :startDate and :endDate",
            countQuery = "select count(r) from Reservation r " +
                    "where r.userId = :userId " +
                    "and r.campground.name like concat('%',:campgroundName,'%') " +
                    "and r.reservationDate between :startDate and :endDate")
    Page<Reservation> getReservationList(Pageable pageable, Long userId, String campgroundName, LocalDate startDate, LocalDate endDate);


    @Query(value = "select r from Reservation r " +
            "where r.reservationDate = :today")
    List<Reservation> getTodayReservation(LocalDate today);

    @Query(value = "select r from Reservation r " +
            "join fetch r.campground " +
            "where r.userId = :userId " +
            "and r.id = :id")
    Optional<Reservation> findByIdAndUserId(Long id, Long userId);
}
