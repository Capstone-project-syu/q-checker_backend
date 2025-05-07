package syu.qchecker.qrcode.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import syu.qchecker.qrcode.domain.Qrcode;
import syu.qchecker.qrcode.domain.Qrimage;
import syu.qchecker.qrcode.service.QrcodeService;

@RestController
@RequestMapping("/api/qrcodes")
@RequiredArgsConstructor
@Tag(name = "QR코드 관리", description = "QR코드 관련 API")
public class QrcodeController {

    private final QrcodeService qrcodeService;

    @PostMapping("/generate/{eventId}")
    @Operation(summary = "QR코드 생성", description = "특정 이벤트에 대한 QR코드를 생성합니다.")
    public ResponseEntity<Qrimage> generateQrCode(
            @PathVariable Long eventId,
            @AuthenticationPrincipal OAuth2User principal) {
        return ResponseEntity.ok(qrcodeService.generateQrCode(eventId, principal));
    }

    @GetMapping("/{qrcodeId}")
    @Operation(summary = "QR코드 조회", description = "QR코드 정보를 조회합니다.")
    public ResponseEntity<Qrcode> getQrCode(@PathVariable Long qrcodeId) {
        return ResponseEntity.ok(qrcodeService.findById(qrcodeId));
    }
}
