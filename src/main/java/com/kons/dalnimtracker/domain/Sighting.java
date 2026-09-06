package com.kons.dalnimtracker.domain;

import com.kons.dalnimtracker.dto.SightingRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity // JPA가 관리하는 엔티티 선언
@Getter // Getter 메서드 자동 생성
@NoArgsConstructor // 파라미터가 없는 기본 생성자 자동 생성
@Table(name = "sighting") // 매핑될 DB 테이블의 이름 지정
public class Sighting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Double latitude;            // 목격 장소 위도

    @Column(nullable = false)
    private Double longitude;           // 목격 장소 경도

    @Column(name = "location_description", length = 100)
    private String locationDescription; // 장소 부가 설명 (예: "도서관 앞 벤치")

    @Column(nullable = false, length = 500)
    private String content;             // 제보 내용

    @Column(name = "cat_status", length = 20)
    private String catStatus;           // 달님이 상태 (예: SLEEPING, EATING)

    @Column(name = "image_url")
    private String imageUrl;            // 사진 URL

    @Column(name = "reporter_name", nullable = false)
    private String reporterName;        // 제보자 닉네임

    @Column(name = "edit_password", nullable = false)
    private String editPassword;        // 익명 게시글 수정/삭제용 비밀번호

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;    // 등록 시간

    @PrePersist // 데이터베이스에 최초로 저장 되기 직전 메서드 자동 실행
    public void prePersist() { // 자동 시간 입력
        this.createdAt = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
    }

    public Sighting(SightingRequestDto requestDto) {
        this.latitude = requestDto.getLatitude();
        this.longitude = requestDto.getLongitude();
        this.locationDescription = requestDto.getLocationDescription();
        this.content = requestDto.getContent();
        this.catStatus = requestDto.getCatStatus();
        this.imageUrl = requestDto.getImageUrl();
        this.reporterName = requestDto.getReporterName();
        this.editPassword = requestDto.getEditPassword();
    }
}
