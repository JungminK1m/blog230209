package shop.mtcoding.min.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.min.dto.board.BoardRequest.BoardSaveRequestDto;
import shop.mtcoding.min.handler.exception.CustomException;
import shop.mtcoding.min.model.User;
import shop.mtcoding.min.service.BoardService;

@Controller
public class BoardController {

    @Autowired
    HttpSession session;

    @Autowired
    BoardService boardService;

    @GetMapping({ "/", "/main" })
    public String main() {
        return "board/main";
    }

    @GetMapping("/board/{id}/detail")
    public String detail() {
        return "board/detail";
    }

    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }

    @GetMapping("/board/updateForm")
    public String updateForm() {
        return "board/updateForm";
    }

    @PostMapping("/board")
    public String save(BoardSaveRequestDto boardSaveRequestDto) {
        User checkUser = (User) session.getAttribute("checkUser");
        if (checkUser == null) {
            throw new CustomException("로그인이 필요합니다.", HttpStatus.UNAUTHORIZED);
        }
        if (boardSaveRequestDto.getTitle() == null || boardSaveRequestDto.getTitle().isEmpty()) {
            throw new CustomException("title을 작성해 주세요.");
        }
        if (boardSaveRequestDto.getContent() == null || boardSaveRequestDto.getTitle().isEmpty()) {
            throw new CustomException("content를 작성해 주세요.");
        }
        boardService.글쓰기(boardSaveRequestDto, checkUser.getId());

        return "redirect:/";
    }
}
