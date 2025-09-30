package syu.qchecker.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import syu.qchecker.user.domain.User;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "사용자 정보 응답 DTO")
public class UserResponseDto {
    
    @Schema(description = "사용자 ID", example = "1")
    private Long userId;
    
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
    
    @Schema(description = "인증된 대학교 이메일", example = "20210001@syu.ac.kr")
    private String studentEmail;
    
    @Schema(description = "대학교명", example = "삼육대학교")
    private String universityName;
    
    @Schema(description = "대학생 인증 여부", example = "true")
    private boolean isStudentVerified;

    public static UserResponseDto of(User user) {
        return UserResponseDto.builder()
                .userId(user.getUserId())
                .provider(user.getProvider())
                .firebaseUid(user.getFirebaseUid())
                .email(user.getEmail())
                .name(user.getName())
                .studentNumber(user.getStudentNumber())
                .studentEmail(user.getStudentEmail())
                .universityName(user.getUniversityName())
                .isStudentVerified(user.isStudentVerified())
                .build();
    }
}
