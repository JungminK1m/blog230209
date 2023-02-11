package shop.mtcoding.min.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.min.dto.board.BoardRequest.BoardSaveRequestDto;
import shop.mtcoding.min.dto.board.BoardRequest.BoardUpdateRequestDto;
import shop.mtcoding.min.handler.exception.CustomApiException;
import shop.mtcoding.min.handler.exception.CustomException;
import shop.mtcoding.min.model.Board;
import shop.mtcoding.min.model.BoardRepository;

@Transactional(readOnly = true)
@Service
public class BoardService {

    @Autowired
    BoardRepository boardRepository;

    @Transactional
    public void 글쓰기(BoardSaveRequestDto boardSaveRequestDto, int userId) {
        int result = boardRepository.insert(boardSaveRequestDto.getTitle(), boardSaveRequestDto.getContent(), userId);
        if (result != 1) {
            throw new CustomException("글쓰기 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public void 게시글삭제(int id, int userId) {
        Board boardPS = boardRepository.findById(id);
        if (boardPS == null) {
            throw new CustomApiException("없는 게시글을 삭제할 수 없습니다");
        }
        if (boardPS.getUserId() != userId) {
            throw new CustomApiException("해당 게시글을 삭제할 권한이 없습니다", HttpStatus.FORBIDDEN);
        }

        try {
            boardRepository.deleteById(id);
        } catch (Exception e) {
            throw new CustomApiException("서버에 일시적인 문제가 생겼습니다", HttpStatus.INTERNAL_SERVER_ERROR);
            // 로그를 남겨야 함 (DB or File)
        }

    }

    @Transactional
    public void 게시글수정(int id, BoardUpdateRequestDto boardUpdateRequestDto, int checkUserId) {
        Board boardPS = boardRepository.findById(id);
        if (boardPS == null) {
            throw new CustomApiException("해당 게시글을 찾을 수 없습니다");
        }
        if (boardPS.getUserId() != checkUserId) {
            throw new CustomApiException("게시글을 수정할 권한이 없습니다", HttpStatus.FORBIDDEN);
        }

        int result = boardRepository.updateById(id, boardUpdateRequestDto.getTitle(),
                boardUpdateRequestDto.getContent());
        if (result != 1) {
            throw new CustomApiException("게시글을 수정에 실패하였습니다", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
