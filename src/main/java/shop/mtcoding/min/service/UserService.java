package shop.mtcoding.min.service;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.min.dto.board.BoardRequest.BoardSaveRequestDto;
import shop.mtcoding.min.dto.user.UserRequest.JoinRequestDto;
import shop.mtcoding.min.dto.user.UserRequest.LoginRequestDto;
import shop.mtcoding.min.handler.exception.CustomException;
import shop.mtcoding.min.model.User;
import shop.mtcoding.min.model.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Transactional
    public void 회원가입(JoinRequestDto joinRequestDto) {
        User sameUser = userRepository.findByUsername(joinRequestDto.getUsername());
        if (sameUser != null) {
            throw new CustomException("동일한 username이 존재합니다.");
        }

        int result = userRepository.insert(joinRequestDto.getUsername(), joinRequestDto.getPassword(),
                joinRequestDto.getEmail());
        if (result != 1) {
            throw new CustomException("회원가입실패");
        }

    }

    public User 로그인(LoginRequestDto loginRequestDto) {
        User checkUser = userRepository.findByUsernameAndPassword(loginRequestDto.getUsername(),
                loginRequestDto.getPassword());
        if (checkUser == null) {
            throw new CustomException("username 혹은 password가 잘못되었습니다.");
        }
        return checkUser;
    }

}
