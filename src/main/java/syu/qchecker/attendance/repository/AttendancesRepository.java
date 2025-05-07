package syu.qchecker.attendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import syu.qchecker.attendance.domain.Attendances;
import syu.qchecker.event.domain.Event;
import syu.qchecker.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface AttendancesRepository extends JpaRepository<Attendances, Long> {
    List<Attendances> findByEvent(Event event);
    Optional<Attendances> findByUserAndEvent(User user, Event event);
}
