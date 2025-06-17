package syu.qchecker.qrcode.dto;

import lombok.Builder;
import lombok.Getter;
import syu.qchecker.event.domain.Event;
import syu.qchecker.qrcode.domain.Qrimage;

@Getter
@Builder
public class QrimageResponseDto {
    private Long qrimageId;
    private Long eventId;
    private String qrimageUrl;

    public static QrimageResponseDto of(Qrimage qrimage) {
        return QrimageResponseDto.builder()
                .qrimageId(qrimage.getQrimageId())
                .eventId(qrimage.getEvent().getEventId())
                .qrimageUrl(qrimage.getImageUrl())
                .build();
    }
}
