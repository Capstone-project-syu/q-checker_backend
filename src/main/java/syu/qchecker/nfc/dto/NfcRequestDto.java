package syu.qchecker.nfc.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NfcRequestDto {
    private Long nfcTag;
    private String status;
    private Long eventId;
}
