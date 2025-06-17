package syu.qchecker.attendance.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import syu.qchecker.attendance.service.AttendanceService;
import syu.qchecker.user.domain.User;

@RestController
@RequestMapping("/api/attendances")
@Tag(name = "출석 기록", description = "출석 기록 관련 API")
public class AttendanceController {
    AttendanceService attendanceService;

    @GetMapping
    @Operation(summary = "사용자 기준 전체 출석 내역", description = "사용자 기준으로 출석 내역을 조회합니다.")
    public ResponseEntity<?> getUserAttendance(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(attendanceService.getAttendanceByUser(user));
    }

    @PostMapping("events/{eventId}")
    @Operation(summary = "QR코드 출석", description = "QR코드를 찍어서 출석합니다.")
    public ResponseEntity<?> recordAttendance(@PathVariable Long eventId, @AuthenticationPrincipal User user) {
        attendanceService.recordAttendance(eventId, user.getEmail());
        return ResponseEntity.ok("출석 완료");
    }

    @DeleteMapping("/{attendanceId}")
    @Operation(summary = "출결 기록 삭제", description = "출결 기록을 삭제합니다.")
    public ResponseEntity<?> deleteAttendance(@AuthenticationPrincipal User user,
                                              @PathVariable Long attendanceId) {
        attendanceService.deleteAttendance(user, attendanceId);
        return ResponseEntity.ok("출결 기록 삭제 성공");
    }
}
