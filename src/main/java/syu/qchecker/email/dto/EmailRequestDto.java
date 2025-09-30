package syu.qchecker.email.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "이메일 인증 요청 DTO")
public class EmailRequestDto {
    
    @Schema(description = "대학교 이메일 주소", example = "student@syu.ac.kr")
    private String email;
    
    @Schema(description = "인증번호 (검증 시에만 필요)", example = "123456")
    private String verificationCode;
}