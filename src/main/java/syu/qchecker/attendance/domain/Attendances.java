package syu.qchecker.attendance.domain;

import jakarta.persistence.*;
import lombok.*;
import syu.qchecker.common.BaseTimeEntity;
import syu.qchecker.event.domain.Event;
import syu.qchecker.user.domain.User;

@Entity
@Table(name = "attendances")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Attendances extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendances_id", nullable = false, columnDefinition = "BIGINT COMMENT '출결 기록 고유 id'")
    private Long attendancesId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, columnDefinition = "BIGINT COMMENT '유저 고유 id'")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false, columnDefinition = "BIGINT COMMENT '이벤트 고유 id'")
    private Event event;
}
