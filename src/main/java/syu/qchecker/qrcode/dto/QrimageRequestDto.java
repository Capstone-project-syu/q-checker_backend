package syu.qchecker.qrcode.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QrimageRequestDto {
    private Long eventId;
    private Long qrcodeId;
    private String imageUrl;
}
