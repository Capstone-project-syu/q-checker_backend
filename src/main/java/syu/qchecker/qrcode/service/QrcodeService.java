package syu.qchecker.qrcode.service;

import syu.qchecker.qrcode.domain.Qrimage;
import org.springframework.security.oauth2.core.user.OAuth2User;

public interface QrcodeService {
    Qrimage generateQrCode(Long eventId, OAuth2User principal);
}
