package com.example.camping.campground.service;

import com.example.camping.campground.dto.CampgroundCreateRequest;
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

    public Campground campgroundCreate(CampgroundCreateRequest request, Long userId) {
        return campgroundRepository.save(Campground.toEntity(request, userId));
    }

    public void deleteCampground(Long id, Long userId) throws Exception {
        Campground campground = campgroundRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("존재하지 않는 캠핑장 입니다,")
        );

        if (!campground.getUserId().equals(userId)){
            throw new Exception("삭제할 수 없습니다.");
        }

        campgroundRepository.deleteById(id);
    }

    public Campground updateCampground(Long id, CampgroundUpdateRequest request, Long userId) {
        Campground campground = campgroundRepository.findByIdAndUserId(id, userId).orElseThrow(
                () -> new NoSuchElementException("존재하지 않는 캠핑장 입니다,")
        );

        campground.update(request);
        return campground;
    }
}
