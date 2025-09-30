package syu.qchecker.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "사용자 등록/수정 요청 DTO")
public class UserRequestDto {
    
    @Schema(description = "소셜 로그인 타입", example = "google")
    private String provider;

    @Schema(description = "Firebase UID")
    private String firebaseUid;
    
    @Schema(description = "소셜 로그인 이메일", example = "user@gmail.com")
    private String email;
    
    @Schema(description = "사용자 이름", example = "홍길동")
    private String name;
    
    @Schema(description = "학번", example = "20210001")
    private Long studentNumber;
    
    @Schema(description = "인증된 대학교 이메일", example = "student@syu.ac.kr")
    private String universityEmail;
} 
