package com.kons.dalnimtracker.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SightingRequestDto { // 사용자 -> 서버
    private Double latitude;
    private Double longitude;
    private String locationDescription;
    private String content;
    private String catStatus;
    private String imageUrl;
    private String reporterName;
    private String editPassword;
}
