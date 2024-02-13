package com.example.camping.campground.controller;

import com.example.camping.campground.dto.CampgroundCreateRequest;
import com.example.camping.campground.dto.CampgroundDetailResponse;
import com.example.camping.campground.dto.CampgroundListResponse;
import com.example.camping.campground.dto.CampgroundUpdateRequest;
import com.example.camping.campground.service.CampgroundCUDService;
import com.example.camping.campground.service.CampgroundReadService;
import com.example.camping.global.annotation.CurrentUserId;
import com.example.camping.global.config.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/camp")
public class CampgroundApiController {

    private final CampgroundCUDService campgroundCUDService;
    private final CampgroundReadService campgroundReadService;

    @PostMapping("/create")
    public ResponseDTO<Long> campgroundCreate(
            @RequestBody CampgroundCreateRequest request,
            @CurrentUserId Long userId
    ) {
        return ResponseDTO.ok(campgroundCUDService.campgroundCreate(request,userId));
    }

    @GetMapping("/list")
    public ResponseDTO<CampgroundListResponse> getCampgroundList(
            @PageableDefault(size=20, sort="name",direction= Sort.Direction.ASC) Pageable pageable,
            @Param("campgroundName") String campgroundName
    ) {
        return ResponseDTO.ok(campgroundReadService.getCampgroundList(pageable, campgroundName));
    }

    @GetMapping("/{id}")
    public ResponseDTO<CampgroundDetailResponse> getCampgroundDetail(@PathVariable Long id) {
        return ResponseDTO.ok(campgroundReadService.getCampgroundDetail(id));
    }

    @DeleteMapping("/{id}")
    public ResponseDTO deleteCampground(
            @PathVariable Long id,
            @CurrentUserId Long userId
    ) {
        campgroundCUDService.deleteCampground(id, userId);
        return ResponseDTO.ok();
    }

    @PatchMapping("/{id}")
    public ResponseDTO<CampgroundDetailResponse> updateCampground(
            @PathVariable Long id,
            @RequestBody CampgroundUpdateRequest request,
            @CurrentUserId Long userId
    ) {
        return ResponseDTO.ok(campgroundCUDService.updateCampground(id, request, userId));
    }
}