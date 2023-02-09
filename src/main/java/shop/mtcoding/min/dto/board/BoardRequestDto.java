package shop.mtcoding.min.dto.board;

import lombok.Getter;
import lombok.Setter;

public class BoardRequestDto {

    @Getter
    @Setter
    public static class BoardSaveRequestDto {
        private String title;
        private String content;

    }
}
