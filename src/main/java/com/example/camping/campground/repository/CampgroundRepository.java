package com.example.camping.campground.repository;

import com.example.camping.campground.entity.Campground;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CampgroundRepository extends JpaRepository<Campground, Long> {
    Optional<Campground> findByIdAndUserId(Long id, Long userId);


    @Query("select c from Campground c where c.name like concat('%', :name, '%')")
    Page<Campground> findByName(Pageable pageable, String name);


}
