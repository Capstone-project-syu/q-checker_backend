package syu.qchecker.qrcode.domain;

import jakarta.persistence.*;
import lombok.*;
import syu.qchecker.common.BaseTimeEntity;
import syu.qchecker.event.domain.Event;
import java.time.LocalDateTime;

@Entity
@Table(name = "qr_images")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Qrimage extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qrimage_id", nullable = false, columnDefinition = "BIGINT COMMENT 'QR이미지 고유 id'")
    private Long qrimageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false, columnDefinition = "BIGINT COMMENT '이벤트 고유 id'")
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qrcode_id", nullable = false, columnDefinition = "BIGINT COMMENT 'QR코드 고유 id'")
    private Qrcode qrcode;

    @Column(name = "image_url", nullable = false, columnDefinition = "VARCHAR(255) NOT NULL")
    private String imageUrl;
}
