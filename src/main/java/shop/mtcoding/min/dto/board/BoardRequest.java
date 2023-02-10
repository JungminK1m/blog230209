package shop.mtcoding.min.dto.board;

import lombok.Getter;
import lombok.Setter;

public class BoardRequest {

    @Setter
    @Getter
    public static class BoardSaveRequestDto {
        private String title;
        private String content;

    }
}
