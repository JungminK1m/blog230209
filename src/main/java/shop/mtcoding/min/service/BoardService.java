package shop.mtcoding.min.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import shop.mtcoding.min.dto.board.BoardRequest.BoardSaveRequestDto;
import shop.mtcoding.min.handler.exception.CustomException;
import shop.mtcoding.min.model.BoardRepository;

@Service
public class BoardService {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    HttpSession session;

    public void 글쓰기(BoardSaveRequestDto boardSaveRequestDto, int userId) {
        int result = boardRepository.insert(boardSaveRequestDto.getTitle(), boardSaveRequestDto.getContent(), userId);
        if (result != 1) {
            throw new CustomException("글쓰기 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
