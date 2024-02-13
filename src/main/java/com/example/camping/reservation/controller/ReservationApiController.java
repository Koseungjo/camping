package com.example.camping.reservation.controller;

import com.example.camping.global.annotation.CurrentUserId;
import com.example.camping.global.config.ResponseDTO;
import com.example.camping.reservation.dto.*;
import com.example.camping.reservation.service.ReservationCUDService;
import com.example.camping.reservation.service.ReservationReadService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservation")
public class ReservationApiController {

    private final ReservationCUDService reservationCUDService;
    private final ReservationReadService reservationReadService;

    @PostMapping("/create")
    public ResponseDTO<ReservationResponse> reservationCreate(
            @RequestBody ReservationCreateRequest request,
            @CurrentUserId Long userId
    ) {
        request.setUserId(userId);
        ReservationResponse response = reservationCUDService.reservationCreate(request);
        return ResponseDTO.ok(response);
    }

    @GetMapping("/list")
    public ResponseDTO<ReservationListResponse> getReservationList(
            @PageableDefault(size = 20, sort = "reservationDate", direction = Sort.Direction.ASC) Pageable pageable,
            @ModelAttribute ReservationSearchRequest request,
            @CurrentUserId Long userId
    ) {
        request.setUserId(userId);
        ReservationListResponse response = reservationReadService.getReservationList(pageable, request);
        return ResponseDTO.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseDTO<ReservationDetailResponse> getReservationDetail(
            @PathVariable Long id,
            @CurrentUserId Long userId
    ) {
        ReservationDetailResponse response = reservationReadService.getReservationDetail(id, userId);
        return ResponseDTO.ok(response);
    }

    @PatchMapping("/{id}/cancel")
    public ResponseDTO<ReservationDetailResponse> cancelReservation(@PathVariable Long id, @CurrentUserId Long userId) {
        ReservationDetailResponse response = reservationCUDService.cancelReservation(id, userId);
        return ResponseDTO.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseDTO deleteReservation(@PathVariable Long id, @CurrentUserId Long userId) {
        reservationCUDService.deleteReservation(id, userId);
        return ResponseDTO.ok();
    }
}
