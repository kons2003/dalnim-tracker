package com.kons.dalnimtracker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kons.dalnimtracker.dto.SightingRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest // 스프링부트의 모든 빈(Bean)과 DataInitConfig를 로드하여 실제와 유사한 환경 구성
@AutoConfigureMockMvc // 가상 API 요청을 위한 MockMvc 객체 자동 주입
@Transactional
class SightingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("새로운 목격 정보를 성공적으로 등록한다")
    void createSighting_ReturnsCreatedData() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper(); // 데이터형식 JSON으로 변환

        SightingRequestDto requestDto = SightingRequestDto.builder()
                .latitude(37.5665)
                .longitude(126.9780)
                .locationDescription("중앙도서관 계단")
                .content("달님이 식빵 굽고 있어요!")
                .catStatus("SLEEPING")
                .reporterName("테스트냥이")
                .editPassword("1234")
                .build();

        mockMvc.perform(post("/api/sightings")
                        .contentType(MediaType.APPLICATION_JSON) // JSON 형태로 보낸다고 명시
                        .content(objectMapper.writeValueAsString(requestDto))) // 객체를 JSON 문자열로 변환하여 본문에 탑재
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists()) // 데이터베이스에 저장되며 새로운 ID가 발급되었는지 확인
                .andExpect(jsonPath("$.reporterName").value("테스트냥이")) // 보낸 데이터가 그대로 잘 응답되는지 확인
                .andExpect(jsonPath("$.locationDescription").value("중앙도서관 계단"));
    }

    @Test
    @DisplayName("상태값을 필터링하여 페이징된 목격 정보를 정상 조회한다")
    void getSightings_WithFilter_ReturnsPagedData() throws Exception {
        mockMvc.perform(get("/api/sightings")
                        .param("catStatus", "SLEEPING"))
                .andExpect(status().isOk()) // HTTP 상태 코드가 200(OK)인지 확인
                .andExpect(jsonPath("$.content").exists()) // 응답 JSON에 content 데이터 배열이 있는지 확인
                .andExpect(jsonPath("$.pageable").exists()) // 페이징 메타데이터가 정상 출력되는지 확인
                .andExpect(jsonPath("$.content[0].catStatus").value("SLEEPING")); // 필터링이 정확히 적용되었는지 확인
    }
}