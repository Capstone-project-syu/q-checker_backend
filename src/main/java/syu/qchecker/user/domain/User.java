package syu.qchecker.user.domain;

import jakarta.persistence.*;
import lombok.*;
import syu.qchecker.common.BaseTimeEntity;
import syu.qchecker.university.domain.University;

@Entity
@Table(name = "users")
@Setter
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
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
    
    @Column(name = "student_email", nullable = true, columnDefinition = "VARCHAR(100) COMMENT '대학교 이메일'")
    private String studentEmail;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_id", foreignKey = @ForeignKey(name = "fk_user_university"))
    private University university;
    
    @Column(name = "university_verified", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    @Builder.Default
    private boolean isStudentVerified = false;

    public User update(String name) {
        this.name = name;
        return this;
    }
    
    public User updateUniversityInfo(String studentEmail, syu.qchecker.university.domain.University university) {
        this.studentEmail = studentEmail;
        this.university = university;
        this.isStudentVerified = true;
        return this;
    }
    
    public String getUniversityName() {
        return university != null ? university.getName() : null;
    }
}
