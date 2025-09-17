package syu.qchecker.email.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailSender {
    
    private final JavaMailSender mailSender;

    public void sendVerificationEmail(String toEmail, String verificationCode, String universityName) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setSubject("[Q-Checker] " + universityName + " 이메일 인증번호");
            message.setText(buildVerificationEmailContent(verificationCode, universityName));
            message.setFrom("noreply@qchecker.com");
            
            mailSender.send(message);
            log.info("인증번호 이메일 발송 완료: {}", toEmail);
            
        } catch (Exception e) {
            log.error("이메일 발송 실패: {} - {}", toEmail, e.getMessage());
            throw new RuntimeException("이메일 발송에 실패했습니다. 잠시 후 다시 시도해주세요.");
        }
    }

    private String buildVerificationEmailContent(String verificationCode, String universityName) {
        return String.format(
            """
            안녕하세요, Q-Checker입니다.
            
            %s 학생 인증을 위한 인증번호입니다.
            
            인증번호: %s
            
            ※ 이 인증번호는 10분 후 만료됩니다.
            ※ 본인이 요청한 것이 아니라면 이 이메일을 무시하세요.
            """,
            universityName, verificationCode
        );
    }
}
