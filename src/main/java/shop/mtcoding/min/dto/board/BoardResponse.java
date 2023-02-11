package shop.mtcoding.min.dto.board;

import lombok.Getter;
import lombok.Setter;

public class BoardResponse {

    @Setter
    @Getter
    public static class BoardMainResponseDto {
        private int id;
        private String title;
        private String thumbnail;
        private String username;
    }

    @Setter
    @Getter
    public static class BoardDetailResponseDto {
        private int id;
        private String title;
        private String content;
        private int userId;
        private String username;

    }
}
