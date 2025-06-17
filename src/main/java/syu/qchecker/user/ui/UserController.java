package syu.qchecker.user.ui;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import syu.qchecker.user.dto.UserRequestDto;
import syu.qchecker.user.dto.UserResponseDto;
import syu.qchecker.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import syu.qchecker.user.domain.User;

@RestController
@RequestMapping("/api/users")
@Tag(name = "마이페이지", description = "마이페이지 관련 API")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final HttpSession httpSession;

    @PostMapping
    @Operation(summary = "회원 가입", description = "소셜 로그인을 통해 회원가입을 합니다.")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto) {
        UserResponseDto response = userService.createUser(userRequestDto);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @Operation(summary = "회원 정보 수정", description = "회원 정보를 수정합니다.")
    public ResponseEntity<UserResponseDto> updateUserInfo(@RequestBody UserRequestDto userRequestDto,
                                                          @AuthenticationPrincipal User user) {
        UserResponseDto response = userService.updateUserInfo(userRequestDto, user);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "내 정보 조회", description = "현재 로그인 중인 회원 정보를 조회합니다.")
    public ResponseEntity<UserResponseDto> getMyInfo(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(UserResponseDto.of(user));
    }

    @PostMapping("/logout")
    @Operation(summary = "로그아웃", description = "사용자를 로그아웃합니다.")
    public ResponseEntity<String> logout() {
        httpSession.invalidate();
        return ResponseEntity.ok("로그아웃 성공");
    }
}
