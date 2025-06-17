package syu.qchecker.attendance.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AttendancesRequestDto {
    private Long userId;
    private Long eventId;
}
