package syu.qchecker.auth.dto;

import java.util.Map;

import lombok.Builder;
import lombok.Getter;
import syu.qchecker.user.domain.User;

@Getter
public class OAuthAttributesDto {

    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;

    @Builder
    public OAuthAttributesDto(Map<String, Object> attributes,
                              String nameAttributeKey, String name,
                              String email) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
    }

    // OAuth2User에서 반환하는 사용자 정보는 Map
    // 따라서 값 하나하나를 변환해야 한다.
    public static OAuthAttributesDto of(String registrationId,
                                        String userNameAttributeName,
                                        Map<String, Object> attributes) {

        return ofGoogle(userNameAttributeName, attributes);
    }

    // 구글 생성자
    private static OAuthAttributesDto ofGoogle(String usernameAttributeName,
                                               Map<String, Object> attributes) {
        return OAuthAttributesDto.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .attributes(attributes)
                .nameAttributeKey(usernameAttributeName)
                .build();
    }

    // User 엔티티 생성
    public User toEntity() {
        return User.builder()
                .socialType("google")
                .socialId((String) attributes.get("sub"))
                .name(name)
                .email(email)
                .build();
    }
}
