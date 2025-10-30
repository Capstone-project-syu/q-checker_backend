package syu.qchecker.nfc.dto;

import lombok.Builder;
import lombok.Getter;
import syu.qchecker.nfc.domain.Nfc;

@Getter
@Builder
public class NfcResponseDto {
    private Long nfcId;
    private Long nfcTag;
    private String status;
    private Long eventId;
    private Long lectureValue;
    private String lecture;

    public static NfcResponseDto of(Nfc nfc){
        return NfcResponseDto.builder()
                .nfcId(nfc.getNfcId())
                .nfcTag(nfc.getNfcTag())
                .status(nfc.getStatus())
                .eventId(nfc.getEvent().getEventId())
                .lectureValue(nfc.getLectureValue())
                .lecture(nfc.getLecture())
                .build();
    }
}
