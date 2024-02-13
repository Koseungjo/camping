package com.example.camping.campground.service;

import com.example.camping.campground.dto.CampgroundCreateRequest;
import com.example.camping.campground.dto.CampgroundDetailResponse;
import com.example.camping.campground.dto.CampgroundUpdateRequest;
import com.example.camping.campground.entity.Campground;
import com.example.camping.campground.repository.CampgroundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class CampgroundCUDService {

    private final CampgroundRepository campgroundRepository;

    public Long campgroundCreate(CampgroundCreateRequest request, Long userId) {
        return campgroundRepository.save(Campground.toEntity(request, userId)).getId();
    }

    public void deleteCampground(Long id, Long userId) {
        Campground campground = campgroundRepository.findByIdAndUserId(id, userId).orElseThrow(
                () -> new NoSuchElementException("존재하지 않는 캠핑장 입니다,")
        );

        campgroundRepository.delete(campground);
    }

    public CampgroundDetailResponse updateCampground(Long id, CampgroundUpdateRequest request, Long userId) {
        Campground campground = campgroundRepository.findByIdAndUserId(id, userId).orElseThrow(
                () -> new NoSuchElementException("존재하지 않는 캠핑장 입니다,")
        );
        return CampgroundDetailResponse.toDto(campground.update(request));
    }
}
