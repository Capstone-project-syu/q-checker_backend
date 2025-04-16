package syu.qchecker.user.ui;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "User API", description = "User 관련 API")
public class UserController {

    @Operation(summary = "예시 API", description = "예시 API입니다.")
    @GetMapping("/users/{id}")
    public String getUser() {
        return "유저 정보";
    }
}
