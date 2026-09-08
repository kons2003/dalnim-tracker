package com.kons.dalnimtracker.controller;

import com.kons.dalnimtracker.dto.PasswordRequestDto;
import com.kons.dalnimtracker.dto.SightingRequestDto;
import com.kons.dalnimtracker.dto.SightingResponseDto;
import com.kons.dalnimtracker.dto.SightingUpdateRequestDto;
import com.kons.dalnimtracker.service.SightingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // JSON 형태의 데이터를 주고 받는 REST API 컨트롤러임을 명시
@RequestMapping("/api/sightings")
@RequiredArgsConstructor // 의존성 자동 주입
public class SightingController {

    private final SightingService sightingService;

    // 제보 글 등록 API
    @PostMapping
    public ResponseEntity<SightingResponseDto> createSighting(@RequestBody SightingRequestDto requestDto) {
        SightingResponseDto responseDto = sightingService.createSighting(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    // 제보 글 전체 목록 최신순 조회 API
    @GetMapping
    public ResponseEntity<List<SightingResponseDto>> getAllSightings() {
        List<SightingResponseDto> responseList = sightingService.getAllSightings();
        return ResponseEntity.ok(responseList);
    }

    // 제보 글 수정 API
    @PutMapping("/{id}")
    public ResponseEntity<SightingResponseDto> updateSighting(
            @PathVariable("id") Integer id,
            @RequestBody SightingUpdateRequestDto requestDto) {
        SightingResponseDto responseDto = sightingService.updateSighting(id, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    // 제보 글 삭제 API
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSighting(
            @PathVariable("id") Integer id,
            @RequestBody PasswordRequestDto requestDto) {
        sightingService.deleteSighting(id, requestDto);
        return ResponseEntity.noContent().build();
    }
}
