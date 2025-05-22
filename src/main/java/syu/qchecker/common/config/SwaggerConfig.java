package syu.qchecker.common.config;

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
        return new OpenAPI()
                .info(new Info()
                        .title("API 명세서")
                        .version("1.0")
                        .description("API 명세서입니다.")
                        .contact(new Contact()
                                .name("관리자")
                                .email("admin@example.com")))
                .servers(List.of(new Server().url("http://localhost:8080")))
                .tags(List.of(
                        new Tag().name("마이페이지").description("마이페이지 관련 API"),
                        new Tag().name("출석 기록").description("출석 기록 관련 API"),
                        new Tag().name("이벤트 관리").description("이벤트 관리 관련 API"),
                        new Tag().name("소셜 로그인").description("소셜 로그인 관련 API")
                ));
    }
}
