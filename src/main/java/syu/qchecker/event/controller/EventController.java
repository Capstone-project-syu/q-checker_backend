package syu.qchecker.event.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
@Tag(name = "이벤트 관리", description = "이벤트 관리 관련 API")
public class EventController {

    @PostMapping
    @Operation(summary = "이벤트 생성", description = "새로운 이벤트를 생성합니다.")
    public String createEvent() {
        // ...existing code...
        return "이벤트 생성 성공";
    }

    @DeleteMapping("/{event_id}")
    @Operation(summary = "이벤트 삭제", description = "이벤트를 삭제합니다.")
    public String deleteEvent(@PathVariable String event_id) {
        // ...existing code...
        return "이벤트 삭제 성공";
    }

    @GetMapping("/{event_id}/attendances")
    @Operation(summary = "이벤트 출석 내역 조회", description = "특정 이벤트의 출석 내역을 조회합니다.")
    public String getEventAttendances(@PathVariable String event_id) {
        // ...existing code...
        return "이벤트 출석 내역 조회 성공";
    }
}
