package com.example.camping.campground.service;

import com.example.camping.campground.entity.Campground;
import com.example.camping.campground.repository.CampgroundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CampgroundReadService {

    private final CampgroundRepository campgroundRepository;
    public Page<Campground> getCampgroundList(Pageable pageable) {
        return campgroundRepository.findAll(pageable);
    }

    public Campground getCampgroundDetail(Long id) {
        return campgroundRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("존재하지 않는 캠핑장 입니다,")
        );
    }
}
