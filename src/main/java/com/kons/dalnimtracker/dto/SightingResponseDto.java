package com.kons.dalnimtracker.dto;

import com.kons.dalnimtracker.domain.Sighting;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SightingResponseDto { // 서버 -> 사용자
    private final Integer id;
    private final Double latitude;
    private final Double longitude;
    private final String locationDescription;
    private final String content;
    private final String catStatus;
    private final String imageUrl;
    private final String reporterName;
    private final LocalDateTime createdAt;

    // Entity를 받아서 안전한 DTO로 변환해 주는 생성자
    public SightingResponseDto(Sighting entity) {
        this.id = entity.getId();
        this.latitude = entity.getLatitude();
        this.longitude = entity.getLongitude();
        this.locationDescription = entity.getLocationDescription();
        this.content = entity.getContent();
        this.catStatus = entity.getCatStatus();
        this.imageUrl = entity.getImageUrl();
        this.reporterName = entity.getReporterName();
        this.createdAt = entity.getCreatedAt();
    }
}
