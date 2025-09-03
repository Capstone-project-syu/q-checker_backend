package syu.qchecker.nfc.ui;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import syu.qchecker.user.domain.User;

@RestController
@RequestMapping("/api/nfc")
@Tag(name = "nfc코드", description = "nfc코드 관련 API")
@RequiredArgsConstructor
public class NfcController {

    @PostMapping("/register")
    @Operation(
            summary = "nfc 태그 등록",
            description = "NFC 태그 정보 등록."
    )
    public ResponseEntity<Void> registerNfcTag(@AuthenticationPrincipal User user,
                                               @PathVariable("nfcId") Long nfcId) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{nfcId}")
    @Operation(summary = "nfc 정보 조회", description = "개별 nfc 정보를 조회한다.")
    public ResponseEntity<Void> getQrcodeById(@PathVariable("nfcId") Long nfcId) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{nfcId}")
    @Operation(summary = "nfc 삭제", description = "nfc를 삭제")
    public ResponseEntity<Void> deleteQrcode(@AuthenticationPrincipal User user,
                                             @PathVariable("nfcId") Long nfcId) {
        return ResponseEntity.ok().build();
    }
}
