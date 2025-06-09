package syu.qchecker.auth.dto;

import lombok.Getter;
import syu.qchecker.user.domain.User;

@Getter
public class SessionUserDto {
    private String name;
    private String email;

    public SessionUserDto(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
    }
}

