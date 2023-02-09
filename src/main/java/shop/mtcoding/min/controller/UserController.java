package shop.mtcoding.min.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.min.dto.UserRequestDto.JoinRequestDto;
import shop.mtcoding.min.handler.exception.CustomException;
import shop.mtcoding.min.service.UserService;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @PostMapping("/join")
    public String join(JoinRequestDto joinRequestDto) {
        if (joinRequestDto.getUsername() == null || joinRequestDto.getUsername().isEmpty()) {
            throw new CustomException("username을 작성해 주세요.");
        }
        if (joinRequestDto.getPassword() == null || joinRequestDto.getPassword().isEmpty()) {
            throw new CustomException("password를 작성해 주세요.");
        }
        if (joinRequestDto.getEmail() == null || joinRequestDto.getEmail().isEmpty()) {
            throw new CustomException("email을 작성해 주세요.");
        }

        userService.회원가입(joinRequestDto);
        // 회원가입이 잘 됐으면
        return "redirect:/loginForm";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }

    @GetMapping("/user/updateForm")
    public String updateForm() {
        return "user/updateForm";
    }

}
