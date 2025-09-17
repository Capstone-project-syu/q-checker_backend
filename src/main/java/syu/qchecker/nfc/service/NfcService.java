package syu.qchecker.nfc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import syu.qchecker.event.domain.Event;
import syu.qchecker.event.repository.EventRepository;
import syu.qchecker.nfc.domain.Nfc;
import syu.qchecker.nfc.dto.NfcRequestDto;
import syu.qchecker.nfc.dto.NfcResponseDto;
import syu.qchecker.nfc.repository.NfcRepository;
import syu.qchecker.user.domain.User;

@Service
@RequiredArgsConstructor
public class NfcService {
    private final NfcRepository nfcRepository;
    private final EventRepository eventRepository;

    @Transactional
    public NfcResponseDto createNfc(User user, NfcRequestDto nfcRequestDto){
        Event event = eventRepository.findById(nfcRequestDto.getEventId())
                .orElseThrow(() -> new IllegalArgumentException("해당 이벤트가 존재하지 않습니다."));

        Nfc nfc = Nfc.builder()
                .nfcTag(nfcRequestDto.getNfcTag())
                .status(nfcRequestDto.getStatus())
                .event(event)
                .build();
        Nfc savedNfc = nfcRepository.save(nfc);
        return NfcResponseDto.of(savedNfc);
    }

    @Transactional(readOnly = true)
    public NfcResponseDto getNfcInfo(Long nfcId){
        Nfc nfc = nfcRepository.findById(nfcId)
                .orElseThrow(() -> new IllegalArgumentException("해당 NFC를 찾을 수 없습니다."));
        return NfcResponseDto.of(nfc);
    }

    @Transactional
    public void deleteNfc(Long nfcId){
        Nfc nfc = nfcRepository.findById(nfcId)
                .orElseThrow(() -> new IllegalArgumentException("해당 NFC를 찾을 수 없습니다."));
        nfcRepository.delete(nfc);
    }
}
