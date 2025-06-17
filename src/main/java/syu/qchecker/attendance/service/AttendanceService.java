package syu.qchecker.attendance.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import syu.qchecker.attendance.domain.Attendances;
import syu.qchecker.attendance.repository.AttendanceRepository;
import syu.qchecker.event.domain.Event;
import syu.qchecker.event.repository.EventRepository;
import syu.qchecker.qrcode.domain.Qrcode;
import syu.qchecker.qrcode.repository.QrcodeRepository;
import syu.qchecker.user.domain.User;
import syu.qchecker.user.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public List<Attendances> getAttendanceByUser(User user) {
        return attendanceRepository.findByUser(user);
    }

    public List<Attendances> getAttendanceByEvent(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("해당 이벤트가 존재하지 않습니다."));
        return attendanceRepository.findByEvent(event);
    }

    @Transactional
    public void deleteAttendance(User user, Long attendanceId) {
        Attendances attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new IllegalArgumentException("해당 출석 기록이 존재하지 않습니다."));
        if (!attendance.getUser().getUserId().equals(user.getUserId())) {
            throw new SecurityException("삭제 권한이 없습니다.");
        }
        attendanceRepository.delete(attendance);
    }

    @Transactional
    public void recordAttendance(Long eventId, String userEmail) {
//        User user = userRepository.findByEmail(userEmail)
//                .orElseThrow(() -> new IllegalArgumentException("사용자 없음"));
//
//        Event event = eventRepository.findById(eventId)
//                .orElseThrow(() -> new IllegalArgumentException("이벤트 없음"));
//
//        if (attendanceRepository.existsByUserAndEvent(user, event)) {
//            throw new IllegalStateException("이미 출석한 이벤트입니다.");
//        }
//
//        Attendances attendance = new Attendances();
//        attendance.setUser(user);
//        attendance.setEvent(event);
//
//        attendanceRepository.save(attendance);
    }
}
