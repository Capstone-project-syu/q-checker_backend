package syu.qchecker.nfc.ui;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import syu.qchecker.nfc.dto.NfcRequestDto;
import syu.qchecker.nfc.dto.NfcResponseDto;
import syu.qchecker.nfc.service.NfcService;
import syu.qchecker.user.domain.User;

@RestController
@RequestMapping("/api/nfc")
@Tag(name = "nfc코드", description = "nfc코드 관련 API")
@RequiredArgsConstructor
public class NfcController {
    private final NfcService nfcService;

    @PostMapping("/register")
    @Operation(summary = "NFC 태그 등록", description = "NFC 태그 정보 등록")
    public ResponseEntity<NfcResponseDto> registerNfcTag(@AuthenticationPrincipal User user,
                                                         @RequestBody NfcRequestDto nfcRequestDto) {
        // nfc 태그 id와 이벤트 id 매핑
        NfcResponseDto response = nfcService.createNfc(user, nfcRequestDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{nfcId}")
    @Operation(summary = "NFC 태그 조회", description = "개별 NFC 태그를 조회한다.")
    public ResponseEntity<NfcResponseDto> getNfcTagById(@PathVariable("nfcId") Long nfcId) {
        // 하나의 nfc 태그는 한 번에 하나의 이벤트만 소유 가능
        // 태그 등록 해제로 비활성화 상태여야 새로운 이벤트 등록 가능
        // 새로운 등록 요청 시 이전 태그 등록은 자동 반납(비활성화)
        NfcResponseDto response = nfcService.getNfcInfo(nfcId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{nfcId}")
    @Operation(summary = "nfc 태그 삭제", description = "nfc 태그 정보 등록 해제")
    public ResponseEntity<Void> deleteNfcTag(@AuthenticationPrincipal User user,
                                             @PathVariable("nfcId") Long nfcId) {
        nfcService.deleteNfc(nfcId);
        return ResponseEntity.ok().build();
    }
}
