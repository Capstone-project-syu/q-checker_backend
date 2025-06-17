package syu.qchecker.attendance.dto;

import lombok.Builder;
import lombok.Getter;
import syu.qchecker.attendance.domain.Attendances;

@Getter
@Builder
public class AttendancesResponseDto {
    private Long attendancesId;
    private Long userId;
    private Long eventId;

    public static AttendancesResponseDto of(Attendances attendances) {
        return AttendancesResponseDto.builder()
                .attendancesId(attendances.getAttendancesId())
                .userId(attendances.getUser().getUserId())
                .eventId(attendances.getEvent().getEventId())
                .build();
    }
}
