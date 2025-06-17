package syu.qchecker.event.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import syu.qchecker.event.dto.EventRequestDto;
import syu.qchecker.user.domain.User;

@Service
@RequiredArgsConstructor
public class EventService {

//    @Transactional
//    public String createEvent(User user, EventRequestDto eventRequestDto) {
//        // 이벤트 생성
//        Event event = EventRequestDto.builder();
//        event.setUser(user);
//        event.setEventTitle(dto.getEventTitle());
//        event.setEventDescription(dto.getEventDescription());
//        event.setEventDatetime(dto.getEventDatetime());
//        event.setLatitude(dto.getLatitude());
//        event.setLongitude(dto.getLongitude());
//        event.setValidRadius(dto.getValidRadius());
//        Event savedEvent = eventRepository.save(event);
//
//    }

}
