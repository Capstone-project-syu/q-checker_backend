package syu.qchecker.email.ui;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import syu.qchecker.user.domain.User;
import syu.qchecker.email.dto.EmailRequestDto;

@RestController
@RequestMapping("/api/email")
@Tag(name = "이메일 인증", description = "이메일 인증 관련 API")
@RequiredArgsConstructor
public class EmailController {

    @PostMapping("/request")
    @Operation(
            summary = "인증번호 요청",
            description = "이메일로 인증번호 발송"
    )
    public ResponseEntity<Void> sendEmail(@RequestBody EmailRequestDto dto) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/verify")
    @Operation(
            summary = "인증번호 검증",
            description = "입력한 인증번호 검증"
    )
    public ResponseEntity<Void> verifyEmail(@RequestBody EmailRequestDto dto) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/status")
    @Operation(
            summary = "상태 조회",
            description = "이메일 인증 상태 조회"
    )
    public ResponseEntity<Void> getEmailStatus(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok().build();
    }
}
