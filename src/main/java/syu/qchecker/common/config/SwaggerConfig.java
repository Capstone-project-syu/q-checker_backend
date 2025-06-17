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
                .info(new Info().title("How(HomeWork out) API")
                        .description("This is How API")
                        .version("v0.0.1")
                        .description("API 명세서입니다.")
                        .contact(new Contact()
                                .name("관리자")
                                .email("admin@example.com")))
                .servers(List.of(
                        new Server().url("https://api.qchecker.me").description("배포 서버"),
                        new Server().url("http://localhost:8080").description("로컬 서버")
                ))

                .tags(List.of(
                        new Tag().name("마이페이지").description("마이페이지 관련 API"),
                        new Tag().name("출석 기록").description("출석 기록 관련 API")
                        ));
    }

    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }
}
