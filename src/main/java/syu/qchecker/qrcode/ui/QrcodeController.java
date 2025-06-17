package syu.qchecker.qrcode.ui;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import syu.qchecker.qrcode.dto.QrcodeRequestDto;
import syu.qchecker.qrcode.dto.QrcodeResponseDto;
import syu.qchecker.qrcode.dto.QrimageResponseDto;
import syu.qchecker.qrcode.service.QrcodeService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import syu.qchecker.user.domain.User;

@RestController
@RequestMapping("/api/qrcode")
@Tag(name = "Qr코드 & Qr이미지", description = "Qr코드 관련 API")
@RequiredArgsConstructor
public class QrcodeController {
    private final QrcodeService qrcodeService;

    @PostMapping
    @Operation(summary = "qr코드 생성", description = "이벤트 생성 후, 해당 이벤트의 출석 qr코드를 생성한다.")
    public ResponseEntity<QrimageResponseDto> createQrEvent(@RequestBody QrcodeRequestDto qrcodeRequestDto,
                                                            @AuthenticationPrincipal User user) {
        QrimageResponseDto response = qrcodeService.createQrcode(qrcodeRequestDto, user);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{qrcodeId}")
    @Operation(summary = "qr코드 조회", description = "개별 qr코드 정보를 조회한다.")
    public ResponseEntity<QrcodeResponseDto> getQrcodeById(@PathVariable("qrcodeId") Long qrcodeId) {
        QrcodeResponseDto response = qrcodeService.getQrcodeById(qrcodeId);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{qrcodeId}")
    @Operation(summary = "qr코드 삭제", description = "qr코드를 삭제한다.")
    public ResponseEntity<Void> deleteQrcode(@AuthenticationPrincipal User user,
                                             @PathVariable("qrcodeId") Long qrcodeId) {
        qrcodeService.deleteQrcode(user, qrcodeId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{qrcodeId}/qrimage")
    @Operation(summary = "qr이미지 정보 조회", description = "해당 qr코드와 연결된 qr이미지 정보를 보여준다.")
    public ResponseEntity<QrimageResponseDto> getQrimageInfo(@PathVariable("qrcodeId") Long qrcodeId,
                                                             @AuthenticationPrincipal User user) {
        QrimageResponseDto response = qrcodeService.getQrimageInfo(user, qrcodeId);
        return ResponseEntity.ok(response);
    }
}
