package shop.mtcoding.min.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.min.dto.user.UserRequest.JoinRequestDto;
import shop.mtcoding.min.dto.user.UserRequest.LoginRequestDto;
import shop.mtcoding.min.handler.exception.CustomException;
import shop.mtcoding.min.model.User;
import shop.mtcoding.min.service.UserService;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private HttpSession session;

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
        System.out.println("회원가입 됨");
        return "redirect:/loginForm";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }

    @PostMapping("/login")
    public String login(LoginRequestDto loginRequestDto) {
        if (loginRequestDto == null || loginRequestDto.getUsername().isEmpty()) {
            throw new CustomException("username을 입력하세요.");
        }
        if (loginRequestDto == null || loginRequestDto.getPassword().isEmpty()) {
            throw new CustomException("password를 입력하세요.");
        }
        User checkUser = userService.로그인(loginRequestDto);
        session.setAttribute("checkUser", checkUser);
        System.out.println("로그인 됨");
        return "redirect:/";
    }

    @GetMapping("/user/updateForm")
    public String updateForm() {
        return "user/updateForm";
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        System.out.println("로그아웃 됨");
        return "redirect:/";
    }
}
