package syu.qchecker.email.ui;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import syu.qchecker.email.dto.EmailRequestDto;
import syu.qchecker.email.dto.EmailResponseDto;
import syu.qchecker.email.service.EmailService;
import syu.qchecker.user.domain.User;

@RestController
@RequestMapping("/api/email")
@Tag(name = "대학교 이메일 인증", description = "대학생 인증을 위한 대학교 이메일 인증 API")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/request")
    @Operation(
            summary = "대학교 이메일 인증번호 요청",
            description = "대학교 이메일로 인증번호를 발송합니다."
    )
    public ResponseEntity<EmailResponseDto> sendVerificationCode(@RequestBody EmailRequestDto dto) {
        EmailResponseDto response = emailService.sendVerificationCode(dto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/verify")
    @Operation(
            summary = "인증번호 검증",
            description = "이메일로 받은 인증번호를 검증합니다."
    )
    public ResponseEntity<EmailResponseDto> verifyCode(@RequestBody EmailRequestDto emailRequestDto) {
        EmailResponseDto response = emailService.verifyCode(emailRequestDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/status")
    @Operation(
            summary = "이메일 인증 상태 조회",
            description = "현재 로그인한 사용자의 이메일 인증 상태를 조회합니다."
    )
    public ResponseEntity<EmailResponseDto> getEmailVerificationStatus(@AuthenticationPrincipal User user) {
        EmailResponseDto response = emailService.getVerificationStatus(user.getEmail());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/status/{email}")
    @Operation(
            summary = "특정 이메일 인증 상태 조회",
            description = "특정 이메일의 인증 상태를 조회합니다."
    )
    public ResponseEntity<EmailResponseDto> getEmailVerificationStatus(@PathVariable String email) {
        EmailResponseDto response = emailService.getVerificationStatus(email);
        return ResponseEntity.ok(response);
    }
}
