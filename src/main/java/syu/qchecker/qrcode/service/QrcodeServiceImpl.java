package syu.qchecker.qrcode.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import syu.qchecker.qrcode.domain.Qrcode;
import syu.qchecker.qrcode.domain.Qrimage;
import syu.qchecker.qrcode.repository.QrcodeRepository;
import syu.qchecker.qrcode.repository.QrimageRepository;
import syu.qchecker.event.repository.EventRepository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * QR코드 생성 서비스 구현체
 */
@Service
@RequiredArgsConstructor
public class QrcodeServiceImpl implements QrcodeService {

    @Value("${qrcode.image.path}")
    private String qrImagePath;

    private final QrcodeRepository qrcodeRepository;
    private final QrimageRepository qrimageRepository;
    private final EventRepository eventRepository;

    @Override
    public Qrimage generateQrCode(Long eventId, OAuth2User principal) {
        String qrData = "EVENT-" + eventId + "-" + UUID.randomUUID();
        int width = 300, height = 300;

        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(qrData, BarcodeFormat.QR_CODE, width, height);

            Path dirPath = Paths.get(qrImagePath);
            if (!Files.exists(dirPath)) Files.createDirectories(dirPath);

            String fileName = "qr_" + System.currentTimeMillis() + ".png";
            Path filePath = dirPath.resolve(fileName);
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", filePath);

            Qrcode qrcode = new Qrcode();
            qrcode.setEvent(eventRepository.findById(eventId).orElseThrow());
            qrcode.setStatus("A");
            qrcodeRepository.save(qrcode);

            Qrimage qrimage = new Qrimage();
            qrimage.setQrcode(qrcode);
            qrimage.setEvent(qrcode.getEvent());
            qrimage.setImageUrl("/qr-images/" + fileName);
            return qrimageRepository.save(qrimage);

        } catch (Exception e) {
            throw new RuntimeException("QR 생성 실패", e);
        }
    }
}
