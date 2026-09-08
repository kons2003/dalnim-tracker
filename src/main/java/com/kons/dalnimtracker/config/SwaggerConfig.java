package com.kons.dalnimtracker.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("달님이 트래커 API")
                        .description("대학내 특정 고양이 트래커 프로젝트 백엔드 API 명세서입니다.")
                        .version("v1.0.0"));
    }
}
