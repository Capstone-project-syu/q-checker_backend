package syu.qchecker.email.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import syu.qchecker.email.domain.EmailVerification;

import java.util.Optional;

@Repository
public interface EmailVerificationRepository extends JpaRepository<EmailVerification, Long> {
    
    Optional<EmailVerification> findByEmailAndVerificationCode(String email, String verificationCode);
    
    Optional<EmailVerification> findByEmail(String email);
    
    boolean existsByEmailAndIsVerified(String email, boolean isVerified);
    
    void deleteByEmail(String email);
}