package syu.qchecker.qrcode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import syu.qchecker.qrcode.domain.Qrcode;
import syu.qchecker.qrcode.domain.Qrimage;

import java.util.Optional;

public interface QrimageRepository extends JpaRepository<Qrimage, Long> {
    Optional<Qrimage> findByQrcode(Qrcode qrcode);
}
