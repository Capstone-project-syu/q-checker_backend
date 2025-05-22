package syu.qchecker.common.oauth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/oauth2/authorization")
@Tag(name = "소셜 로그인", description = "소셜 로그인 관련 API")
public class AuthController {

    @PostMapping("/kakao")
    @Operation(summary = "카카오/구글 소셜 로그인", description = "카카오 또는 구글 소셜 로그인을 처리합니다.")
    public String socialLogin() {
        // ...existing code...
        return "소셜 로그인 성공";
    }
}
