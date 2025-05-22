package syu.qchecker.common.config;

import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws org.springframework.security.oauth2.core.OAuth2AuthenticationException {
        // 기본 OAuth2UserService로부터 사용자 정보 가져오기
        OAuth2User oAuth2User = new DefaultOAuth2UserService().loadUser(userRequest);

        // OAuth2 공급자 이름 (Google, Kakao 등)을 확인 가능
        String clientName = userRequest.getClientRegistration().getRegistrationId();

        // 사용자 속성 (추가 처리용)
        Map<String, Object> attributes = oAuth2User.getAttributes();

        // 사용자 커스텀 처리 로직 구현 (예: 사용자 DB 저장, 권한 추가 등)
        // 예시로 로그 출력만 포함
        System.out.println("OAuth2 Client: " + clientName);
        System.out.println("User Attributes: " + attributes);

        // 필요 시, CustomOAuth2User 반환하여 관리 가능
        return oAuth2User;
    }
}