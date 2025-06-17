package syu.qchecker.attendance.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import syu.qchecker.attendance.domain.Attendances;
import syu.qchecker.attendance.dto.AttendancesResponseDto;
import syu.qchecker.attendance.repository.AttendanceRepository;
import syu.qchecker.event.domain.Event;
import syu.qchecker.event.repository.EventRepository;
import syu.qchecker.user.domain.User;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final EventRepository eventRepository;

    @Transactional(readOnly = true)
    public List<AttendancesResponseDto> getAllAttendancesByUser(User user) {
        List<Attendances> attendances = attendanceRepository.findByUser(user);
        return attendances.stream()
                .map(AttendancesResponseDto::of)
                .collect(Collectors.toList());
    }

    public List<AttendancesResponseDto> getAllAttendancesByEvent(User user, Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("해당 이벤트가 존재하지 않습니다."));

        if (!event.getUser().equals(user)) {
            throw new IllegalArgumentException("이벤트 관리자만 접근할 수 있습니다.");
        }

        return attendanceRepository.findByEvent(event).stream()
                .map(AttendancesResponseDto::of)
                .collect(Collectors.toList());
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
    public AttendancesResponseDto recordAttendance(User user, Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("이벤트 없음"));

        if (attendanceRepository.existsByUserAndEvent(user, event)) {
            throw new IllegalStateException("이미 출석한 이벤트입니다.");
        }

        Attendances attendance = Attendances.builder()
                .user(user)
                .event(event)
                .build();

        Attendances savedAttendances = attendanceRepository.save(attendance);
        return AttendancesResponseDto.of(savedAttendances);
    }
}
