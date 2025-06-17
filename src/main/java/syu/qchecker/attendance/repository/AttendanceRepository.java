package syu.qchecker.attendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import syu.qchecker.attendance.domain.Attendances;
import syu.qchecker.event.domain.Event;
import syu.qchecker.user.domain.User;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendances, Long> {
    List<Attendances> findByUser(User user);
    List<Attendances> findByEvent(Event event);
    boolean existsByUserAndEvent(User user, Event event);
}
