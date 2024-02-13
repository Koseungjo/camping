package com.example.camping.campground.dto;

import com.example.camping.campground.entity.Campground;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampgroundDetailResponse {
    private Long id;
    private Long userId;
    private String name;
    private String location;
    private String description;

    public static CampgroundDetailResponse toDto(Campground campground) {
        return CampgroundDetailResponse.builder()
                .id(campground.getId())
                .userId(campground.getUserId())
                .name(campground.getName())
                .location(campground.getLocation())
                .description(campground.getDescription())
                .build();
    }
}
