package shop.mtcoding.min.dto;

import lombok.Getter;
import lombok.Setter;

public class UserRequestDto {

    @Getter
    @Setter
    public static class JoinRequestDto {
        private String username;
        private String password;
        private String email;

    }

    @Getter
    @Setter
    public static class LoginRequestDto {
        private String username;
        private String password;

    }
}
