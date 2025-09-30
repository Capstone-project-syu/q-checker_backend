package syu.qchecker.attendance.ui;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import syu.qchecker.attendance.dto.AttendancesResponseDto;
import syu.qchecker.attendance.service.AttendanceService;
import syu.qchecker.user.domain.User;

import java.util.List;

@RestController
@RequestMapping("/api/attendances")
@RequiredArgsConstructor
@Tag(name = "출석 기록", description = "출석 기록 관련 API")
public class AttendanceController {
    private final AttendanceService attendanceService;

    @GetMapping
    @Operation(summary = "유저 전체 출석 내역", description = "현재 유저의 모든 출석 내역을 조회합니다.")
    public ResponseEntity<List<AttendancesResponseDto>> getAllAttendancesByUser(@AuthenticationPrincipal User user) {
        List<AttendancesResponseDto> responses = attendanceService.getAllAttendancesByUser(user);
        return ResponseEntity.ok(responses);
    }

    @PostMapping("/events/{eventId}")
    @Operation(summary = "QR코드 출석", description = "QR코드를 찍어서 출석합니다.")
    public ResponseEntity<AttendancesResponseDto> recordAttendance(@AuthenticationPrincipal User user,
                                                                   @PathVariable Long eventId) {
        AttendancesResponseDto response = attendanceService.recordAttendance(user, eventId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/nfc/{nfcTag}")
    @Operation(summary = "NFC 출석", description = "NFC를 읽어서 출석합니다.")
    public ResponseEntity<AttendancesResponseDto> recordAttendanceByNfc(@AuthenticationPrincipal User user,
                                                                        @PathVariable Long nfcTag){
        AttendancesResponseDto response = attendanceService.recordAttendanceByNfc(user, nfcTag);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/events/{eventId}")
    @Operation(summary = "이벤트 출석 조회", description = "해당 이벤트의 모든 출석 내역을 조회합니다.")
    public ResponseEntity<List<AttendancesResponseDto>> getAllAttendancesByEvent(@AuthenticationPrincipal User user,
                                                                                 @PathVariable Long eventId) {
        List<AttendancesResponseDto> responses = attendanceService.getAllAttendancesByEvent(user, eventId);
        return ResponseEntity.ok(responses);
    }

    @DeleteMapping("/{attendanceId}")
    @Operation(summary = "출결 기록 삭제", description = "출결 기록을 삭제합니다.")
    public ResponseEntity<?> deleteAttendance(@AuthenticationPrincipal User user,
                                              @PathVariable Long attendanceId) {
        attendanceService.deleteAttendance(user, attendanceId);
        return ResponseEntity.ok("출결 기록 삭제 성공");
    }
}
