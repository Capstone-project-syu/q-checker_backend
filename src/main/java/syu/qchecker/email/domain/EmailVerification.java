package syu.qchecker.email.domain;

import jakarta.persistence.*;
import lombok.*;
import syu.qchecker.common.BaseTimeEntity;
import syu.qchecker.university.domain.University;

import java.time.LocalDateTime;

@Entity
@Table(name = "email_verifications")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EmailVerification extends BaseTimeEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "verification_id")
    private Long verificationId;
    
    @Column(name = "email", nullable = false, columnDefinition = "VARCHAR(100)")
    private String email;
    
    @Column(name = "verification_code", nullable = false, columnDefinition = "VARCHAR(10)")
    private String verificationCode;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_id", nullable = false)
    private University university;
    
    @Column(name = "is_verified", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    @Builder.Default
    private boolean isVerified = false;
    
    @Column(name = "expires_at", nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime expiresAt;
    
    public void verify() {
        this.isVerified = true;
    }
    
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiresAt);
    }
    
    public String getUniversityName() {
        return university != null ? university.getName() : null;
    }
}
