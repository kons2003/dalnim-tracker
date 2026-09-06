package com.kons.dalnimtracker.service;

import com.kons.dalnimtracker.domain.Sighting;
import com.kons.dalnimtracker.dto.SightingRequestDto;
import com.kons.dalnimtracker.dto.SightingResponseDto;
import com.kons.dalnimtracker.repository.SightingRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // 인터페이스 가져올때 의존성 주입
public class SightingService {

    private final SightingRepository sightingRepository;

    // 제보 글 저장 로직
    @Transactional // 데이터베이스 상태 변경 작업 중 에러 발생 시 롤백
    public SightingResponseDto createSighting(SightingRequestDto requestDto) {
        Sighting sighting = new Sighting(requestDto);
        Sighting savedSighting = sightingRepository.save(sighting);
        return new SightingResponseDto(savedSighting);
    }

    // 전체 제보 글 최신순 조회 로직
    @Transactional(readOnly = true)
    public List<SightingResponseDto> getAllSightings() {
        return sightingRepository.findAllByOrderByIdDesc().stream()
                .map(SightingResponseDto::new)
                .collect(Collectors.toList());
    }
}
