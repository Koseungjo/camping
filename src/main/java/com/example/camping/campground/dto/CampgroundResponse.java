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
public class CampgroundResponse {
    private Long id;
    private Long userId;
    private String name;

    public static CampgroundResponse toDto(Campground campground){
        return CampgroundResponse.builder()
                .id(campground.getId())
                .userId(campground.getUserId())
                .name(campground.getName())
                .build();
    }

}
