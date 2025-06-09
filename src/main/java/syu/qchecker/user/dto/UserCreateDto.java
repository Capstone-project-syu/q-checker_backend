package syu.qchecker.user.dto;

import lombok.Getter;

@Getter
public class UserCreateDto {
    private String socialType;
    private String socialId;
    private String email;
    private String name;
    private Long studentNumber;

    public UserCreateDto(String socialType, String socialId, String email, String name, Long studentNumber) {
        this.socialType = socialType;
        this.socialId = socialId;
        this.email = email;
        this.name = name;
        this.studentNumber = studentNumber;
    }
} 