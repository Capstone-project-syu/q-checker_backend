package syu.qchecker.qrcode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import syu.qchecker.qrcode.domain.Qrimage;

public interface QrimageRepository extends JpaRepository<Qrimage, Long> {
}
