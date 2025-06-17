package syu.qchecker.event.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import syu.qchecker.event.domain.Event;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class EventRequestDto {
    private String eventTitle;
    private String eventDescription;
    private LocalDateTime eventDatetime;
    private Float latitude;
    private Float longitude;
    private Float validRadius;
    private int qrValidMinutes;
    private String eventLocation;

    public static EventRequestDto of(Event event) {
        return EventRequestDto.builder()
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
