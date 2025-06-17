package syu.qchecker.qrcode.ui;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import syu.qchecker.event.dto.EventRequestDto;
import syu.qchecker.qrcode.domain.Qrimage;
import syu.qchecker.qrcode.dto.QrcodeRequestDto;
import syu.qchecker.qrcode.dto.QrcodeResponseDto;
import syu.qchecker.qrcode.dto.QrimageResponseDto;
import syu.qchecker.qrcode.service.QrcodeService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import syu.qchecker.user.domain.User;

@RestController
@RequestMapping("/api/qrcode")
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
    @Operation(summary = "qr코드 조회", description = "개별 qr코드를 조회한다.")
    public ResponseEntity<QrcodeResponseDto> getQrcodeById(@PathVariable("qrcodeId") Long qrcodeId) {
        QrcodeResponseDto response = qrcodeService.getQrcodeById(qrcodeId);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{qrcodeId}")
    @Operation(summary = "qr코드 삭제", description = "qr코드를 삭제한다.")
    public ResponseEntity<Void> deleteQrcode(@AuthenticationPrincipal User user,
                                             @PathVariable("qrcodeId") Long qrcodeId) {
        qrcodeService.deleteQrcode(qrcodeId);
        return ResponseEntity.ok().build();
    }
}
