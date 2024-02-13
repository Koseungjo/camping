package com.example.camping.campground.service;

import com.example.camping.campground.dto.CampgroundDetailResponse;
import com.example.camping.campground.dto.CampgroundListResponse;
import com.example.camping.campground.dto.CampgroundResponse;
import com.example.camping.campground.entity.Campground;
import com.example.camping.campground.repository.CampgroundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CampgroundReadService {

    private final CampgroundRepository campgroundRepository;
    public CampgroundListResponse getCampgroundList(Pageable pageable, String campgroundName) {
         return CampgroundListResponse.toDto(campgroundRepository.findByName(pageable, campgroundName).map(CampgroundResponse::toDto));
    }

    public CampgroundDetailResponse getCampgroundDetail(Long id) {
        Campground campground = campgroundRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("존재하지 않는 캠핑장 입니다,")
        );
        return CampgroundDetailResponse.toDto(campground);
    }
}
