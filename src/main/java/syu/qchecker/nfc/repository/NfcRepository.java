package syu.qchecker.nfc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import syu.qchecker.nfc.domain.Nfc;

public interface NfcRepository extends JpaRepository<Nfc, Long> {
}
