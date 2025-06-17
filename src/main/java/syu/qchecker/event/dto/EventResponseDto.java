package syu.qchecker.event.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import syu.qchecker.event.domain.Event;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class EventResponseDto {
    private Long eventId;
    private Long userId;
    private String eventTitle;
    private LocalDateTime eventDatetime;
    private String eventLocation;
    private String eventDescription;
    private Float latitude;
    private Float longitude;
    private Float validRadius;

    public static EventResponseDto of(Event event) {
        return EventResponseDto.builder()
                .eventId(event.getEventId())
                .userId(event.getUser().getUserId())
                .eventTitle(event.getEventTitle())
                .eventDescription(event.getEventDescription())
                .eventDatetime(event.getEventDatetime())
                .latitude(event.getLatitude())
                .longitude(event.getLongitude())
                .validRadius(event.getValidRadius())
                .eventLocation(event.getEventLocation())
                .build();
    }
}
