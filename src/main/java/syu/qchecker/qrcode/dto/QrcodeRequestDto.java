package syu.qchecker.qrcode.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QrcodeRequestDto {
    private Long eventId;
    private String status;
}
