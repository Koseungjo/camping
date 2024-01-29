package com.example.camping.campground.repository;

import com.example.camping.campground.entity.Campground;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CampgroundRepository extends JpaRepository<Campground, Long> {
    Optional<Campground> findByIdAndUserId(Long id, Long userId);

}
