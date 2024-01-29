package com.example.camping.campground.controller;

import com.example.camping.campground.dto.CampgroundCreateRequest;
import com.example.camping.campground.dto.CampgroundUpdateRequest;
import com.example.camping.campground.entity.Campground;
import com.example.camping.campground.service.CampgroundCUDService;
import com.example.camping.campground.service.CampgroundReadService;
import com.example.camping.global.annotation.CurrentUserId;
import com.example.camping.global.config.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/camp")
public class CampgroundController {

    private final CampgroundCUDService campgroundCUDService;
    private final CampgroundReadService campgroundReadService;

    @PostMapping("/create")
    public ResponseDTO<Campground> campgroundCreate(
            @RequestBody CampgroundCreateRequest request,
            @CurrentUserId Long userId
    ) {
        return ResponseDTO.ok(campgroundCUDService.campgroundCreate(request,userId));
    }

    @GetMapping("/list")
    public ResponseDTO<Page<Campground>> getCampgroundList(
            @PageableDefault(size=20, sort="name",direction= Sort.Direction.ASC) Pageable pageable
    ) {
        return ResponseDTO.ok(campgroundReadService.getCampgroundList(pageable));
    }

    @GetMapping("/{id}")
    public ResponseDTO<Campground> getCampgroundDetail(@PathVariable Long id) {
        return ResponseDTO.ok(campgroundReadService.getCampgroundDetail(id));
    }

    @DeleteMapping("/{id}")
    public ResponseDTO deleteCampground(
            @PathVariable Long id,
            @CurrentUserId Long userId
    ) throws Exception {
        campgroundCUDService.deleteCampground(id, userId);
        return ResponseDTO.ok();
    }

    @PatchMapping("/{id}")
    public ResponseDTO<Campground> updateCampground(
            @PathVariable Long id,
            CampgroundUpdateRequest request,
            @CurrentUserId Long userId
    ) {
        return ResponseDTO.ok(campgroundCUDService.updateCampground(id, request, userId));
    }
}