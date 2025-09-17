package syu.qchecker.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import syu.qchecker.email.service.EmailService;
import syu.qchecker.email.service.UniversityEmailValidator;
import syu.qchecker.university.domain.University;
import syu.qchecker.user.domain.User;
import syu.qchecker.user.dto.UserRequestDto;
import syu.qchecker.user.dto.UserResponseDto;
import syu.qchecker.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final UniversityEmailValidator universityEmailValidator;

    @Transactional
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        User user = User.builder()
                .email(userRequestDto.getEmail())
                .name(userRequestDto.getName())
                .socialType(userRequestDto.getSocialType())
                .socialId(userRequestDto.getSocialId())
                .studentNumber(userRequestDto.getStudentNumber())
                .build();

        if (userRequestDto.getUniversityEmail() != null && 
            !userRequestDto.getUniversityEmail().trim().isEmpty()) {
            
            String universityEmail = userRequestDto.getUniversityEmail().toLowerCase().trim();

            if (!emailService.isEmailVerified(universityEmail)) {
                throw new IllegalArgumentException("인증되지 않은 대학교 이메일입니다. 먼저 이메일 인증을 완료해주세요.");
            }

            University university = universityEmailValidator.getUniversityByEmail(universityEmail)
                    .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 대학교 이메일입니다."));
            user.updateUniversityInfo(universityEmail, university);
        }

        User savedUser = userRepository.save(user);
        return UserResponseDto.of(savedUser);
    }

    @Transactional
    public UserResponseDto updateUserInfo(UserRequestDto userRequestDto, User user) {
        User realUser = userRepository.findById(user.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        
        realUser.setName(userRequestDto.getName());
        realUser.setStudentNumber(userRequestDto.getStudentNumber());

        if (userRequestDto.getUniversityEmail() != null && 
            !userRequestDto.getUniversityEmail().trim().isEmpty()) {
            
            String newUniversityEmail = userRequestDto.getUniversityEmail().toLowerCase().trim();

            if (!newUniversityEmail.equals(realUser.getStudentEmail())) {
                if (!emailService.isEmailVerified(newUniversityEmail)) {
                    throw new IllegalArgumentException("인증되지 않은 대학교 이메일입니다. 먼저 이메일 인증을 완료해주세요.");
                }

                University university = universityEmailValidator.getUniversityByEmail(newUniversityEmail)
                        .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 대학교 이메일입니다."));
                realUser.updateUniversityInfo(newUniversityEmail, university);
            }
        }
        
        return UserResponseDto.of(realUser);
    }

    public boolean isStudentVerified(Long userId) {
        return userRepository.findById(userId)
                .map(User::isStudentVerified)
                .orElse(false);
    }
} 
