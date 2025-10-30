package syu.qchecker.nfc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import syu.qchecker.nfc.domain.Nfc;

import java.util.Optional;

public interface NfcRepository extends JpaRepository<Nfc, Long> {
    Optional<Nfc> findByExpiredFalseAndNfcTag(Long nfcTag);
    Optional<Nfc> findByExpiredFalseAndLectureValue(Long lectureValue);

}
