package syu.qchecker.common.config;

import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().addSecurityItem(new SecurityRequirement().addList("JWT"))
                .components(new Components().addSecuritySchemes("JWT", createAPIKeyScheme()))
                .info(new Info().title("Q-Checker API")
                        .description("QR코드 기반 대학생 출석 체크 시스템 API")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("Q-Checker Team")
                                .email("admin@qchecker.me")))
                .servers(List.of(
                        new Server().url("https://api.qchecker.me").description("배포 서버"),
                        new Server().url("http://localhost:8080").description("로컬 서버")
                ))

                .tags(List.of(
                        new Tag().name("인증").description("OAuth 로그인 관련 API"),
                        new Tag().name("마이페이지").description("사용자 정보 관리 API"),
                        new Tag().name("대학교 이메일 인증").description("대학생 인증을 위한 이메일 인증 API"),
                        new Tag().name("이벤트").description("이벤트 관리 API"),
                        new Tag().name("Qr코드 & Qr이미지").description("QR코드 생성 및 관리 API"),
                        new Tag().name("출석 기록").description("출석 기록 관리 API"),
                        new Tag().name("nfc코드").description("NFC 관련 API"),
                        new Tag().name("이메일 인증").description("이메일 인증 관련 API")
                        ));
    }

    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }
}
