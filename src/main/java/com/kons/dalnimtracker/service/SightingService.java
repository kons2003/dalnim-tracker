package com.kons.dalnimtracker.service;

import com.kons.dalnimtracker.domain.Sighting;
import com.kons.dalnimtracker.dto.PasswordRequestDto;
import com.kons.dalnimtracker.dto.SightingRequestDto;
import com.kons.dalnimtracker.dto.SightingResponseDto;
import com.kons.dalnimtracker.dto.SightingUpdateRequestDto;
import com.kons.dalnimtracker.repository.SightingRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    // 제보 글 전체 조회 로직
    public Page<SightingResponseDto> getAllSightings(String catStatus, Pageable pageable) {
        Page<Sighting> sightings;

        // 상태값이 파라미터로 넘어왔다면 필터링 조회, 없다면 전체 조회
        if (catStatus != null && !catStatus.isBlank()) {
            sightings = sightingRepository.findByCatStatus(catStatus, pageable);
        } else {
            sightings = sightingRepository.findAll(pageable);
        }
        return sightings.map(SightingResponseDto::new);
    }

    // 제보 글 수정 로직
    @Transactional
    public SightingResponseDto updateSighting(Integer id, SightingUpdateRequestDto requestDto) {
        Sighting sighting = sightingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=" + id));
        sighting.validatePassword(requestDto.getEditPassword());
        sighting.update(requestDto);
        return new SightingResponseDto(sighting);
    }

    // 제보 글 삭제 로직
    @Transactional
    public void deleteSighting(Integer id, PasswordRequestDto requestDto) {
        Sighting sighting = sightingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=" + id));

        sighting.validatePassword(requestDto.getEditPassword());
        sightingRepository.delete(sighting);
    }
}
