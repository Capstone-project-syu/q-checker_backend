package syu.qchecker.qrcode.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import syu.qchecker.event.domain.Event;
import syu.qchecker.event.repository.EventRepository;
import syu.qchecker.qrcode.domain.Qrcode;
import syu.qchecker.qrcode.domain.Qrimage;
import syu.qchecker.qrcode.dto.QrcodeRequestDto;
import syu.qchecker.qrcode.dto.QrcodeResponseDto;
import syu.qchecker.qrcode.dto.QrimageResponseDto;
import syu.qchecker.qrcode.repository.QrcodeRepository;
import syu.qchecker.qrcode.repository.QrimageRepository;
import syu.qchecker.user.domain.User;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QrcodeService {
    private final QrcodeRepository qrcodeRepository;
    private final QrimageRepository qrimageRepository;
    private final EventRepository eventRepository;
    private final S3Client s3Client;
    private final String bucketName = "q-checker-images";
    private final String cloudfrontDomain = "https://d6bp6bvrnzace.cloudfront.net";

    @Transactional
    public QrimageResponseDto createQrcode(QrcodeRequestDto qrcodeRequestDto, User user) {
        Event event = eventRepository.findById(qrcodeRequestDto.getEventId())
                .orElseThrow(() -> new IllegalArgumentException("해당 이벤트가 존재하지 않습니다."));

        Qrcode qrcode = Qrcode.builder()
                .event(event)
                .status(qrcodeRequestDto.getStatus())
                .build();
        Qrcode savedQrcode = qrcodeRepository.save(qrcode);

        // 3. QR 이미지 생성 및 S3 업로드
        String filename = "qr/" + UUID.randomUUID() + ".png";
        try {
            // ✅ 여기 수정
            String qrContent = "https://api.qchecker.me/api/attendance/events/" + savedQrcode.getEvent().getEventId();

            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix matrix = qrCodeWriter.encode(qrContent, BarcodeFormat.QR_CODE, 300, 300);
            BufferedImage image = MatrixToImageWriter.toBufferedImage(matrix);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            byte[] imageBytes = baos.toByteArray();

            s3Client.putObject(PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(filename)
                            .contentType("image/png")
                            .build(),
                    RequestBody.fromBytes(imageBytes));

            // 4. QR 이미지 엔티티 저장
            Qrimage qrimage = Qrimage.builder()
                    .event(savedQrcode.getEvent())
                    .qrcode(savedQrcode)
                    .imageUrl(cloudfrontDomain + "/" + filename)
                    .build();
            Qrimage savedQrimage = qrimageRepository.save(qrimage);
            return QrimageResponseDto.of(savedQrimage);
        } catch (Exception e) {
            throw new RuntimeException("QR 이미지 생성 실패", e);
        }
    }

    @Transactional(readOnly = true)
    public QrcodeResponseDto getQrcodeById(Long qrcodeId) {
        Qrcode qrcode = qrcodeRepository.findById(qrcodeId)
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 데이터는 존재하지 않습니다."));
        return QrcodeResponseDto.of(qrcode);
    }

    public void deleteQrcode(Long qrcodeId) {
        Qrcode qrcode = qrcodeRepository.findById(qrcodeId)
                .orElseThrow(() -> new IllegalArgumentException("해당 기록을 찾을 수 없습니다."));
        qrcodeRepository.delete(qrcode);
    }
}
