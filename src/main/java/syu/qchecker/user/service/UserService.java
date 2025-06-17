package syu.qchecker.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import syu.qchecker.user.domain.User;
import syu.qchecker.user.dto.UserRequestDto;
import syu.qchecker.user.dto.UserResponseDto;
import syu.qchecker.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        User user = User.builder()
                .email(userRequestDto.getEmail())
                .name(userRequestDto.getName())
                .socialType(userRequestDto.getSocialType())
                .socialId(userRequestDto.getSocialId())
                .studentNumber(userRequestDto.getStudentNumber())
                .build();

        User savedUser = userRepository.save(user);
        return UserResponseDto.of(savedUser);
    }

    @Transactional
    public UserResponseDto updateUserInfo(UserRequestDto userRequestDto, User user) {
        user.setName(userRequestDto.getName());
        user.setStudentNumber(userRequestDto.getStudentNumber());
        return UserResponseDto.of(user);
    }
} 
