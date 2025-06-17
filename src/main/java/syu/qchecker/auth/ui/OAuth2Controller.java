package syu.qchecker.auth.ui;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import syu.qchecker.auth.dto.SessionUserDto;
import syu.qchecker.auth.jwt.JwtTokenProvider;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "인증", description = "인증 관련 API")
@RequiredArgsConstructor
public class OAuth2Controller {

    private final JwtTokenProvider jwtTokenProvider; // ✅ 주입 필요

    @GetMapping("/google")
    public void loginGoogle(HttpServletResponse response) throws IOException {
        response.sendRedirect("/oauth2/authorization/google");
    }

    @GetMapping("/login-success")
    public ResponseEntity<?> loginSuccess(HttpSession session) {
        SessionUserDto user = (SessionUserDto) session.getAttribute("user");
        if (user == null) return ResponseEntity.status(401).body("Unauthorized");

        String jwt = jwtTokenProvider.createToken(user.getEmail());
        return ResponseEntity.ok().body(Map.of("token", jwt));
    }
}
