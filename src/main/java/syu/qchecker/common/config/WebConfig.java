package syu.qchecker.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // QR 이미지 정적 리소스 매핑
        // QR 이미지 핸들링
        registry.addResourceHandler("/qr-images/**")
                .addResourceLocations("file:./uploads/qr/");
    }
}
