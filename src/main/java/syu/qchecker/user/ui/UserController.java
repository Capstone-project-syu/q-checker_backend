package syu.qchecker.user.ui;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import syu.qchecker.user.dto.UserCreateDto;
import syu.qchecker.user.dto.UserDto;
import syu.qchecker.user.service.UserService;

@RestController
@RequestMapping("/api/users")
@Tag(name = "마이페이지", description = "마이페이지 관련 API")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    @Operation(summary = "회원 가입", description = "소셜 로그인을 통해 회원가입을 합니다.")
    public ResponseEntity<UserDto.Response> createUser(@RequestBody UserCreateDto createDto) {
        return ResponseEntity.ok(UserDto.Response.from(userService.createUser(createDto)));
    }

    @PutMapping("/{userId}")
    @Operation(summary = "회원 정보 수정", description = "회원 정보를 수정합니다.")
    public ResponseEntity<UserDto.Response> updateUser(
            @PathVariable Long userId,
            @RequestBody UserDto.Update updateDto) {
        return ResponseEntity.ok(userService.updateUser(userId, updateDto));
    }

    @GetMapping("/{userId}")
    @Operation(summary = "회원 정보 조회", description = "회원 ID로 회원 정보를 조회합니다.")
    public ResponseEntity<UserDto.Response> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @GetMapping("/{user_id}/attendances")
    @Operation(summary = "나의 전체 열람", description = "사용자의 전체 출석 내역을 조회합니다.")
    public ResponseEntity<String> getUserAttendances(@PathVariable("user_id") Long userId) {
        return ResponseEntity.ok("출석 내역 조회 성공");
    }

    @PostMapping("/logout")
    @Operation(summary = "로그아웃", description = "사용자를 로그아웃합니다.")
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok("로그아웃 성공");
    }
}
