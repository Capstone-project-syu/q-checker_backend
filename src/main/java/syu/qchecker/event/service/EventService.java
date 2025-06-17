package syu.qchecker.event.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import syu.qchecker.event.domain.Event;
import syu.qchecker.event.dto.EventRequestDto;
import syu.qchecker.event.dto.EventResponseDto;
import syu.qchecker.event.repository.EventRepository;
import syu.qchecker.user.domain.User;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    @Transactional
    public EventResponseDto createEvent(User user, EventRequestDto eventRequestDto) {
        Event event = Event.builder()
                .user(user)
                .eventTitle(eventRequestDto.getEventTitle())
                .eventDescription(eventRequestDto.getEventDescription())
                .eventDatetime(eventRequestDto.getEventDatetime())
                .eventLocation(eventRequestDto.getEventLocation())
                .latitude(eventRequestDto.getLatitude())
                .longitude(eventRequestDto.getLongitude())
                .validRadius(eventRequestDto.getValidRadius())
                .build();

        Event savedEvent = eventRepository.save(event);
        return EventResponseDto.of(savedEvent);
    }

    public void deleteEvent(User user, Long eventId) {
        Event event = eventRepository.findByIdAndUser(eventId, user)
                .orElseThrow(() -> new IllegalArgumentException("해당 이벤트를 찾을 수 없습니다."));
        eventRepository.delete(event);
    }
}
