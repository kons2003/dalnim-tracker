package com.kons.dalnimtracker.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class SightingUpdateRequestDto {
    private Double latitude;
    private Double longitude;
    private String locationDescription;
    private String content;
    private String catStatus;
    private String imageUrl;
    private String editPassword;
}
