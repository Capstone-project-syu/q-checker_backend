package syu.qchecker.nfc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import syu.qchecker.event.domain.Event;
import syu.qchecker.event.repository.EventRepository;
import syu.qchecker.nfc.domain.Nfc;
import syu.qchecker.nfc.dto.NfcRegisterDto;
import syu.qchecker.nfc.dto.NfcRequestDto;
import syu.qchecker.nfc.dto.NfcResponseDto;
import syu.qchecker.nfc.repository.NfcRepository;
import syu.qchecker.user.domain.User;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NfcService {
    private final NfcRepository nfcRepository;
    private final EventRepository eventRepository;

    @Transactional
    public NfcResponseDto registerNfc(User user, NfcRegisterDto nfcRegisterDto){
        Event event = eventRepository.findById(nfcRegisterDto.getEventId())
                .orElseThrow(() -> new IllegalArgumentException("해당 이벤트가 존재하지 않습니다."));

        Optional<Nfc> n = nfcRepository.findByExpiredFalseAndLectureValue(nfcRegisterDto.getLectureValue());

        if (n.isPresent()){
            Nfc existingNfc = n.get();

            existingNfc.setExpired(true);

            nfcRepository.save(existingNfc);
        }
        // 활성화된 Nfc 존재 시 비활성화 상태로 변경 후 해당 NfcTag 사용

        Long nfcTag;

        if (nfcRegisterDto.getLectureValue() == 401){
            nfcTag = 123401L;
        } else if (nfcRegisterDto.getLectureValue() == 402){
            nfcTag = 123402L;
        } else if (nfcRegisterDto.getLectureValue() == 403){
            nfcTag = 123403L;
        } else if (nfcRegisterDto.getLectureValue() == 404){
            nfcTag = 123404L;
        }else {
            nfcTag = 123123L;
        }

        Nfc nfc = Nfc.builder()
                .nfcTag(nfcTag)
                .lectureValue(nfcRegisterDto.getLectureValue())
                .lecture(nfcRegisterDto.getLecture())
                .status(nfcRegisterDto.getStatus())
                .expired(false)
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
    public void deleteNfc(User user, Long nfcId){
        Nfc nfc = nfcRepository.findById(nfcId)
                .orElseThrow(() -> new IllegalArgumentException("해당 NFC를 찾을 수 없습니다."));

        if (!nfc.getEvent().getUser().getUserId().equals(user.getUserId())){
            throw new RuntimeException("해당 NFC를 삭제할 수 없습니다.");
        }

        nfcRepository.delete(nfc);
    }
}
