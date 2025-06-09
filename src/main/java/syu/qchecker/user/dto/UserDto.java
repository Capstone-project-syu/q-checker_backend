package syu.qchecker.user.dto;

import lombok.Getter;
import lombok.Setter;
import syu.qchecker.user.domain.User;

import java.time.LocalDateTime;

public class UserDto {
    @Getter
    @Setter
    public static class Create {
        private String socialType;
        private String socialId;
        private String email;
        private String name;
        private Long studentNumber;
    }

    @Getter
    @Setter
    public static class Update {
        private String name;
        private Long studentNumber;
    }

    @Getter
    @Setter
    public static class Response {
        private Long userId;
        private String socialType;
        private String socialId;
        private String email;
        private String name;
        private Long studentNumber;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public static Response from(User user) {
            Response response = new Response();
            response.setUserId(user.getUserId());
            response.setSocialType(user.getSocialType());
            response.setSocialId(user.getSocialId());
            response.setEmail(user.getEmail());
            response.setName(user.getName());
            response.setStudentNumber(user.getStudentNumber());
            response.setCreatedAt(user.getCreatedAt());
            response.setUpdatedAt(user.getUpdatedAt());
            return response;
        }
    }
} 