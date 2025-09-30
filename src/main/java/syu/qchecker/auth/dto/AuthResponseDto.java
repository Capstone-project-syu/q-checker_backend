package syu.qchecker.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponseDto {
    private String accessToken;
    private String tokenType;
    private Long expiresIn;
    private UserInfoDto user;

    @Data
    @AllArgsConstructor
    public static class UserInfoDto {
        private Long id;
        private String email;
        private String name;
        private String provider;
    }
}