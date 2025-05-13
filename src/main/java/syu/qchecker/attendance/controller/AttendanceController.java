package syu.qchecker.attendance.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/attendances")
@Tag(name = "출석 기록", description = "출석 기록 관련 API")
public class AttendanceController {

    @GetMapping("/users/{user_id}")
    @Operation(summary = "사용자 기준 출석 내역", description = "사용자 기준으로 출석 내역을 조회합니다.")
    public String getUserAttendance(@PathVariable String user_id) {
        // ...existing code...
        return "사용자 기준 출석 내역 조회 성공";
    }

    @GetMapping("/events/{event_id}")
    @Operation(summary = "이벤트 기준 출석 내역", description = "이벤트 기준으로 출석 내역을 조회합니다.")
    public String getEventAttendance(@PathVariable String event_id) {
        // ...existing code...
        return "이벤트 기준 출석 내역 조회 성공";
    }

    @DeleteMapping("/{attendance_id}")
    @Operation(summary = "출결 기록 삭제", description = "출결 기록을 삭제합니다.")
    public String deleteAttendance(@PathVariable String attendance_id) {
        // ...existing code...
        return "출결 기록 삭제 성공";
    }
}
