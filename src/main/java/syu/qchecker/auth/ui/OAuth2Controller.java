package syu.qchecker.auth.ui;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "인증", description = "인증 관련 API")
@RequiredArgsConstructor
public class OAuth2Controller {

    @GetMapping("/google")
    public void loginGoogle(HttpServletResponse response) throws IOException {
        response.sendRedirect("/oauth2/authorization/google");
    }

    @GetMapping("/login-success")
    public String loginSuccess() {
        return "로그인 성공";
    }
} 
