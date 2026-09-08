package com.kons.dalnimtracker.config;

import com.kons.dalnimtracker.domain.Sighting;
import com.kons.dalnimtracker.repository.SightingRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitConfig {

    @Bean
    @SuppressWarnings("unused")
    public CommandLineRunner initDatabase(SightingRepository sightingRepository) {
        return args -> {
            // DB에 데이터가 하나도 없을 때만 더미 데이터 삽입 (재시작 시 무한 증식 방지)
            if (sightingRepository.count() == 0) {
                Sighting dummy1 = Sighting.builder()
                        .latitude(37.5665)
                        .longitude(126.9780)
                        .locationDescription("학생회관 앞 벤치")
                        .content("달님이 햇빛 쬐면서 자고 있어요!")
                        .catStatus("SLEEPING")
                        .imageUrl("https://example.com/cat1.jpg")
                        .reporterName("컴공냥이")
                        .editPassword("password123")
                        .build();

                Sighting dummy2 = Sighting.builder()
                        .latitude(37.5661)
                        .longitude(126.9785)
                        .locationDescription("도서관 뒷골목")
                        .content("누가 츄르를 주고 갔네요. 맛있게 먹고 있습니다.")
                        .catStatus("EATING")
                        .imageUrl("https://example.com/cat2.jpg")
                        .reporterName("경영냥이")
                        .editPassword("password123")
                        .build();

                sightingRepository.save(dummy1);
                sightingRepository.save(dummy2);
            }
        };
    }
}
