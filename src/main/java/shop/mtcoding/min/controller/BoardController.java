package shop.mtcoding.min.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.mtcoding.min.dto.ResponseDto;
import shop.mtcoding.min.dto.board.BoardRequest.BoardSaveRequestDto;
import shop.mtcoding.min.dto.board.BoardRequest.BoardUpdateRequestDto;
import shop.mtcoding.min.handler.exception.CustomApiException;
import shop.mtcoding.min.handler.exception.CustomException;
import shop.mtcoding.min.model.Board;
import shop.mtcoding.min.model.BoardRepository;
import shop.mtcoding.min.model.User;
import shop.mtcoding.min.service.BoardService;

@Controller
public class BoardController {

    @Autowired
    HttpSession session;

    @Autowired
    BoardService boardService;

    @Autowired
    BoardRepository boardRepository;

    @GetMapping({ "/", "/main" })
    public String main(Model model) {
        model.addAttribute("dtos", boardRepository.findAllWithUser());
        return "board/main";
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable int id, Model model) {
        model.addAttribute("dto", boardRepository.findByIdWithUser(id));
        return "board/detail";
    }

    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }

    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable int id, Model model) {
        User checkUser = (User) session.getAttribute("checkUser");
        if (checkUser == null) {
            throw new CustomException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        }
        Board boardPS = boardRepository.findById(id);
        if (boardPS == null) {
            throw new CustomException("없는 게시글을 수정할 수 없습니다.");
        }
        if (boardPS.getUserId() != checkUser.getId()) {
            throw new CustomException("게시글을 수정할 권한이 없습니다.", HttpStatus.FORBIDDEN);
        }
        model.addAttribute("board", boardPS);
        return "board/updateForm";
    }

    @PostMapping("/board")
    public @ResponseBody ResponseEntity<?> save(@RequestBody BoardSaveRequestDto boardSaveRequestDto) {
        User checkUser = (User) session.getAttribute("checkUser");
        if (checkUser == null) {
            throw new CustomApiException("로그인이 필요합니다.", HttpStatus.UNAUTHORIZED);
        }
        if (boardSaveRequestDto.getTitle() == null || boardSaveRequestDto.getTitle().isEmpty()) {
            throw new CustomApiException("title을 작성해 주세요.");
        }
        if (boardSaveRequestDto.getContent() == null || boardSaveRequestDto.getTitle().isEmpty()) {
            throw new CustomApiException("content를 작성해 주세요.");
        }
        boardService.글쓰기(boardSaveRequestDto, checkUser.getId());
        System.out.println("글 작성됨");
        return new ResponseEntity<>(new ResponseDto<>(1, "글쓰기성공", null), HttpStatus.CREATED);
    }

    @DeleteMapping("/board/{id}")
    public @ResponseBody ResponseEntity<?> delete(@PathVariable int id) {
        User checkUser = (User) session.getAttribute("checkUser");
        if (checkUser == null) {
            throw new CustomApiException("인증이 되지 않아 삭제할 수 없습니다.", HttpStatus.UNAUTHORIZED);
        }

        boardService.게시글삭제(id, checkUser.getId());
        return new ResponseEntity<>(new ResponseDto<>(1, "삭제성공", null), HttpStatus.OK);
    }

    @PutMapping("/board/{id}")
    public @ResponseBody ResponseEntity<?> update(@PathVariable int id,
            @RequestBody BoardUpdateRequestDto boardUpdateRequestDto, HttpServletResponse response) {
        User checkUser = (User) session.getAttribute("checkUser");
        if (checkUser == null) {
            throw new CustomException("인증이 되지 않았습니다");
        }
        if (boardUpdateRequestDto.getTitle() == null || boardUpdateRequestDto.getTitle().isEmpty()) {
            throw new CustomException("title을 작성해주세요");
        }

        if (boardUpdateRequestDto.getContent() == null || boardUpdateRequestDto.getContent().isEmpty()) {
            throw new CustomException("content을 작성해주세요");
        }
        boardService.게시글수정(id, boardUpdateRequestDto, checkUser.getId());
        System.out.println("게시글 수정됨");
        return new ResponseEntity<>(new ResponseDto<>(1, "게시글수정 성공", null), HttpStatus.OK);
    }
}
