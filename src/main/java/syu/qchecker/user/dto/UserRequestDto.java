package syu.qchecker.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserRequestDto {
    private String socialType;
    private String socialId;
    private String email;
    private String name;
    private Long studentNumber;
} 
