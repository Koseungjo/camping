package com.example.camping.batch;

import com.example.camping.global.provider.EmailProvider;
import com.example.camping.reservation.entity.Reservation;
import com.example.camping.reservation.repository.ReservationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Component
public class BatchScheduler {

    private ReservationRepository reservationRepository;
    private EmailProvider emailProvider;

    public BatchScheduler(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
        this.emailProvider = new EmailProvider.Fake();
    }

    @Scheduled(cron = "0 0 7 * * *")
    public void runJob() {
        LocalDate today = LocalDate.now();
        List<Reservation> reservationList = reservationRepository.getTodayReservation(today);
        if (!reservationList.isEmpty()){
            reservationList.forEach(emailProvider::send);
        }
    }
}