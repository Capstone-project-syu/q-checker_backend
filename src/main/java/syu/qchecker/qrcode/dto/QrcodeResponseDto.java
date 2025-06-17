package syu.qchecker.qrcode.dto;

import lombok.Builder;
import lombok.Getter;
import syu.qchecker.qrcode.domain.Qrcode;

@Getter
@Builder
public class QrcodeResponseDto {
    private Long qrcodeId;
    private Long eventId;
    private String status;

    public static QrcodeResponseDto of(Qrcode qrcode) {
        return QrcodeResponseDto.builder()
                .qrcodeId(qrcode.getQrcodeId())
                .eventId(qrcode.getEvent().getEventId())
                .status(qrcode.getStatus())
                .build();
    }
}
