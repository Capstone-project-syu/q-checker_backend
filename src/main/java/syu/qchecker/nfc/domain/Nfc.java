package syu.qchecker.nfc.domain;

import jakarta.persistence.*;
import lombok.*;
import syu.qchecker.common.BaseTimeEntity;
import syu.qchecker.event.domain.Event;

@Entity
@Table(name = "nfc")
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Nfc extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nfc_id", nullable = false)
    private Long nfcId;

    @Column(name = "nfc_tag", nullable = false)
    private Long nfcTag;

    @Column(name = "lecture_value", nullable = false)
    private Long lectureValue;

    @Column(name = "lecture", nullable = false)
    private String lecture;

    @Column(name = "status", nullable = true)
    private String status;

    @Column(name = "expired", nullable = false)
    private boolean expired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false, columnDefinition = "BIGINT COMMENT '이벤트 고유 id'")
    private Event event;
}
