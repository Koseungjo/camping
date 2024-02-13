package com.example.camping.global.provider;

import com.example.camping.reservation.entity.Reservation;
import lombok.extern.slf4j.Slf4j;

public interface EmailProvider {

    void send(Reservation reservation);

    @Slf4j
    class Fake implements EmailProvider {

        @Override
        public void send(Reservation reservation) {
            log.info("{} 유저 전송 완료 ! \n 위치 : {} \n 인원 : {}", reservation.getUserId(), reservation.getCampground().getLocation(), reservation.getNumberOfPeople());
        }

    }
}
