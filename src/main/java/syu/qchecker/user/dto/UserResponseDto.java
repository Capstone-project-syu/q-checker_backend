package syu.qchecker.user.dto;

import lombok.Builder;
import lombok.Getter;
import syu.qchecker.user.domain.User;

@Getter
@Builder
public class UserResponseDto {
    private Long userId;
    private String socialType;
    private String socialId;
    private String email;
    private String name;
    private Long studentNumber;

    public static UserResponseDto of(User user) {
        return UserResponseDto.builder()
                .userId(user.getUserId())
                .socialType(user.getSocialType())
                .socialId(user.getSocialId())
                .email(user.getEmail())
                .studentNumber(user.getStudentNumber())
                .build();
    }
}
