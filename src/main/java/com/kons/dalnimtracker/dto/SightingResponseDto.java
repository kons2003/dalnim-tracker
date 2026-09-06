package com.kons.dalnimtracker.dto;

import com.kons.dalnimtracker.domain.Sighting;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SightingResponseDto { // 서버 -> 사용자
    private Integer id;
    private Double latitude;
    private Double longitude;
    private String locationDescription;
    private String content;
    private String catStatus;
    private String imageUrl;
    private String reporterName;
    private LocalDateTime createdAt;

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
