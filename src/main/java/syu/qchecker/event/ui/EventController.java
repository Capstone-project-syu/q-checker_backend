package syu.qchecker.event.ui;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import syu.qchecker.event.dto.EventRequestDto;
import syu.qchecker.event.dto.EventResponseDto;
import syu.qchecker.event.service.EventService;
import syu.qchecker.user.domain.User;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping
    @Operation(summary = "이벤트 생성", description = "이벤트를 생성합니다.")
    public ResponseEntity<EventResponseDto> createEvent(@AuthenticationPrincipal User user,
                                                        EventRequestDto eventRequestDto) {
        EventResponseDto response = eventService.createEvent(user, eventRequestDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{eventId}")
    @Operation(summary = "이벤트 삭제", description = "이벤트를 삭제합니다.")
    public ResponseEntity<Void> deleteEvent(@AuthenticationPrincipal User user,
                                            @PathVariable("eventId") Long eventId) {
        eventService.deleteEvent(user, eventId);
        return ResponseEntity.ok().build();
    }
}
