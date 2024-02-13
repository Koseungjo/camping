package com.example.camping.campground.dto;

import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class CampgroundListResponse {

    private final Page<CampgroundResponse> campgroundResponse;

    private CampgroundListResponse(Page<CampgroundResponse> campgroundResponse) {
        this.campgroundResponse = campgroundResponse;
    }

    public static CampgroundListResponse toDto(Page<CampgroundResponse> campgroundResponse){
        return new CampgroundListResponse(campgroundResponse);
    }

}
