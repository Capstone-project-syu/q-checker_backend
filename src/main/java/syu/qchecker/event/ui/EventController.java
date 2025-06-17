//package syu.qchecker.event.ui;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.*;
//import syu.qchecker.event.service.EventService;
//import syu.qchecker.qrcode.dto.QrcodeCreateDto;
//import syu.qchecker.user.domain.User;
//
//@RestController
//@RequestMapping("/api/events")
//@RequiredArgsConstructor
//public class EventController {
//
//    private final EventService eventService;
//
//    @PostMapping("/create")
//    public ResponseEntity<String> createEvent(@RequestBody QrcodeCreateDto dto,
//                                              @AuthenticationPrincipal User user) {
//        String imageUrl = eventService.createEventWithQr(user, dto);
//        return ResponseEntity.ok(imageUrl);
//    }
//}
