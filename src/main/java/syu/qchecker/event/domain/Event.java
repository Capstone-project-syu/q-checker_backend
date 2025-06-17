package syu.qchecker.event.domain;

import jakarta.persistence.*;
import lombok.*;
import syu.qchecker.common.BaseTimeEntity;
import syu.qchecker.qrcode.domain.Qrcode;
import syu.qchecker.user.domain.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "events")
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Event extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id", nullable = false, columnDefinition = "BIGINT COMMENT '이벤트 고유 id'")
    private Long eventId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, columnDefinition = "BIGINT COMMENT '유저 고유 id'")
    private User user;

    @Column(name = "event_title", nullable = false, columnDefinition = "VARCHAR(100) NOT NULL")
    private String eventTitle;

    @Column(name = "event_datetime", nullable = false, columnDefinition = "DATETIME NOT NULL")
    private LocalDateTime eventDatetime;

    @Column(name = "event_location", nullable = false, columnDefinition = "VARCHAR(100) NOT NULL")
    private String eventLocation;

    @Column(name = "event_description", nullable = true, columnDefinition = "VARCHAR(500) NULL")
    private String eventDescription;

    @Column(name = "latitude", nullable = true, columnDefinition = "FLOAT NULL")
    private Float latitude;

    @Column(name = "longitude", nullable = true, columnDefinition = "FLOAT NULL")
    private Float longitude;

    @Column(name = "valid_radius", nullable = true, columnDefinition = "FLOAT NULL")
    private Float validRadius;

    @Column
    @OneToMany(mappedBy = "event", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Qrcode> qrcodes = new ArrayList<>();
}
