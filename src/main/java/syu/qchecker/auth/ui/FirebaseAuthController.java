package syu.qchecker.auth.ui;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import syu.qchecker.auth.dto.AuthResponseDto;
import syu.qchecker.auth.dto.FirebaseLoginRequestDto;
import syu.qchecker.auth.service.FirebaseAuthService;

@Tag(name = "Firebase Auth", description = "Firebase 인증 API")
@RestController
@RequestMapping("/api/auth/firebase")
@RequiredArgsConstructor
public class FirebaseAuthController {

    private final FirebaseAuthService firebaseAuthService;

    @Operation(summary = "Firebase 로그인", description = "Google/Kakao Firebase ID 토큰을 사용한 로그인")
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody FirebaseLoginRequestDto request) {
        AuthResponseDto response = firebaseAuthService.authenticateUser(request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Google 로그인", description = "Google Firebase ID 토큰을 사용한 로그인")
    @PostMapping("/google")
    public ResponseEntity<AuthResponseDto> googleLogin(@RequestBody FirebaseLoginRequestDto request) {
        request.setProvider("google");
        AuthResponseDto response = firebaseAuthService.authenticateUser(request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Kakao 로그인", description = "Kakao Firebase ID 토큰을 사용한 로그인")
    @PostMapping("/kakao")
    public ResponseEntity<AuthResponseDto> kakaoLogin(@RequestBody FirebaseLoginRequestDto request) {
        request.setProvider("kakao");
        AuthResponseDto response = firebaseAuthService.authenticateUser(request);
        return ResponseEntity.ok(response);
    }
}