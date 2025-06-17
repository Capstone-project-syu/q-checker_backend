package syu.qchecker.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import syu.qchecker.event.domain.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
}
