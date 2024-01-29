package com.example.camping.campground.entity;

import com.example.camping.campground.dto.CampgroundCreateRequest;
import com.example.camping.campground.dto.CampgroundUpdateRequest;
import com.example.camping.global.entity.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Campground extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String name;
    private String location;
    private String description;


    public static Campground toEntity(CampgroundCreateRequest request, Long userId){
        return Campground.builder()
                .name(request.getName())
                .userId(userId)
                .location(request.getLocation())
                .description(request.getDescription())
                .build();
    }

    public void update(CampgroundUpdateRequest request) {
        this.name = request.getName();
        this.location = request.getDescription();
        this.description = request.getDescription();

    }
}
