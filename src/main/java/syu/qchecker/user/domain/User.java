package syu.qchecker.user.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import syu.qchecker.common.BaseTimeEntity;

@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {
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

    @Builder
    public User(String email, String name, String socialType, String socialId, Long studentNumber) {
        this.email = email;
        this.name = name;
        this.socialType = socialType;
        this.socialId = socialId;
        this.studentNumber = studentNumber;
    }

    public User update(String name) {
        this.name = name;
        return this;
    }
}
