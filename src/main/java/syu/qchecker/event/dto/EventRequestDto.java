package syu.qchecker.event.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EventRequestDto {
    private String eventTitle;
    private String eventDescription;
    private LocalDateTime eventDatetime;
    private String eventLocation;
    private Float latitude;
    private Float longitude;
    private Float validRadius;
}
