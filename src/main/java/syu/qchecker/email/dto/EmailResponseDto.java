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
@Schema(description = "이메일 인증 응답 DTO")
public class EmailResponseDto {
    
    @Schema(description = "응답 메시지", example = "인증번호가 발송되었습니다.")
    private String message;
    
    @Schema(description = "인증 상태", example = "true")
    private boolean verified;
    
    @Schema(description = "대학교 이름", example = "삼육대학교")
    private String universityName;
    
    @Schema(description = "이메일 주소", example = "student@syu.ac.kr")
    private String email;
}