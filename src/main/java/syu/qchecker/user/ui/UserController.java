package syu.qchecker.user.ui;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import syu.qchecker.user.dto.UserCreateDto;
import syu.qchecker.user.dto.UserDto;
import syu.qchecker.user.service.UserService;
import syu.qchecker.auth.dto.SessionUserDto;
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
    public ResponseEntity<UserDto.Response> createUser(@RequestBody UserCreateDto createDto) {
        return ResponseEntity.ok(UserDto.Response.from(userService.createUser(createDto)));
    }

    @PutMapping
    @Operation(summary = "회원 정보 수정", description = "회원 정보를 수정합니다.")
    public ResponseEntity<UserDto.Response> updateUser(
            @RequestBody UserDto.Update updateDto,
            @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(userService.updateUserByEmail(user.getEmail(), updateDto));
    }

    @GetMapping
    @Operation(summary = "내 정보 조회", description = "현재 로그인 중인 회원 정보를 조회합니다.")
    public ResponseEntity<UserDto.Response> getMyInfo(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(userService.getUserByEmail(user.getEmail()));
    }

    @GetMapping("/{user_id}/attendances")
    @Operation(summary = "나의 전체 열람", description = "사용자의 전체 출석 내역을 조회합니다.")
    public ResponseEntity<String> getUserAttendances(@PathVariable("user_id") Long userId) {
        return ResponseEntity.ok("출석 내역 조회 성공");
    }

    @PostMapping("/logout")
    @Operation(summary = "로그아웃", description = "사용자를 로그아웃합니다.")
    public ResponseEntity<String> logout() {
        httpSession.invalidate();
        return ResponseEntity.ok("로그아웃 성공");
    }
}
