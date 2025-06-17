package syu.qchecker.qrcode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import syu.qchecker.qrcode.domain.Qrcode;

public interface QrcodeRepository extends JpaRepository<Qrcode, Long> {
}
