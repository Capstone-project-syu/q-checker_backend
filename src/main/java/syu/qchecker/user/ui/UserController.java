package syu.qchecker.user.ui;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Tag(name = "마이페이지", description = "마이페이지 관련 API")
public class UserController {

    @PutMapping("/{user_id}")
    @Operation(summary = "회원 정보 수정", description = "회원 정보를 수정합니다.")
    public String updateUser(@PathVariable String user_id) {
        // ...existing code...
        return "회원 정보 수정 성공";
    }

    @GetMapping("/{user_id}/attendances")
    @Operation(summary = "나의 전체 열람", description = "사용자의 전체 출석 내역을 조회합니다.")
    public String getUserAttendances(@PathVariable String user_id) {
        // ...existing code...
        return "출석 내역 조회 성공";
    }

    @PostMapping("/logout")
    @Operation(summary = "로그아웃", description = "사용자를 로그아웃합니다.")
    public String logout() {
        // ...existing code...
        return "로그아웃 성공";
    }
}
