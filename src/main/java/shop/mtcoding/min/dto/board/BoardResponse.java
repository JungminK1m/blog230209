package shop.mtcoding.min.dto.board;

import lombok.Getter;
import lombok.Setter;

public class BoardResponse {

    @Setter
    @Getter
    public static class BoardMainResponseDto {
        private int id;
        private String title;
        private String username;
    }
}
