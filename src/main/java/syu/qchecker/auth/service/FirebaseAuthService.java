package syu.qchecker.auth.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import syu.qchecker.auth.dto.AuthResponseDto;
import syu.qchecker.auth.dto.FirebaseLoginRequestDto;
import syu.qchecker.auth.jwt.JwtTokenProvider;
import syu.qchecker.user.domain.User;
import syu.qchecker.user.repository.UserRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class FirebaseAuthService {

    private final FirebaseAuth firebaseAuth;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthResponseDto authenticateUser(FirebaseLoginRequestDto request) {
        try {
            // Firebase ID 토큰 검증
            FirebaseToken decodedToken = firebaseAuth.verifyIdToken(request.getIdToken());

            String uid = decodedToken.getUid();
            String email = decodedToken.getEmail();
            String name = decodedToken.getName();
            String provider = request.getProvider();

            log.info("Firebase authentication successful for user: {}", email);

            // 사용자 정보 저장 또는 업데이트
            User user = saveOrUpdateUser(email, name, provider, uid);

            // JWT 토큰 생성
            String accessToken = jwtTokenProvider.createToken(user.getEmail());
            Long expiresIn = jwtTokenProvider.getTokenValidityInMilliseconds();

            // 응답 DTO 생성
            AuthResponseDto.UserInfoDto userInfo = new AuthResponseDto.UserInfoDto(
                user.getId(),
                user.getEmail(),
                user.getName(),
                provider
            );

            return new AuthResponseDto(accessToken, "Bearer", expiresIn, userInfo);

        } catch (FirebaseAuthException e) {
            log.error("Firebase authentication failed: {}", e.getMessage());
            throw new RuntimeException("Invalid Firebase token", e);
        }
    }

    private User saveOrUpdateUser(String email, String name, String provider, String firebaseUid) {
        return userRepository.findByEmail(email)
                .map(existingUser -> {
                    existingUser.update(name);
                    existingUser.setFirebaseUid(firebaseUid);
                    existingUser.setProvider(provider);
                    return userRepository.save(existingUser);
                })
                .orElseGet(() -> {
                    User newUser = User.builder()
                            .email(email)
                            .name(name)
                            .provider(provider)
                            .firebaseUid(firebaseUid)
                            .build();
                    return userRepository.save(newUser);
                });
    }
}