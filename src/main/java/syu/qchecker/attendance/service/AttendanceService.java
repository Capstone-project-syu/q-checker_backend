package syu.qchecker.attendance.service;

import syu.qchecker.attendance.domain.Attendances;
import syu.qchecker.attendance.controller.AttendanceCheckRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import java.util.List;

public interface AttendanceService {
    List<Attendances> findByEventId(Long eventId);
    Attendances checkAttendance(AttendanceCheckRequest request, OAuth2User principal);
}
