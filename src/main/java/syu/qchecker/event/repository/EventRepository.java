package syu.qchecker.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import syu.qchecker.event.domain.Event;
import syu.qchecker.user.domain.User;

import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {
    Optional<Event> findByEventIdAndUser(Long eventId, User user);
}
