package syu.qchecker.nfc.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class NfcRegisterDto {
    private Long lectureValue;
    private String lecture;
    private String status;
    private Long eventId;
}
