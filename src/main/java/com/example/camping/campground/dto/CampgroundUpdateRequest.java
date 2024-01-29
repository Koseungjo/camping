package com.example.camping.campground.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CampgroundUpdateRequest {
    private String name;
    private String location;
    private String description;
}
