package syu.qchecker.attendance.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import syu.qchecker.event.domain.Event;
import syu.qchecker.user.domain.User;
import java.time.LocalDateTime;

@Entity
@Table(name = "attendances")
@Getter @Setter
public class Attendances {

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

    @Column(name = "created_at", nullable = true, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = true, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;
}
