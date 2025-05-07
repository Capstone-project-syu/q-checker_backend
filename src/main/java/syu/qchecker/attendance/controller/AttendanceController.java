package syu.qchecker.attendance.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import syu.qchecker.attendance.domain.Attendances;
import syu.qchecker.attendance.service.AttendanceService;

import java.util.List;

@RestController
@RequestMapping("/api/attendances")
@RequiredArgsConstructor
@Tag(name = "출석 관리", description = "출석 관련 API")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @Operation(summary = "QR 출석 체크", description = "QR코드와 GPS 위치를 검증하여 출석을 체크합니다.")
    @PostMapping("/check")
    public ResponseEntity<Attendances> checkAttendance(
            @RequestBody AttendanceCheckRequest request,
            @AuthenticationPrincipal OAuth2User principal) {
        return ResponseEntity.ok(attendanceService.checkAttendance(request, principal));
    }

    @Operation(summary = "이벤트별 출석 목록 조회", description = "특정 이벤트의 모든 출석 기록을 조회합니다.")
    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<Attendances>> getAttendancesByEvent(@PathVariable Long eventId) {
        return ResponseEntity.ok(attendanceService.findByEventId(eventId));
    }
}
