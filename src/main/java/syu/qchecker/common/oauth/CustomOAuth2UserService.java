package syu.qchecker.common.oauth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import syu.qchecker.user.domain.User;
import syu.qchecker.user.repository.UserRepository;

import java.util.Collections;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    
    private final UserRepository userRepository;
    
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);
        
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        
        Map<String, Object> attributes = oAuth2User.getAttributes();
        
        if (registrationId.equals("kakao")) {
            return processKakaoUser(attributes, userNameAttributeName);
        }
        
        return oAuth2User;
    }
    
    private OAuth2User processKakaoUser(Map<String, Object> attributes, String userNameAttributeName) {
        // 카카오 계정 정보 추출
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
        
        String email = (String) kakaoAccount.get("email");
        String name = (String) profile.get("nickname");
        String socialId = attributes.get("id").toString();
        
        // 카카오 사용자 정보를 DB에 저장 또는 업데이트
        User user = userRepository.findBySocialTypeAndSocialId("kakao", socialId)
                .orElse(new User());
        
        user.setSocialType("kakao");
        user.setSocialId(socialId);
        user.setEmail(email);
        user.setName(name);
        
        userRepository.save(user);
        
        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
                attributes,
                userNameAttributeName
        );
    }
}
