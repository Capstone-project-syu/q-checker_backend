package syu.qchecker.attendance.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import syu.qchecker.attendance.domain.Attendances;
import syu.qchecker.attendance.repository.AttendancesRepository;
import syu.qchecker.attendance.controller.AttendanceCheckRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendancesRepository attendancesRepository;

    @Override
    public List<Attendances> findByEventId(Long eventId) {
        return attendancesRepository.findByEventId(eventId);
    }

    @Override
    public Attendances checkAttendance(AttendanceCheckRequest request, OAuth2User principal) {
        // 출석 체크 로직 (예시)
        Attendances attendance = new Attendances();
        // ...필드 설정
        return attendancesRepository.save(attendance);
    }
}
