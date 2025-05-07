package syu.qchecker.qrcode.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import syu.qchecker.qrcode.domain.Qrimage;
import syu.qchecker.qrcode.service.QrcodeService;

@RestController
@RequestMapping("/api/qrcodes")
@RequiredArgsConstructor
@Tag(name = "QR코드 관리", description = "QR코드 생성 및 조회 API")
public class QrcodeController {
    private final QrcodeService qrcodeService;

    @Operation(summary = "QR코드 생성", description = "이벤트 ID로 QR코드 이미지를 생성합니다.")
    @PostMapping("/generate/{eventId}")
    public ResponseEntity<Qrimage> generateQrCode(
            @PathVariable Long eventId,
            @AuthenticationPrincipal OAuth2User principal) {
        return ResponseEntity.ok(qrcodeService.generateQrCode(eventId, principal));
    }
}
