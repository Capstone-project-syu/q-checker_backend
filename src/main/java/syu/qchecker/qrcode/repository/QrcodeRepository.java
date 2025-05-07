package syu.qchecker.qrcode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import syu.qchecker.event.domain.Event;
import syu.qchecker.qrcode.domain.Qrcode;

import java.util.List;

public interface QrcodeRepository extends JpaRepository<Qrcode, Long> {
    List<Qrcode> findByEvent(Event event);
}
