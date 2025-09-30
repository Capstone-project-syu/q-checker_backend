package syu.qchecker.auth.dto;

import lombok.Data;

@Data
public class FirebaseLoginRequestDto {
    private String idToken;
    private String provider;
}
