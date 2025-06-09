package syu.qchecker.qrcode.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import syu.qchecker.common.BaseTimeEntity;
import syu.qchecker.event.domain.Event;
@Entity
@Table(name = "qr_codes")
@Getter @Setter
public class Qrcode extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qrcode_id", nullable = false, columnDefinition = "BIGINT COMMENT 'QR코드 고유 id'")
    private Long qrcodeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false, columnDefinition = "BIGINT COMMENT '이벤트 고유 id'")
    private Event event;

    @Column(name = "status", nullable = true, columnDefinition = "VARCHAR(1) NULL")
    private String status;
}
