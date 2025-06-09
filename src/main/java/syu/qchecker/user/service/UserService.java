package syu.qchecker.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import syu.qchecker.user.domain.User;
import syu.qchecker.user.dto.UserCreateDto;
import syu.qchecker.user.dto.UserDto;
import syu.qchecker.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public User createUser(UserCreateDto userCreateDto) {
        User user = User.builder()
                .email(userCreateDto.getEmail())
                .name(userCreateDto.getName())
                .socialType(userCreateDto.getSocialType())
                .socialId(userCreateDto.getSocialId())
                .studentNumber(userCreateDto.getStudentNumber())
                .build();

        return userRepository.save(user);
    }

    @Transactional
    public UserDto.Response updateUser(Long userId, UserDto.Update updateDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        user.setName(updateDto.getName());
        user.setStudentNumber(updateDto.getStudentNumber());

        return UserDto.Response.from(user);
    }

    public UserDto.Response getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
        return UserDto.Response.from(user);
    }

    public UserDto.Response getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
        return UserDto.Response.from(user);
    }

    public UserDto.Response getUserBySocialId(String socialId, String socialType) {
        User user = userRepository.findBySocialIdAndSocialType(socialId, socialType)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
        return UserDto.Response.from(user);
    }
} 