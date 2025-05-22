package syu.qchecker.user.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter @Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false, columnDefinition = "BIGINT COMMENT '유저 고유 id'")
    private Long userId;

    @Column(name = "social_type", nullable = false, columnDefinition = "VARCHAR(50) COMMENT 'google | kakao'")
    private String socialType;

    @Column(name = "social_id", nullable = true, columnDefinition = "VARCHAR(100) NULL")
    private String socialId;

    @Column(name = "email", nullable = false, columnDefinition = "VARCHAR(100) COMMENT '소셜 계정 이메일'")
    private String email;

    @Column(name = "name", nullable = true, columnDefinition = "VARCHAR(100) NULL")
    private String name;

    @Column(name = "student_number", nullable = true, columnDefinition = "BIGINT NULL")
    private Long studentNumber;

    @Column(name = "created_at", nullable = true, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = true, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;
}
