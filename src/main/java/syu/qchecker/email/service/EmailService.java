package syu.qchecker.email.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import syu.qchecker.email.domain.EmailVerification;
import syu.qchecker.email.dto.EmailRequestDto;
import syu.qchecker.email.dto.EmailResponseDto;
import syu.qchecker.email.repository.EmailVerificationRepository;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional
public class EmailService {
    
    private final EmailVerificationRepository emailVerificationRepository;
    private final UniversityEmailValidator universityEmailValidator;
    private final EmailSender emailSender;
    
    private static final int VERIFICATION_CODE_LENGTH = 6;
    private static final int EXPIRY_MINUTES = 10;

    public EmailResponseDto sendVerificationCode(EmailRequestDto requestDto) {
        String email = requestDto.getEmail().toLowerCase().trim();

        if (!universityEmailValidator.isUniversityEmail(email)) {
            throw new IllegalArgumentException("지원하지 않는 대학교 이메일입니다. 대학교 이메일을 사용해주세요.");
        }

        if (isEmailVerified(email)) {
            throw new IllegalArgumentException("이미 사용중인 이메일입니다. 다른 이메일을 사용해주세요.");
        }

        emailVerificationRepository.deleteByEmail(email);

        String verificationCode = generateVerificationCode();
        syu.qchecker.university.domain.University university = universityEmailValidator.getUniversityByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 대학교 이메일입니다."));

        EmailVerification verification = EmailVerification.builder()
                .email(email)
                .verificationCode(verificationCode)
                .university(university)
                .expiresAt(LocalDateTime.now().plusMinutes(EXPIRY_MINUTES))
                .build();
        
        emailVerificationRepository.save(verification);

        emailSender.sendVerificationEmail(email, verificationCode, university.getName());
        
        return EmailResponseDto.builder()
                .message("인증번호가 발송되었습니다. 이메일을 확인해주세요.")
                .verified(false)
                .universityName(university.getName())
                .email(email)
                .build();
    }

    public EmailResponseDto verifyCode(EmailRequestDto requestDto) {
        String email = requestDto.getEmail().toLowerCase().trim();
        String inputCode = requestDto.getVerificationCode();

        EmailVerification verification = emailVerificationRepository
                .findByEmailAndVerificationCode(email, inputCode)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 인증번호입니다."));

        if (verification.isExpired()) {
            emailVerificationRepository.delete(verification);
            throw new IllegalArgumentException("인증번호가 만료되었습니다. 다시 요청해주세요.");
        }

        if (verification.isVerified()) {
            throw new IllegalArgumentException("이미 인증이 완료된 이메일입니다.");
        }

        verification.verify();
        emailVerificationRepository.save(verification);
        
        return EmailResponseDto.builder()
                .message("이메일 인증이 완료되었습니다.")
                .verified(true)
                .universityName(verification.getUniversityName())
                .email(email)
                .build();
    }

    @Transactional(readOnly = true)
    public EmailResponseDto getVerificationStatus(String email) {
        String normalizedEmail = email.toLowerCase().trim();
        
        boolean isVerified = emailVerificationRepository
                .existsByEmailAndIsVerified(normalizedEmail, true);
        
        if (!isVerified) {
            return EmailResponseDto.builder()
                    .message("인증이 완료되지 않은 이메일입니다.")
                    .verified(false)
                    .email(normalizedEmail)
                    .build();
        }

        EmailVerification verification = emailVerificationRepository
                .findByEmail(normalizedEmail)
                .orElseThrow(() -> new IllegalArgumentException("인증 정보를 찾을 수 없습니다."));
        
        return EmailResponseDto.builder()
                .message("인증이 완료된 이메일입니다.")
                .verified(true)
                .universityName(verification.getUniversityName())
                .email(normalizedEmail)
                .build();
    }

    private String generateVerificationCode() {
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        
        for (int i = 0; i < VERIFICATION_CODE_LENGTH; i++) {
            code.append(random.nextInt(10));
        }
        
        return code.toString();
    }

    public boolean isEmailVerified(String email) {
        String normalizedEmail = email.toLowerCase().trim();
        return emailVerificationRepository.existsByEmailAndIsVerified(normalizedEmail, true);
    }
}
